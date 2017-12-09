package com.pelotero.mp.controller;

import com.pelotero.mp.Main;
import com.pelotero.mp.bean.Combo;
import com.pelotero.mp.bean.Item;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.helper.GraphicsHelper;
import com.pelotero.mp.service.ComboService;
import com.pelotero.mp.service.ItemService;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Eli on 8/12/2017.
 */
@Controller
public class ComboController implements Initializable {
    @FXML
    private Button btnLogout;
    @FXML
    private TextField comboName;
    @FXML
    private TextField newItem;
    @FXML
    private Button buttonExit;
    @FXML
    private MenuItem deleteUsers;
    @FXML
    private Label labelComboId1;
    @FXML
    private ListView<Item> availableItems;
    @FXML
    private Button buttonMenu;

    @FXML
    private Button buttonAddItem;

    @FXML
    private Button addItem;
    @FXML
    private TextField comboPrice;

    @FXML
    private Button buttonRemoveItem;
    @FXML
    private Button reset;
    @FXML
    private Label labelComboId;
    @FXML
    private ListView<?> selectedItems;
    @FXML
    private TableView<Combo> comboTable;
    @FXML
    private TableColumn<Combo, Long> columnComboId;
    @FXML
    private TableColumn<Combo, String> columnName;
    @FXML
    private TableColumn<Combo, Double> columnPrice;
    @FXML
    private TableColumn<Combo, Boolean> columnEdit;
    @FXML
    private TableColumn<Combo, List<Item>> columnItems;
    @FXML
    private Button saveUser;

    private ObservableList<Combo> comboList = FXCollections.observableArrayList();
    ObservableList<Item> itemList = FXCollections.observableArrayList();

    @Autowired
    ComboService comboService;

    @Autowired
    ItemService itemService;

    //region INIT
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //cbRole.setItems(roles);

        comboTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadComboDetails();
        setColumnProperties();

    }

    private  void setColumnProperties() {
        columnComboId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
         //TODO: column items
        availableItems.setItems(itemList);
        columnEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Combo, Boolean>, TableCell<Combo, Boolean>> cellFactory =
            new Callback<TableColumn<Combo, Boolean>, TableCell<Combo, Boolean>>()
            {
                @Override
                public TableCell<Combo, Boolean> call( final TableColumn<Combo, Boolean> param)
                {
                    return new TableCell<Combo, Boolean>()
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
                                    Combo combo = getTableView().getItems().get(getIndex());
                                    updateCombo(combo);
                                });

                                btnEdit.setStyle(Constants.TRANSPARENT_BACKGROUND);
                                btnEdit.setGraphic(GraphicsHelper.fixEditImage(getClass()));

                                setGraphic(btnEdit);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }

                        private void updateCombo(Combo combo) {
                            labelComboId.setText(Long.toString(combo.getId()));
                            comboName.setText(combo.getName());
                            comboPrice.setText(String.valueOf(combo.getPrice()));
                        }

                    };
                }
            };


    private void loadComboDetails() {
        comboList.clear();
        comboList.addAll(comboService.findAll());
        itemList.addAll(itemService.findAll());
        comboTable.setItems(comboList);
    }

    //enregion
    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void selecItem(ActionEvent event) {

    }

    @FXML
    void unselectItem(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {

    }



    @FXML
    void save(ActionEvent event) {

    }

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
        if(Main.isAdmin) {
            stageManager.switchScene(FxmlView.MENUADMIN);
        }
    }

    //endregion
}
