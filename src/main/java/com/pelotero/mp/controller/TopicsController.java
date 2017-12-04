package com.pelotero.mp.controller;

import com.pelotero.mp.Main;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.view.FxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

public class TopicsController {
    @FXML
    public Label userId;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public Button reset;
    @FXML
    public Button saveUser;
    @FXML
    public TableView userTable;
    @FXML
    public TableColumn colUserId;
    @FXML
    public TableColumn colFirstName;
    @FXML
    public TableColumn colLastName;
    @FXML
    public TableColumn colEdit;
    @FXML
    public MenuItem deleteUsers;
    @FXML
    public Button buttonExit;
    @FXML
    public Button btnLogout;
    @FXML
    public Button buttonMenu;

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
