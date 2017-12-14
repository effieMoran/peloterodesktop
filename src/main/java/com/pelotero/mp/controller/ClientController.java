package com.pelotero.mp.controller;

import com.pelotero.mp.Main;
import com.pelotero.mp.bean.Client;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.helper.AlertHelper;
import com.pelotero.mp.helper.DatePickerHelper;
import com.pelotero.mp.helper.GraphicsHelper;
import com.pelotero.mp.helper.ValidationHelper;
import com.pelotero.mp.service.ClientService;
import com.pelotero.mp.view.FxmlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import jfxtras.scene.menu.CornerMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ClientController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    BorderPane borderPane;

    CornerMenu cornerMenu;

    @Autowired
    private CustomMenuController customMenu;

    @FXML
    Button buttonExit;

    @FXML
    Button btnLogout;

    @FXML
    Button buttonMenu;

    @FXML
    MenuItem deleteUsers;

    @FXML
    private Label clientId;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker dob;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private TextField email;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField cellPhoneNumber;

    @FXML
    private TextField adress;

    @FXML
    private TextField cuil;

    @FXML
    private Button reset;

    @FXML
    private Button saveUser;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, Long> colUserId;
    @FXML
    private TableColumn<Client, String> colFirstName;
    @FXML
    private TableColumn<Client, String> colLastName;
    @FXML
    private TableColumn<Client, LocalDate> colDOB;
    @FXML
    private TableColumn<Client, String> colGender;
    @FXML
    private TableColumn<Client, String> colEmail;
    @FXML
    private TableColumn<Client, Boolean> colEdit;
    @FXML
    private TableColumn<Client, String> columnAdress;
    @FXML
    private TableColumn<Client, String> columnCuil;
    @FXML
    private TableColumn<Client, String> columnPhone;
    @FXML
    private TableColumn<Client, String> columnCellPhone;


    @Autowired
    private ClientService clientService;

    private ObservableList<Client> clientList = FXCollections.observableArrayList();

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Logout and go to the login page
     */
    @FXML
    private void logout(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void saveClient(ActionEvent event){

        if(ValidationHelper.validate("First Name", getFirstName(), Constants.NAME_PATTERN) &&
                ValidationHelper.validate("Last Name", getLastName(), Constants.NAME_PATTERN) &&
                ValidationHelper.emptyValidation("DOB", dob.getEditor().getText().isEmpty())){

            if(clientId.getText() == null || "".equals(clientId.getText())){
                if(ValidationHelper.validate("Email", getEmail(), Constants.EMAIL_PATTERN)){

                    Client client = new Client();
                    setClientFields(client);
                    client = clientService.save(client);
                    AlertHelper.saveAlert("Usuario", "El cliente fue creado");
                }
            }else{
                Client client = clientService.find(Long.parseLong(clientId.getText()));
                setClientFields(client);
                client = clientService.update(client);
                AlertHelper.updateAlert("Cliente", "Cliente actualizado");
            }

            clearFields();
            loadClientDetails();
        }
    }

    private void setClientFields(Client client) {
        client.setFirstName(getFirstName());
        client.setLastName(getLastName());
        client.setBirthDate(getDob());
        client.setGender(getGender());
        client.setEmail(getEmail());
        client.setAdress(getAdress());
    }
    @FXML
    private void deleteClients(ActionEvent event){
        List<Client> clients = clientTable.getSelectionModel().getSelectedItems();
        if(AlertHelper.deleteAlert()) clientService.deleteInBatch(clients);
        loadClientDetails();
    }

    private void clearFields() {
        clientId.setText(null);
        firstName.clear();
        lastName.clear();
        dob.getEditor().clear();
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        adress.setText(null);
        phoneNumber.setText(null);
        cellPhoneNumber.setText(null);
        cuil.setText(null);
        email.clear();
    }

    private String getGenderTitle(String gender){
        return (gender.equals("Male")) ? "his" : "her";
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public LocalDate getDob() {
        return dob.getValue();
    }

    public String getGender(){
        return rbMale.isSelected() ? "Male" : "Female";
    }

    public String getEmail() {
        return email.getText();
    }

    public String getAdress(){
        return adress.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadClientDetails();
        DatePickerHelper.setCalendarFonOnlyAdults(dob);
        cornerMenu= new CornerMenu(CornerMenu.Location.TOP_LEFT, this.borderPane, true)
                .withAnimationInterpolation(null)
                .withAutoShowAndHide(true);
        cornerMenu.getItems().addAll(customMenu.addMenuItems());
    }

    private void setColumnProperties(){

        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnAdress.setCellValueFactory(new PropertyValueFactory<Client, String>("adress"));
        columnCellPhone.setCellValueFactory(new PropertyValueFactory<>("cellPhone"));
        columnCuil.setCellValueFactory(new PropertyValueFactory<Client, String>("cuil"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Client, Boolean>, TableCell<Client, Boolean>> cellFactory =
            new Callback<TableColumn<Client, Boolean>, TableCell<Client, Boolean>>()
            {
                @Override
                public TableCell<Client, Boolean> call( final TableColumn<Client, Boolean> param)
                {
                    return new TableCell<Client, Boolean>()
                    {
                        Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                        final Button btnEdit = new Button();

                        @Override
                        public void updateItem(Boolean check, boolean empty)
                        {
                            super.updateItem(check, empty);
                            if(empty)
                            {
                                setGraphic(null);
                                setText(null);
                            }
                            else{
                                btnEdit.setOnAction(e ->{
                                    Client client = getTableView().getItems().get(getIndex());
                                    updateClient(client);
                                });

                                btnEdit.setStyle(Constants.TRANSPARENT_BACKGROUND);
                                btnEdit.setGraphic(GraphicsHelper.fixEditImage(getClass()));

                                setGraphic(btnEdit);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }

                        private void updateClient( Client client) {
                            clientId.setText(Long.toString(client.getId()));
                            firstName.setText(client.getFirstName());
                            lastName.setText(client.getLastName());
                            dob.setValue(client.getBirthDate());
                            adress.setText(client.getAdress());
                            cellPhoneNumber.setText(client.getCellphoneNumber());
                            phoneNumber.setText(client.getPhoneNumber());
                            cuil.setText(client.getCuil());
                            if(client.getGender().equals("Male")) rbMale.setSelected(true);
                            else rbFemale.setSelected(true);
                        }
                    };
                }
            };

    private void loadClientDetails(){
        clientList.clear();
        clientList.addAll(clientService.findAll());
        clientTable.setItems(clientList);
    }

    //region ACTIONS

    @FXML
    private void goBackToMenu(ActionEvent event) throws IOException {
        if(Main.isAdmin()) {
            stageManager.switchScene(FxmlView.MENUADMIN);
        }
    }

    //endregion
}
