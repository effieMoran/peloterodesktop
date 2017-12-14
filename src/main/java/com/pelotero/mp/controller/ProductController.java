package com.pelotero.mp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ProductController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private ComboBox<?> category;

    @FXML
    private TextField maximumStock;

    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private ComboBox<?> category1;

    @FXML
    private TableView<?> productTable;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnDescription;

    @FXML
    private TableColumn<?, ?> columnBrand;

    @FXML
    private TableColumn<?, ?> columnCategory;

    @FXML
    private TableColumn<?, ?> columnStock;

    @FXML
    private TableColumn<?, ?> columnMaximumStock;

    @FXML
    private TableColumn<?, ?> columnPrice;

    @FXML
    private TableColumn<?, ?> colEdit;

    @FXML
    private TableView<?> productTable1;

    @FXML
    private TableColumn<?, ?> columnId1;

    @FXML
    private TableColumn<?, ?> columnName1;

    @FXML
    private TableColumn<?, ?> columnDescription1;

    @FXML
    private TableColumn<?, ?> columnBrand1;

    @FXML
    private TableColumn<?, ?> columnCategory1;

    @FXML
    private TableColumn<?, ?> columnStock1;

    @FXML
    private TableColumn<?, ?> columnMaximumStock1;

    @FXML
    private TableColumn<?, ?> columnPrice1;

    @FXML
    private TableColumn<?, ?> colEdit1;

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void goBackToMenu(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

}
