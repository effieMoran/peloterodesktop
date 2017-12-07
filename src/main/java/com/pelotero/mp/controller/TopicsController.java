package com.pelotero.mp.controller;

import com.pelotero.mp.Main;
import com.pelotero.mp.bean.Topic;
import com.pelotero.mp.bean.User;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.helper.AlertHelper;
import com.pelotero.mp.helper.ValidationHelper;
import com.pelotero.mp.service.TopicService;
import com.pelotero.mp.view.FxmlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class TopicsController {
    @FXML
    public Label labelTopicId;
    @FXML
    public TableColumn topicId;
    @FXML
    public Button reset;
    @FXML
    public Button saveUser;
    @FXML
    public TableView  topicTable;
    @FXML
    public TableColumn columnEdit;
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
        if(Main.isAdmin) {
            stageManager.switchScene(FxmlView.MENUADMIN);
        }
    }

    //endregion

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
        if(labelTopicId.getText() == null || "".equals(labelTopicId.getText())){
            if(!"".equals(getName())){
                Topic topic = new Topic();
                setTopicFields(topic);
                topicService.save(topic);
                AlertHelper.saveAlert("Temática", " La temática "+ topic.getName()+
                        " con ID "+ topic.getId());
            }
        }else{
            Topic topic = topicService.find(Long.parseLong(topicId.getText()));
            setTopicFields(topic);
            topicService.update(topic);
            AlertHelper.updateAlert("Temática", topic.getName());
        }
    }

    private void setTopicFields(Topic topic) {
        topic.setName(getName());
        topic.setDescription(getDescription());
    }

    @FXML
    private void delete(ActionEvent event){
        List<Topic> topics = topicTable.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get() == ButtonType.OK) topicService.deleteInBatch(topics);

        loadTopicDetails();
    }

    //endregion

    private void clearFields() {
        name.clear();
        description.clear();
    }
}
