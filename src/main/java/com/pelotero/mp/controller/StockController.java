package com.pelotero.mp.controller;

import com.pelotero.mp.bean.Product;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.helper.AlertHelper;
import com.pelotero.mp.helper.GraphicsHelper;
import com.pelotero.mp.service.ProductService;
import com.pelotero.mp.view.FxmlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class StockController implements Initializable {

    //region FXML_ATRIBUTTES
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label labelId;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField brand;
    @FXML
    private ComboBox<String> category;
    @FXML
    private TextField stock;
    @FXML
    private TextField maximumStock;
    @FXML
    private TextField price;
    @FXML
    private Button reset;
    @FXML
    private Button save;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Long> columnId;
    @FXML
    private TableColumn<Product, String> columnName;
    @FXML
    private TableColumn<Product, String> columnDescription;
    @FXML
    private TableColumn<Product, String> columnBrand;
    @FXML
    private TableColumn<Product, String> columnCategory;
    @FXML
    private TableColumn<Product, Integer> columnStock;
    @FXML
    private TableColumn<Product, Integer> columnMaximumStock;
    @FXML
    private TableColumn<Product, Double> columnPrice;
    @FXML
    private TableColumn<Product, Boolean> colEdit;
    //endregion

    private CornerMenu cornerMenu;
    private ObservableList<Product> products = FXCollections.observableArrayList();

    @Lazy
    @Autowired
    StageManager stageManager;
    @Autowired
    private CustomMenuController customMenu;
    @Autowired
    private ProductService productService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadDetails();
        setCategories();
        cornerMenu= new CornerMenu(CornerMenu.Location.TOP_LEFT, this.borderPane, true)
                .withAnimationInterpolation(null)
                .withAutoShowAndHide(true);
        cornerMenu.getItems().addAll(customMenu.addMenuItems());
    }

    //region COLUMN_SETTING
    private void setColumnProperties(){
        columnId.setCellValueFactory(new PropertyValueFactory<Product, Long>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        columnStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        columnMaximumStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("maximumStock"));

        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Product, Boolean>, TableCell<Product, Boolean>> cellFactory =
            new Callback<TableColumn<Product, Boolean>, TableCell<Product, Boolean>>()
            {
                @Override
                public TableCell<Product, Boolean> call( final TableColumn<Product, Boolean> param)
                {
                    return   new TableCell<Product, Boolean>()
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
                                    Product product= getTableView().getItems().get(getIndex());
                                    updateProduct(product);
                                });

                                btnEdit.setStyle(Constants.TRANSPARENT_BACKGROUND);
                                btnEdit.setGraphic(GraphicsHelper.fixEditImage(getClass()));

                                setGraphic(btnEdit);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }

                        private void updateProduct(Product product) {
                            labelId.setText(String.valueOf(product.getId()));
                            price.setText(String.valueOf(product.getPrice()));
                            stock.setText(String.valueOf(product.getStock()));
                            maximumStock.setText(String.valueOf(product.getMaximumStock()));

                            category.setValue(product.getCategory());
                            name.setText(product.getName());
                            brand.setText(product.getBrand());
                        }
                    };
                }
            };


    public void loadDetails(){
        products.clear();
        products.addAll(productService.findAll());
        productTable.setItems(products);
    }
    //endregion

    private void setCategories(){
        category.getItems().addAll(Constants.GIFTS, Constants.TOYS, Constants.FOOD, Constants.DRINKS,
                Constants.COTILLON);
    }
    private void clearFields(){
        labelId.setText(null);
        name.setText(null);
        brand.setText(null);
        stock.setText(null);
        description.setText(null);
        maximumStock.setText(null);
        price.setText(null);
        category.setValue(null);
    }

    //region FXML_METHODS_REGION
    @FXML
    void delete(ActionEvent event) {
        List<Product> products = productTable.getSelectionModel().getSelectedItems();
        if(AlertHelper.deleteAlert()) productService.deleteInBatch(products);
        loadDetails();
    }
    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    void goBackToMenu(ActionEvent event) {
        stageManager.switchScene(FxmlView.MENUADMIN);
    }
    @FXML
    void logout(ActionEvent event) {
        stageManager.switchScene(FxmlView.LOGIN);
    }
    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }
    @FXML
    void save(ActionEvent event) {
        //Todo: add validations
        if(null == labelId.getText() || "".equals(labelId.getText())){
            Product product = new Product();
            setFields(product);
            product = productService.save(product);
            AlertHelper.saveAlert(Constants.PRODUCT_SAVED_TITLE, Constants.PRODUCT_SAVER_MESSAGE);
        }
        else {
            Product product = productService.find(Long.valueOf(labelId.getText()));
            setFields(product);
            product = productService.update(product);
            AlertHelper.updateAlert(Constants.PRODUCT_SAVED_TITLE, Constants.PRODUCT_MODIFIED_MESSAGE);

        }
        clearFields();
        loadDetails();
    }
    private void setFields(Product product){
        product.setName(name.getText());
        product.setBrand(brand.getText());
        product.setCategory(category.getValue());
        product.setStock(Integer.valueOf(stock.getText()));
        product.setPrice(Double.valueOf(price.getText()));
        product.setMaximumStock(Integer.valueOf(maximumStock.getText()));
        product.setDescription(description.getText());
    }
    //endregion
}
