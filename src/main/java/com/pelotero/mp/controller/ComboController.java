package com.pelotero.mp.controller;

import com.pelotero.mp.Main;
import com.pelotero.mp.bean.Combo;
import com.pelotero.mp.bean.Item;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.helper.AlertHelper;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
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
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Eli on 8/12/2017.
 */
@Controller
public class ComboController implements Initializable {
    @FXML
    BorderPane borderPane;

    CornerMenu cornerMenu;

    @Autowired
    private CustomMenuController customMenu;
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
    private ListView<Item> selectedItems;
    public static final ObservableList items = FXCollections.observableArrayList();
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
    private TableColumn<Combo, Boolean> columnItems;
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
        comboTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        loadComboDetails();
        setColumnProperties();
        cornerMenu= new CornerMenu(CornerMenu.Location.TOP_LEFT, this.borderPane, true)
                .withAnimationInterpolation(null)
                .withAutoShowAndHide(true);
        cornerMenu.getItems().addAll(customMenu.addMenuItems());
    }

    private  void setColumnProperties() {
        columnComboId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
         //TODO: column items
        columnItems.setCellFactory(cellFactoryItems);
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

    Callback<TableColumn<Combo, Boolean>, TableCell<Combo, Boolean>> cellFactoryItems =
            new Callback<TableColumn<Combo, Boolean>, TableCell<Combo, Boolean>>()
            {
                @Override
                public TableCell<Combo, Boolean> call( final TableColumn<Combo, Boolean> param)
                {
                    return new TableCell<Combo, Boolean>()
                    {
                        MenuButton menuButton= new MenuButton();

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
                                menuButton = new MenuButton();
                                menuButton.setOnAction(e ->{
                                    /*
                                            Combo combo = getTableView().getItems().get(getIndex());

                                            List<MenuItem> menuItems = menuButton.getItems();
                                            Iterator<MenuItem> menuItemIterator = menuItems.iterator();
                                            while (menuItemIterator.hasNext()){
                                                MenuItem itemForDelete = menuItemIterator.next();
                                                ContextMenu contextMenu = new ContextMenu();
                                                MenuItem deleteItem = new MenuItem("Borrar");
                                                contextMenu.getItems().add(deleteItem);
                                                deleteItem.setOnAction( a ->{
                                                    if(AlertHelper.deleteAlert()){
                                                        menuItems.remove(itemForDelete);
                                                        List<Item> itemsInCombo =combo.getItems();

                                                    }
                                                });
                                                contextMenu.getItems().add(deleteItem);
                                            }
                                    */
                                }
                                );

                                Combo combo = getTableView().getItems().get(getIndex());
                                List<Item> comboItemList = combo.getItems();
                                Iterator<Item> it = comboItemList.iterator();
                                while (it.hasNext()){
                                    //TODO: CHECK FOR DUPLICATES
                                    MenuItem menuItem = new MenuItem(it.next().toString());
                                    menuButton.getItems().add(menuItem);
                                }
                                setAlignment(Pos.CENTER);
                                menuButton.setText("Contenido");
                                setGraphic(menuButton);
                            }
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
        List<Combo> combos = comboTable.getSelectionModel().getSelectedItems();
        if(AlertHelper.deleteAlert()) comboService.deleteInBatch(combos);
        loadComboDetails();
    }

    @FXML
    void selecItem(ActionEvent event) {

    }

    @FXML
    void unselectItem(ActionEvent event) {

    }

    @FXML
    void addItemToList(ActionEvent event) {
        Item item = new Item(newItem.getText());
        items.add(item);
        selectedItems.setItems(items);
        newItem.clear();
    }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
    void deleteItem(ActionEvent event) {
        Item item = (Item) event.getSource();
        items.remove(item);
        clearFields();
    }

    @FXML
    void save(ActionEvent event) {
        if(labelComboId.getText() == null || "".equals(labelComboId.getText())){
            if (comboIsValid()) {
                Combo combo = new Combo();
                setComboFields(combo);
                combo = comboService.save(combo);
                //TODO: Mejorar alerts
                AlertHelper.saveAlert("Combo", "El combo " + combo.getName());
            }
        }else{
            if (comboIsValid()) {
                Combo combo = comboService.find(Long.parseLong(labelComboId.getText()));
                setComboFields(combo);
                combo = comboService.update(combo);
                AlertHelper.updateAlert("Usuario", combo.getName() + " " + combo.getPrice());
            }
        }

        clearFields();
        loadComboDetails();
    }

    private boolean comboIsValid() {
        if ("".equals(comboName.getText())) {
            return false;
        }
        if (!comboPrice.getText().matches(Constants.NUMBER_PATTERN)) {
           return false;
        }
        if ( selectedItems.getItems().isEmpty()) {
            return false;
        }
        if ("".equals(comboPrice.getText())) {
            return false;
        }
        return true;
    }
    String getName(){
        return comboName.getText();
    }
    private void setComboFields(Combo combo) {

        combo.setName(comboName.getText());
        combo.setPrice(Double.valueOf(comboPrice.getText()));
        //TODO: SET ITEMS LIST
    }

    private void clearFields() {
        labelComboId.setText(null);
        comboName.clear();
        comboPrice.clear();
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
        if(Main.isAdmin()) {
            stageManager.switchScene(FxmlView.MENUADMIN);
        }
    }

    //endregion
}
