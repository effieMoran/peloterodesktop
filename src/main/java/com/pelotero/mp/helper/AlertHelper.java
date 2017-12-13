package com.pelotero.mp.helper;

import com.pelotero.mp.bean.User;
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

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle( title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void updateAlert(String title, String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean deleteAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea borrar los seleccionados?");
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }

    public static boolean validate(String field, String value, String pattern){
        if(!value.isEmpty()){
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if(m.find() && m.group().equals(value)){
                return true;
            }else{
                validationAlert(field, false);
                return false;
            }
        }else{
            validationAlert(field, true);
            return false;
        }
    }

    public static boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
            validationAlert(field, true);
            return false;
        }
    }

    public static void validationAlert(String field, boolean empty){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error de validación");
        alert.setHeaderText(null);
        if(field.equals("Rol")) alert.setContentText("Por favor seleccione "+ field);
        else{
            if(empty) alert.setContentText("Por favor ingrese "+ field);
            else alert.setContentText("Por favor ingrese "+ field);
        }
        alert.showAndWait();
    }
}
