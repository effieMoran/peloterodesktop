package com.pelotero.mp.controller;

import com.pelotero.mp.bean.Bill;
import com.pelotero.mp.bean.Booking;
import com.pelotero.mp.bean.Client;
import com.pelotero.mp.bean.Combo;
import com.pelotero.mp.bean.Duty;
import com.pelotero.mp.bean.Party;
import com.pelotero.mp.bean.Topic;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.constants.ValidationMessages;
import com.pelotero.mp.helper.AlertHelper;
import com.pelotero.mp.helper.DatePickerHelper;
import com.pelotero.mp.helper.GraphicsHelper;
import com.pelotero.mp.helper.ValidationHelper;
import com.pelotero.mp.service.BillLineService;
import com.pelotero.mp.service.BillService;
import com.pelotero.mp.service.BookingService;
import com.pelotero.mp.service.ClientService;
import com.pelotero.mp.service.ComboService;
import com.pelotero.mp.service.DutyService;
import com.pelotero.mp.service.PartyService;
import com.pelotero.mp.service.TopicService;
import com.pelotero.mp.view.FxmlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import jfxtras.scene.menu.CornerMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by User on 12/12/2017.
 */

@Controller
public class BookingController implements Initializable{

    @Autowired
    BookingService bookingService;

    @Autowired
    PartyService partyService;

    @Autowired
    ComboService comboService;

    @Autowired
    TopicService topicService;

    @Autowired
    DutyService dutyService;

    @Autowired
    ClientService clientService;

    @Autowired
    BillService billService;

    @Autowired
    BillLineService billLineService;

    CornerMenu cornerMenu;

    @Lazy
    @Autowired
    StageManager stageManager;
    @Lazy
    @Autowired
    private CustomMenuController customMenu;
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label bookingId;

    @FXML
    private DatePicker datePicckerParty;

    @FXML
    private RadioButton rbFirstTurn;
    @FXML
    private ToggleGroup gender1;
    @FXML
    private RadioButton rbSecondTurn;

    @FXML
    private ComboBox<Combo> comboBoxCombo;
    @FXML
    private ComboBox<Topic> comboBoxTopic;
    @FXML
    private ComboBox<Duty> comboBoxDuty;
    @FXML
    private ComboBox<Client> comboBoxClient;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private TextField kidName;

    @FXML
    private TextField kidAge;

    @FXML
    private TextField kidsInvited;

    @FXML
    private Button reset;

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, Long> columnBookingId;
    @FXML
    private TableColumn<Booking, Client> columnClient;
    @FXML
    private TableColumn<Booking, LocalDate> columnBookingDate;
    @FXML
    private TableColumn<Booking, LocalDate> columnPartyDate;
    @FXML
    private TableColumn<Booking, String> columnStatus;
    @FXML
    private TableColumn<Booking, String> columnTurn;
    @FXML
    private TableColumn<Booking, Combo> columnCombo;
    @FXML
    private TableColumn<Booking, Topic> columnTopic;
    @FXML
    private TableColumn<Booking, Duty> colService;
    @FXML
    private TableColumn<Booking, String> columnGender;
    @FXML
    private TableColumn<Booking, String> columnName;
    @FXML
    private TableColumn<Booking, Integer> columnAge;
    @FXML
    private TableColumn<Booking, Integer> columnKidsInvited;
    @FXML
    private TableColumn<Booking, Boolean> colEdit;

    public String getGender(){
        return rbMale.isSelected() ? "Niño" : "Niña";
    }
    public String getTurn(){
        return rbSecondTurn.isSelected() ? "18:00" : "14:00";
    }
    private ObservableList<Booking> bookingsList = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        bookingTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadBookingDetails();

        comboBoxCombo.getItems().addAll(comboService.findAll());
        comboBoxClient.getItems().addAll(clientService.findAll());
        comboBoxDuty.getItems().addAll(dutyService.findAll());
        comboBoxTopic.getItems().addAll(topicService.findAll());
        comboBoxTopic.getItems().addAll(topicService.findAll());
        DatePickerHelper.disableRangeDates(datePicckerParty);

        hideFields();

        cornerMenu= new CornerMenu(CornerMenu.Location.TOP_LEFT, borderPane, true)
                .withAnimationInterpolation(null)
                .withAutoShowAndHide(true);
        cornerMenu.getItems().addAll(customMenu.addMenuItems());
    }

    private void setColumnProperties(){

        columnName.setCellValueFactory(new PropertyValueFactory<Booking, String>("kidName"));
        columnAge.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("kidAge"));
        columnGender.setCellValueFactory(new PropertyValueFactory<Booking, String>("kidGender"));

        columnBookingId.setCellValueFactory(new PropertyValueFactory<Booking, Long>("id"));
        columnTurn.setCellValueFactory(new PropertyValueFactory<Booking, String>("turn"));
        columnCombo.setCellValueFactory(new PropertyValueFactory<Booking, Combo>("combo"));
        columnTopic.setCellValueFactory(new PropertyValueFactory<Booking, Topic>("topic"));
        colService.setCellValueFactory(new PropertyValueFactory<Booking, Duty>("duty"));
        columnClient.setCellValueFactory(new PropertyValueFactory<Booking, Client>("client"));
        columnBookingDate.setCellValueFactory(new PropertyValueFactory<Booking, LocalDate>("bookingDate"));
        columnKidsInvited.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("kidsInvited"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<Booking, String>("status"));
        columnPartyDate.setCellValueFactory(new PropertyValueFactory<Booking, LocalDate>("partyDate"));

        colEdit.setCellFactory(cellFactory);
    }

    private void hideFields() {
        columnGender.setVisible(false);
        columnName.setVisible(false);
        columnAge.setVisible(false);
        columnBookingDate.setVisible(false);
        columnKidsInvited.setVisible(false);
        columnStatus.setVisible(false);
    }

    Callback<TableColumn<Booking, Boolean>, TableCell<Booking, Boolean>> cellFactory =
            new Callback<TableColumn<Booking, Boolean>, TableCell<Booking, Boolean>>()
            {
                @Override
                public TableCell<Booking, Boolean> call( final TableColumn<Booking, Boolean> param)
                {
                    return   new TableCell<Booking, Boolean>()
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
                                    Booking booking = getTableView().getItems().get(getIndex());
                                    if(bookingService.editBookingPossible(booking)) {
                                        updateBooking(booking);
                                    }
                                    }
                                );

                                btnEdit.setStyle(Constants.TRANSPARENT_BACKGROUND);
                                btnEdit.setGraphic(GraphicsHelper.fixEditImage(getClass()));
                                setGraphic(btnEdit);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }

                        private void updateBooking(Booking booking) {
                            bookingId.setText(Long.toString(booking.getId()));
                            kidAge.setText(String.valueOf(booking.getKidAge()));
                            kidName.setText(booking.getKidName());
                            kidsInvited.setText(String.valueOf(booking.getKidsInvited()));
                            datePicckerParty.setValue(booking.getPartyDate());

                            if("Niño".equals(booking.getKidGender())) {
                                rbMale.setSelected(true);
                                rbFemale.setSelected(false);
                            }
                            else {
                                rbFemale.setSelected(true);
                                rbMale.setSelected(false);
                            }
                            if("14:00".equals(booking.getTurn())) rbFirstTurn.setSelected(true);
                            else rbSecondTurn.setSelected(true);

                            comboBoxCombo.setValue(booking.getCombo());
                            comboBoxDuty.setValue(booking.getDuty());
                            comboBoxTopic.setValue(booking.getTopic());
                            comboBoxClient.setValue(booking.getClient());
                        }
                    };
                }
            };


    private void loadBookingDetails(){
        bookingsList.clear();

        bookingsList.addAll(bookingService.findAll());
        for(Booking b: bookingsList){
            if(null != b.getPartyDate()) {
                if (b.getPartyDate().isBefore(LocalDate.now())) {
                    b.setStatus(Constants.BOOKING_FINALIZED);
                    bookingService.update(b);
                } else if (LocalDate.now().isBefore(b.getPartyDate().minusDays(3))) {
                    b.setStatus(Constants.BOOKING_NO_REFUND);
                    bookingService.update(b);
                }
            }
        }

        bookingTable.setItems(bookingsList);
    }
    @FXML
    void cancelBooking(ActionEvent event) {
        List<Booking> bookings = bookingTable.getSelectionModel().getSelectedItems();
        for(Booking b: bookings ){
            b.setStatus(Constants.BOOKING_CANCELLED);
            bookingService.update(b);
        }

        loadBookingDetails();
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

    private String fieldValidationMessage = "";
    @FXML
    void saveBooking(ActionEvent event) {

        if (passedFieldValidation()) {
            if (bookingId.getText() == null || "".equals(bookingId.getText())) {

                Booking booking = new Booking();
                setBookingFields(booking);
                Bill bill = billService.generateBill(booking);
                if (AlertHelper.confirmation("Datos de la reserva", "Datos de la fiesta: " + bill,
                        "¿Está seguro de que desea realizar la reserva?")) {
                    partyService.save(booking.getParty());
                    bill.setBillLines(billLineService.saveAll(bill.getBillLines()));
                    bill = billService.save(bill);
                    booking.setBill(bill);
                    booking = bookingService.save(booking);
                    AlertHelper.saveAlert("Reserva guardada", "El identificador de la reserva es " +
                            booking.getId() + ".\n");
                }
            } else {
                Booking booking = bookingService.find(Long.parseLong(bookingId.getText()));
                setBookingFields(booking);
                booking = bookingService.update(booking);
                AlertHelper.saveAlert("Reserva Actualizadacon exito", " ");
            }

            clearFields();
            loadBookingDetails();
        }
        else {
            ValidationHelper.validationFieldAlert(ValidationMessages.TITLE_TOPIC, ValidationMessages.HEADER_NOT_SAVE, fieldValidationMessage);
        }
    }

    private boolean passedFieldValidation() {
        boolean isValid = true;
        if ( null == comboBoxClient.getValue()) {
            fieldValidationMessage += ValidationMessages.CLIENT_NOT_PRESENT;
            isValid = false;
        }
        if (null == datePicckerParty.getPromptText()) {
            fieldValidationMessage += ValidationMessages.DATE_NOT_SELECTED;
        }
        if (!partyService.isPartyAvailableForBooking(datePicckerParty.getValue(), getTurn())) {
            fieldValidationMessage += ValidationMessages.PARTY_NOT_AVAILABLE;
            isValid = false;
        }
        if (null == comboBoxCombo.getValue()) {
            fieldValidationMessage += ValidationMessages.COMBO_NOT_PRESENT;
            isValid = false;
        }

        if (null == comboBoxTopic.getValue()) {
            fieldValidationMessage += ValidationMessages.TOPIC_NOT_PRESENT;
            isValid = false;
        }

        if ("".equals(kidsInvited.getText())) {
            fieldValidationMessage += ValidationMessages.KIDS_INVITED_NOT_PRESENT;
            isValid = false;
        } else if ( !kidsInvited.getText().matches(Constants.NUMBER_PATTERN)) {
            fieldValidationMessage += ValidationMessages.KIDS_NUMBER_NOT_NUMERIC;
            isValid = false;
        }

        if (!"".equals(kidAge.getText()) && !kidAge.getText().matches(Constants.NUMBER_PATTERN)) {
            fieldValidationMessage += ValidationMessages.KID_AGE_NOT_NUMERIC;
            isValid = false;
        }
        return isValid;
    }

    private void clearFields() {
        bookingId.setText(null);
        datePicckerParty.setValue(null);
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        rbSecondTurn.setSelected(true);
        rbFirstTurn.setSelected(false);

        kidAge.setText(null);
        kidsInvited.setText(null);
        kidName.setText(null);

        comboBoxCombo.setValue(null);
        comboBoxClient.setValue(null);
        comboBoxDuty.setValue(null);
        comboBoxTopic.setValue(null);
    }

    private void setBookingFields(Booking booking) {
        if (!"".equals(kidName.getText())) {
            booking.setKidName(kidName.getText());
        }
        if (!"".equals(kidAge.getText())) {
            booking.setKidAge(Integer.valueOf(kidAge.getText()));
        }
        booking.setKidGender(getGender());

        if (!"".equals(kidsInvited.getText())) {
            booking.setKidsInvited(Integer.valueOf(kidsInvited.getText()));
        }

        Party party = new Party(datePicckerParty.getValue());
        party.setTurn(getTurn());
        booking.setParty(party);

        booking.setTurn(getTurn());
        booking.setBookingDate(LocalDate.now());
        booking.setPartyDate(datePicckerParty.getValue());
        booking.setStatus(Constants.BOOKING_WAITING);
        booking.setClient(comboBoxClient.getValue());
        booking.setCombo(comboBoxCombo.getValue());
        booking.setTopic(comboBoxTopic.getValue());
        if (null != comboBoxDuty.getValue()) {
            booking.setDuty(comboBoxDuty.getValue());
        }
    }

}

