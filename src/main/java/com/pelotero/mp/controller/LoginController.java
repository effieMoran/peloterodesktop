package com.pelotero.mp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.pelotero.mp.Main;
import com.pelotero.mp.config.StageManager;
import com.pelotero.mp.service.UserService;
import com.pelotero.mp.view.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@Controller
public class LoginController implements Initializable{

	@FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label lblLogin;
    
    @Autowired
    private UserService userService;
    
    @Lazy
    @Autowired
    private StageManager stageManager;
        
	@FXML
    private void login(ActionEvent event) throws IOException{
    	if(userService.authenticate(getUsername(), getPassword())){
    		Main.setAdmin( userService.isAdmin(getUsername()));

			stageManager.switchScene(FxmlView.MENUADMIN);

    	}else{
    		lblLogin.setText("Login Failed.");
    	}
    }
	
	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TODO: ADD animations and leave method
	}

}
