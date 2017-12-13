package com.pelotero.mp.controller;

import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10/12/2017.
 */
@Controller
public class CustomMenuController
{
    @Lazy
    @Autowired
    StageManager stageManager;
    private static final String COMBO_IMAGE_URL = "/images/icons/basket.png";
    private static final String USERS_IMAGE_URL = "/images/icons/003-man-1.png";
    private static final String PROVIDERS_IMAGE_URL = "/images/icons/delivery-truck.png";
    private static final String REPORTS_IMAGE_URL = "/images/icons/graph.png";
    private static final String TOPICS_IMAGE_URL = "/images/icons/bee_512px.png";
    private static final String SERVICES_IMAGE_URL = "/images/icons/old_photo_camera_512px.png";
    private static final String PARTY_IMAGE_URL = "/images/icons/giftbox.png";
    private static final String PRODUCTS_IMAGE_URL = "/images/icons/barcode-scanning.png";
    private static final String BOOKINGS_IMAGE_URL = "/images/icons/checklist.png";
    private static final String CLIENTS_IMAGE_URL = "/images/icons/002-people.png";
    private static final String PAYMENTS_IMAGE_URL = "/images/icons/money-pig.png";

    private static final int PREF_WIDTH = 57;
    private static final int PREF_HEIGHT = 57;

    private static ImageView imageViewCombo = new ImageView(new Image(COMBO_IMAGE_URL));
    private static ImageView imageViewUsers = new ImageView(new Image(USERS_IMAGE_URL));
    private static ImageView imageViewProviders = new ImageView(new Image(PROVIDERS_IMAGE_URL));
    private static ImageView imageViewReports = new ImageView(new Image(REPORTS_IMAGE_URL));
    private static ImageView imageViewTopics = new ImageView(new Image(TOPICS_IMAGE_URL));
    private static ImageView imageViewServices = new ImageView(new Image(SERVICES_IMAGE_URL));
    private static ImageView imageViewParty = new ImageView(new Image(PARTY_IMAGE_URL));
    private static ImageView imageViewBookings = new ImageView(new Image(BOOKINGS_IMAGE_URL));
    private static ImageView imageViewClients = new ImageView(new Image(CLIENTS_IMAGE_URL));
    private static ImageView imageViewProducts = new ImageView(new Image(PRODUCTS_IMAGE_URL));
    private static ImageView imageViewPayments = new ImageView(new Image(PAYMENTS_IMAGE_URL));


    public List<MenuItem> addMenuItems(){

        imageViewCombo.setFitHeight(PREF_HEIGHT);
        imageViewCombo.setFitWidth(PREF_WIDTH);

        imageViewProviders.setFitHeight(PREF_HEIGHT);
        imageViewProviders.setFitWidth(PREF_WIDTH);

        imageViewReports.setFitHeight(PREF_HEIGHT);
        imageViewReports.setFitWidth(PREF_WIDTH);

        imageViewServices.setFitHeight(PREF_HEIGHT);
        imageViewServices.setFitWidth(PREF_WIDTH);

        imageViewTopics.setFitHeight(PREF_HEIGHT);
        imageViewTopics.setFitWidth(PREF_WIDTH);

        imageViewUsers.setFitHeight(PREF_HEIGHT);
        imageViewUsers.setFitWidth(PREF_WIDTH);

        imageViewParty.setFitHeight(PREF_HEIGHT);
        imageViewParty.setFitWidth(PREF_WIDTH);

        imageViewBookings.setFitHeight(PREF_HEIGHT);
        imageViewBookings.setFitWidth(PREF_WIDTH);

        imageViewClients.setFitHeight(PREF_HEIGHT);
        imageViewClients.setFitWidth(PREF_WIDTH);

        imageViewProducts.setFitHeight(PREF_HEIGHT);
        imageViewProducts.setFitWidth(PREF_WIDTH);

        imageViewPayments.setFitHeight(PREF_HEIGHT);
        imageViewPayments.setFitWidth(PREF_WIDTH);

        MenuItem menuItemProviders = registerActionProviders(new MenuItem("Proveedores",imageViewProviders ));
        MenuItem menuItemCombos = registerActionCombos(new MenuItem("Combos",imageViewCombo ));
        MenuItem menuItemReports = registerAction(new MenuItem("Reportes",imageViewReports ));
        MenuItem menuItemServices = registerActionServices(new MenuItem("Servicios",imageViewServices ));
        MenuItem menuItemTopics = registerActionTopic(new MenuItem("Temática",imageViewTopics ));
        MenuItem menuItemUsers = registerActionUsers(new MenuItem("Usuarios",imageViewUsers ));

        MenuItem menuItemParty = registerAction(new MenuItem("Fiesta",imageViewParty ));
        MenuItem menuItemBookings = registerActionBookings(new MenuItem("Reservas",imageViewBookings ));
        MenuItem menuItemClients = registerActionClients(new MenuItem("Clientes",imageViewClients ));
        MenuItem menuItemProducts = registerAction(new MenuItem("Productos",imageViewProducts ));
        MenuItem menuItemPayments = registerAction(new MenuItem("Pagos",imageViewPayments ));


        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(menuItemProviders);
        menuItemList.add(menuItemCombos);
        menuItemList.add(menuItemReports);
        menuItemList.add(menuItemServices);
        menuItemList.add(menuItemTopics);
        menuItemList.add(menuItemUsers);

        menuItemList.add(menuItemParty);
        menuItemList.add(menuItemBookings);
        menuItemList.add(menuItemProducts);
        menuItemList.add(menuItemClients);
        menuItemList.add(menuItemPayments);

        return menuItemList;
    }

    private  MenuItem registerAction(MenuItem menuItem) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {
                //TODO:ACTION FOR THE MENU
            }
        });
        return menuItem;
    }

    private MenuItem registerActionProviders(MenuItem menuItem) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                //TODO:ACTION FOR PROVIDERS
                //stageManager.switchScene(FxmlView.);
            }
        });
        return menuItem;
    }
    private MenuItem registerActionCombos(MenuItem menuItem) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {
                stageManager.switchScene(FxmlView.COMBO);
            }
        });
        return menuItem;
    }

    private MenuItem registerActionServices(MenuItem menuItem) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {
                stageManager.switchScene(FxmlView.DUTY);
            }
        });
        return menuItem;
    }

    private MenuItem registerActionTopic(MenuItem menuItem) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stageManager.switchScene(FxmlView.TOPIC);
            }
        });
        return menuItem;
    }

    private MenuItem registerActionUsers(MenuItem menuItem) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stageManager.switchScene(FxmlView.USER);
            }
        });
        return menuItem;
    }

    private MenuItem registerActionClients(MenuItem menuItem) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stageManager.switchScene(FxmlView.CLIENT);
            }
        });
        return menuItem;
    }

    private MenuItem registerActionBookings(MenuItem menuItem) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stageManager.switchScene(FxmlView.BOOKING);
            }
        });
        return menuItem;
    }
}