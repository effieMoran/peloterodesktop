package com.pelotero.mp.helper;

import com.pelotero.mp.bean.User;
import com.pelotero.mp.constants.Constants;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 5/12/2017.
 */
public class AlertHelper {

    public static void saveAlert(String title,String message){

        Alert alert = getAlert(title, null, message, Alert.AlertType.INFORMATION);
        alert.showAndWait();
    }

    public static void updateAlert(String title, String message){

        Alert alert = getAlert(title, null, message, Alert.AlertType.INFORMATION);
        alert.showAndWait();
    }

    public static boolean deleteAlert(){
        Alert alert = getAlert("Confirmación", null, Constants.DELETE_WARNING, Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }

    public static void validationAlert(String className, String message){
        Alert alert = getAlert("Error de validación en " + className, null, message, Alert.AlertType.WARNING);
        alert.showAndWait();
    }
    public static  boolean confirmation(String title, String header, String message){
        Alert alert = getAlert(title, header, message, Alert.AlertType.CONFIRMATION);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }

    public static void errorAlert(String title, String header, String message){
        Alert alert = getAlert(title, header, message, Alert.AlertType.ERROR);

        alert.showAndWait();
    }

    private static Alert getAlert(String title, String header, String message, Alert.AlertType error) {
        Alert alert = new Alert(error);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        return alert;
    }
}
