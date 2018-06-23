package com.pelotero.mp.helper;

import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  ValidationHelper {

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

    public static void validationFieldAlert(String title, String header, String contentText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
