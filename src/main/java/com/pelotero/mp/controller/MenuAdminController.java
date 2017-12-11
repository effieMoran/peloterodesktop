package com.pelotero.mp.controller;

import com.pelotero.mp.Main;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.view.FxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jfxtras.scene.menu.CornerMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MenuAdminController implements Initializable {

    @FXML
    BorderPane borderPane;

    CornerMenu cornerMenu;

    @Autowired
    private CustomMenuController customMenu;

    @FXML
    HBox hboxtop;

    //region FXML_CONTROLLS
    @FXML
    public Button buttonLogOut;
    @FXML
    public Button buttonExit;

    //endregion
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO: ADD animations and leave method

        cornerMenu= new CornerMenu(CornerMenu.Location.TOP_LEFT, this.borderPane, true)
               .withAnimationInterpolation(null)
               .withAutoShowAndHide(true);
        cornerMenu.getItems().addAll(customMenu.addMenuItems());

    }




    //region NAVIGATION_BUTTONS

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
