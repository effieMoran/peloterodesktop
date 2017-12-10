package com.pelotero.mp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

/**
 * Created by Eli on 9/12/2017.
 */
@Controller
public class DutyController implements Initializable {

    @FXML
    private Button btnLogout;

    @FXML
    private TableView<?> dutyTable;

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
    private TableColumn<?, ?> columnPrice;

    @FXML
    private Button reset;

    @FXML
    private TableColumn<?, ?> columnPriceId;

    @FXML
    private TableColumn<?, ?> columnEdit;

    @FXML
    private TableColumn<?, ?> columnName;

}
