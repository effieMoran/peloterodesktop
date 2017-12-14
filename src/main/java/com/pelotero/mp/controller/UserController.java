package com.pelotero.mp.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.pelotero.mp.Main;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.helper.AlertHelper;
import com.pelotero.mp.helper.GraphicsHelper;
import com.pelotero.mp.helper.ValidationHelper;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import jfxtras.scene.menu.CornerMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.pelotero.mp.bean.User;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.service.UserService;
import com.pelotero.mp.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

@Controller
public class UserController implements Initializable {

    @FXML
    BorderPane borderPane;

    CornerMenu cornerMenu;

    @Lazy
    @Autowired
    private CustomMenuController customMenu;

    //region FXML_CONTROLS
    @FXML
    private Button btnLogout;
    @FXML
    private Label userId;
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
    private ComboBox<String> cbRole;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button reset;
    @FXML
    private Button saveUser;
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonMenu;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Long> colUserId;
    @FXML
    private TableColumn<User, String> colFirstName;
    @FXML
    private TableColumn<User, String> colLastName;
    @FXML
    private TableColumn<User, LocalDate> colDOB;
    @FXML
    private TableColumn<User, String> colGender;
    @FXML
    private TableColumn<User, String> colRole;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, Boolean> colEdit;

    @FXML
    private MenuItem deleteUsers;

    //endregion

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private UserService userService;

    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<String> roles = FXCollections.observableArrayList("Admin", "User");

    //region ACTIONS
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML
    private void goBackToMenu(ActionEvent event) throws IOException {
        if(Main.isAdmin()) {
            stageManager.switchScene(FxmlView.MENUADMIN);
        }
    }

    //endregion
    
    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }
    
    @FXML
    private void saveUser(ActionEvent event){

        if(ValidationHelper.validate("First Name", getFirstName(), Constants.NAME_PATTERN) &&
           ValidationHelper.validate("Last Name", getLastName(), Constants.NAME_PATTERN) &&
           ValidationHelper.emptyValidation("DOB", dob.getEditor().getText().isEmpty()) &&
           ValidationHelper.emptyValidation("Role", getRole() == null) ){

            if(userId.getText() == null || "".equals(userId.getText())){
                if(ValidationHelper.validate("Email", getEmail(), Constants.EMAIL_PATTERN) &&
                    ValidationHelper.emptyValidation("Password", getPassword().isEmpty())){

                    User user = new User();
                    setUserFields(user);
                    user =userService.save(user);
                    AlertHelper.saveAlert("Usuario guardado", "El usuario "+user.getFirstName()
                            +" "+user.getLastName() +"ha sido guardado exitoasamente y su Identificador es \n" + user.getId());
                }
            }else{
                User user = userService.find(Long.parseLong(userId.getText()));
                setUserFields(user);
                user = userService.update(user);
                AlertHelper.updateAlert("Usuario Actualizado", "El usuario "+user.getFirstName()
                        + " " + user.getLastName()+ "fue actualizado exitosamente ");
            }

            clearFields();
            loadUserDetails();
        }
    }

    private void setUserFields(User user) {
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setDob(getDob());
        user.setGender(getGender());
        user.setRole(getRole());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
    }

    @FXML
    private void deleteUsers(ActionEvent event){
        List<User> users = userTable.getSelectionModel().getSelectedItems();
        if(AlertHelper.deleteAlert()) userService.deleteInBatch(users);
        loadUserDetails();
    }
    
    private void clearFields() {
        userId.setText(null);
        firstName.clear();
        lastName.clear();
        dob.getEditor().clear();
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        cbRole.getSelectionModel().clearSelection();
        email.clear();
        password.clear();
    }

    //region GETTERS


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
        return rbMale.isSelected() ? "Hombre" : "Mujer";
    }

    public String getRole() {
        return cbRole.getSelectionModel().getSelectedItem();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbRole.setItems(roles);
        userTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();

        // Add all users into table
        loadUserDetails();

        setCalendarFonOnlyAdults();
        cornerMenu= new CornerMenu(CornerMenu.Location.TOP_LEFT, borderPane, true)
                .withAnimationInterpolation(null)
                .withAutoShowAndHide(true);
        cornerMenu.getItems().addAll(customMenu.addMenuItems());
    }

    private void setCalendarFonOnlyAdults() {
        dob.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isAfter(LocalDate.now().minusYears(Constants.MINIMUN_AGE))) {
                    setDisable(true);
                }
            }
        });
        dob.setValue(LocalDate.now().minusYears(Constants.MINIMUN_AGE));
    }

    /*
     *  Set All userTable column properties
     */
    private void setColumnProperties(){
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>> cellFactory =
            new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>()
    {
        @Override
        public TableCell<User, Boolean> call( final TableColumn<User, Boolean> param)
        {
            return   new TableCell<User, Boolean>()
            {
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
                            User user = getTableView().getItems().get(getIndex());
                            updateUser(user);
                        });

                        btnEdit.setStyle(Constants.TRANSPARENT_BACKGROUND);
                        btnEdit.setGraphic(GraphicsHelper.fixEditImage(getClass()));

                        setGraphic(btnEdit);
                        setAlignment(Pos.CENTER);
                        setText(null);
                    }
                }

                private void updateUser(User user) {
                    userId.setText(Long.toString(user.getId()));
                    firstName.setText(user.getFirstName());
                    lastName.setText(user.getLastName());
                    dob.setValue(user.getDob());
                    if(user.getGender().equals("Male")) rbMale.setSelected(true);
                    else rbFemale.setSelected(true);
                    cbRole.getSelectionModel().select(user.getRole());
                }
            };
        }
    };

    /*
     *  Add All users to observable list and update table
     */
    private void loadUserDetails(){
        userList.clear();
        userList.addAll(userService.findAll());
        userTable.setItems(userList);
    }

}
