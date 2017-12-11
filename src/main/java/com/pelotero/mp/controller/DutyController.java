package com.pelotero.mp.controller;

import com.pelotero.mp.Main;
import com.pelotero.mp.bean.Duty;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.helper.AlertHelper;
import com.pelotero.mp.helper.GraphicsHelper;
import com.pelotero.mp.service.DutyService;
import com.pelotero.mp.view.FxmlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import jfxtras.scene.menu.CornerMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Eli on 9/12/2017.
 */
@Controller
public class DutyController implements Initializable {
    @FXML
    BorderPane borderPane;

    CornerMenu cornerMenu;

    @Autowired
    private CustomMenuController customMenu;

    @FXML
    private Button btnLogout;

    @FXML
    private TableView<Duty> dutyTable;

    @FXML
    private Button save;

    @FXML
    private Button buttonExit;

    @FXML
    private MenuItem deleteUsers;

    @FXML
    private Button buttonMenu;

    @FXML
    private Label labelDutyId;

    @FXML
    private TextField price;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Duty, Double> columnPrice;

    @FXML
    private Button reset;

    @FXML
    private TableColumn<Duty, Long> columnId;

    @FXML
    private TableColumn<Duty, Boolean> columnEdit;

    @FXML
    private TableColumn<Duty, String> columnName;

    @Autowired
    private DutyService dutyService;

    private ObservableList<Duty> dutyList = FXCollections.observableArrayList();
    //region ACTIONS

    @Lazy
    @Autowired
    private StageManager stageManager;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dutyTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadTopicDetails();
        cornerMenu= new CornerMenu(CornerMenu.Location.TOP_LEFT, this.borderPane, true)
                .withAnimationInterpolation(null)
                .withAutoShowAndHide(true);
        cornerMenu.getItems().addAll(customMenu.addMenuItems());
    }

    private void setColumnProperties(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Duty, Boolean>, TableCell<Duty, Boolean>> cellFactory =
            new Callback<TableColumn<Duty, Boolean>, TableCell<Duty, Boolean>>()
            {
                @Override
                public TableCell<Duty, Boolean> call( final TableColumn<Duty, Boolean> param)
                {
                    return   new TableCell<Duty, Boolean>()
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
                                    Duty duty = getTableView().getItems().get(getIndex());
                                    updateTopic(duty);
                                });

                                btnEdit.setStyle(Constants.TRANSPARENT_BACKGROUND);
                                btnEdit.setGraphic(GraphicsHelper.fixEditImage(getClass()));

                                setGraphic(btnEdit);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }

                        private void updateTopic(Duty duty) {
                            labelDutyId.setText(Long.toString(duty.getId()));
                            name.setText(duty.getName());
                            price.setText(String.valueOf(duty.getPrice()));
                        }
                    };
                }
            };

    //region INITIALIZE

    //endregion

    private void loadTopicDetails(){
        dutyList.clear();
        dutyList.addAll(dutyService.findAll());
        dutyTable.setItems(dutyList);
    }

    //region GETTER
    public String getName() {
        return name.getText();
    }

    public Double getPrice() {
        return Double.valueOf(price.getText());
    }
    //endregion

    //region ADD_AND_EDIT

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void save(ActionEvent event){
        if(labelDutyId.getText() == null || "".equals(labelDutyId.getText())){
            if(!"".equals(getName())){
                Duty duty = new Duty();
                setDutyFields(duty);
                dutyService.save(duty);
                AlertHelper.saveAlert("Temática", " La temática "+ duty.getName()+
                        " con ID "+ duty.getId());
            }
        }else{
            Duty duty = dutyService.find(Long.parseLong(labelDutyId.getText()));
            setDutyFields(duty);
            dutyService.update(duty);
            AlertHelper.updateAlert("Temática", duty.getName());
        }
        clearFields();
        loadTopicDetails();
    }

    private void setDutyFields(Duty duty) {
        duty.setName(getName());
        duty.setPrice(getPrice());
    }

    @FXML
    private void delete(ActionEvent event){
        List<Duty> topics = dutyTable.getSelectionModel().getSelectedItems();
        if(AlertHelper.deleteAlert()) dutyService.deleteInBatch(topics);

        loadTopicDetails();
    }

    //endregion

    private void clearFields() {
        labelDutyId.setText(null);
        name.clear();
        price.clear();
    }
}
