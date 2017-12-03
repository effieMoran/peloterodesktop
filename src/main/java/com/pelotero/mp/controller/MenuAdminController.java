package com.pelotero.mp.controller;

import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MenuAdminController implements Initializable {

    //region FXML_CONTROLLS
    @FXML
    public Button buttonLogOut;
    @FXML
    public Button buttonExit;
    @FXML
    public HBox hBox1;
    @FXML
    public VBox vBox1;
    @FXML
    public Button buttonUsers;
    @FXML
    public ImageView imageViewUsers;
    @FXML
    public Label labelUsers;
    @FXML
    public Button buttonSuppliers;
    @FXML
    public Label labelSuppliers;
    @FXML
    public Button buttonReports;
    @FXML
    public Label labelReports;
    @FXML
    public VBox vBox3;
    @FXML
    public Button buttonCombo;
    @FXML
    public Label labelCombos;
    @FXML
    public Button buttonTopic;
    @FXML
    public Label labelTopics;
    @FXML
    public Button buttonServices;
    @FXML
    public Label labelServices;
    @FXML
    public VBox vBox2;
    @FXML
    public Button buttonProducts;
    @FXML
    public Label labelProducts;
    @FXML
    public Button buttonParty;
    @FXML
    public Label labelParties;
    @FXML
    public Button buttonBookings;
    @FXML
    public Label labelBookings;
    @FXML
    public VBox vBox4;
    @FXML
    public Button buttonClients;
    @FXML
    public Label labelClients;
    @FXML
    public Button buttonPayments;
    @FXML
    public Label labelPayments;

    //endregion
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO: ADD animations and leave method
    }

    @FXML
    private void goToUsersMenu(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.USER);
    }

    //region NAVIGATION_BUTTONS

    @FXML
    private void goToSuppliers(ActionEvent event) throws IOException {
    }
    @FXML
    private void goToCombos(ActionEvent event) throws IOException {
    }
    @FXML
    private void goToProperties(ActionEvent event) throws IOException {
    }
    @FXML
    private void goToTopics(ActionEvent event) throws IOException {
    }
    @FXML
    private void goToServices(ActionEvent event) throws IOException {
    }

    @FXML
    private void goToParties(ActionEvent event) throws IOException {
    }
    @FXML
    private void goToBookings(ActionEvent event) throws IOException {
    }
    @FXML
    private void goToClients(ActionEvent event) throws IOException {
    }
    @FXML
    private void goToPayments(ActionEvent event) throws IOException {
    }

    //endregion
}
