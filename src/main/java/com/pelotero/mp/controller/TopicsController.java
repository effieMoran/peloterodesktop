package com.pelotero.mp.controller;

import com.pelotero.mp.Main;
import com.pelotero.mp.bean.Topic;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.constants.ValidationMessages;
import com.pelotero.mp.helper.AlertHelper;
import com.pelotero.mp.helper.GraphicsHelper;
import com.pelotero.mp.helper.ValidationHelper;
import com.pelotero.mp.service.TopicService;
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
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class TopicsController implements Initializable {
    @FXML
    BorderPane borderPane;
    CornerMenu cornerMenu;
    @Autowired
    private CustomMenuController customMenu;
    @FXML
    public Label labelTopicId;
    @FXML
    public Button reset;
    @FXML
    public Button saveUser;
    @FXML
    public TableView<Topic>  topicTable;
    @FXML
    private TableColumn<Topic, Long> columnTopicId;
    @FXML
    private TableColumn<Topic, String> columnName;
    @FXML
    private TableColumn<Topic, String> columnDescription;
    @FXML
    public TableColumn<Topic, Boolean> columnEdit;
    @FXML
    public MenuItem delete;
    @FXML
    public Button buttonExit;
    @FXML
    public Button buttonLogout;
    @FXML
    public Button buttonMenu;
    @FXML
    public TextField name;
    @FXML
    public TextField description;

    @Autowired
    private TopicService topicService;

    private ObservableList<Topic> topicList = FXCollections.observableArrayList();
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        topicTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadTopicDetails();
        cornerMenu= new CornerMenu(CornerMenu.Location.TOP_LEFT, this.borderPane, true)
                .withAnimationInterpolation(null)
                .withAutoShowAndHide(true);
        cornerMenu.getItems().addAll(customMenu.addMenuItems());
    }

    private void setColumnProperties(){
        columnTopicId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Topic, Boolean>, TableCell<Topic, Boolean>> cellFactory =
            new Callback<TableColumn<Topic, Boolean>, TableCell<Topic, Boolean>>()
            {
                @Override
                public TableCell<Topic, Boolean> call( final TableColumn<Topic, Boolean> param)
                {
                    return   new TableCell<Topic, Boolean>()
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
                                    Topic topic = getTableView().getItems().get(getIndex());
                                    updateTopic(topic);
                                });

                                btnEdit.setStyle(Constants.TRANSPARENT_BACKGROUND);
                                btnEdit.setGraphic(GraphicsHelper.fixEditImage(getClass()));

                                setGraphic(btnEdit);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }

                        private void updateTopic(Topic topic) {
                            labelTopicId.setText(Long.toString(topic.getId()));
                            name.setText(topic.getName());
                            description.setText(topic.getDescription());
                        }
                    };
                }
            };

    //region INITIALIZE

    //endregion

    private void loadTopicDetails(){
        topicList.clear();
        topicList.addAll(topicService.findAll());
        topicTable.setItems(topicList);
    }

    //region GETTER
    public String getName() {
        return name.getText();
    }

    public String getDescription() {
        return description.getText();
    }
    //endregion

    //region ADD_AND_EDIT

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void save(ActionEvent event){
        submitForm(name);
        clearFields();
        loadTopicDetails();
    }

    public void submitForm(TextField textField) {
        if(labelTopicId.getText() == null || "".equals(labelTopicId.getText())){

            if(!"".equals(getName())){
                Topic topic = new Topic();
                setTopicFields(topic);
                topicService.save(topic);
                AlertHelper.saveAlert("Temática", " La temática "+ topic.getName()+
                        " con ID "+ topic.getId());
            }
            else {
                validateInput();
            }
        }else{
            if(!"".equals(getName())){
                Topic topic = topicService.find(Long.parseLong(labelTopicId.getText()));
                setTopicFields(topic);
                topicService.update(topic);
                AlertHelper.updateAlert("Temática", topic.getName());
            }
            else {
                validateInput();
            }
        }
    }

    private void validateInput() {
        ValidationHelper.validationFieldAlert(ValidationMessages.TITLE_BOOKING, ValidationMessages.HEADER_BOOKING, ValidationMessages.CONTENT_TEXT_TOPIC);
    }

    private void setTopicFields(Topic topic) {
        topic.setName(getName());
        topic.setDescription(getDescription());
    }

    @FXML
    private void delete(ActionEvent event){
        List<Topic> topics = topicTable.getSelectionModel().getSelectedItems();
        if(AlertHelper.deleteAlert()) topicService.deleteInBatch(topics);

        loadTopicDetails();
    }

    //endregion

    private void clearFields() {
        labelTopicId.setText(null);
        name.clear();
        description.clear();
    }
}
