package com.pelotero.mp.controller;

import com.pelotero.mp.Main;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.view.FxmlView;
import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.ClockBuilder;
import eu.hansolo.medusa.FGauge;
import eu.hansolo.medusa.FGaugeBuilder;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.GaugeDesign;
import eu.hansolo.medusa.TimeSection;
import eu.hansolo.medusa.TimeSectionBuilder;
import eu.hansolo.medusa.skins.ClockSkin;
import eu.hansolo.medusa.skins.FlatSkin;
import eu.hansolo.medusa.skins.MinimalClockSkin;
import eu.hansolo.medusa.skins.ModernSkin;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import jfxtras.scene.menu.CornerMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;


import javax.management.monitor.GaugeMonitor;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
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

    Gauge gauge;
    FGauge fGauge;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO: ADD animations and leave method

        cornerMenu= new CornerMenu(CornerMenu.Location.TOP_LEFT, this.borderPane, true)
               .withAnimationInterpolation(null)
               .withAutoShowAndHide(true);
        cornerMenu.getItems().addAll(customMenu.addMenuItems());

        TimeSection timeSection = TimeSectionBuilder.create()
                .start(LocalTime.of(14, 00, 00))
                .stop(LocalTime.of(15, 00, 00))
                .onTimeSectionEntered(event ->
                        System.out.println("Garden light on"))
                .onTimeSectionLeft(event ->
                        System.out.println("Garden light off"))
                .color(Color.rgb(255, 128, 0, 0.5))
                .build();
        Clock clock =  ClockBuilder.create()
                .secondColor(Color.ALICEBLUE)
                .sectionsVisible(true)
                .checkSectionsForValue(true)
                .running(true)
                .build();
        clock.setSkin(new MinimalClockSkin(clock));
        clock.setSecondColor(Color.GREEN);
        clock.setMinuteColor(Color.DARKSALMON);
        //clock.setBackgroundPaint(Paint.valueOf("blue"));
        clock.setKnobColor(Color.GREEN);
        clock.setAlarmColor(Color.GREEN);
        borderPane.setBottom(clock);
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
