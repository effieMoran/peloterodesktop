package com.pelotero.mp.helper;

import com.pelotero.mp.bean.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by User on 5/12/2017.
 */
public class AlertHelper {

    public static void saveAlert(String className,String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle( className + " ha sido guardado guardado exitosamente.");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void updateAlert(String className, String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(className+" actualizado exitosamente.");
        alert.setHeaderText(null);
        alert.setContentText("El "+ className + " "+ message+" actualizado exitosamente.");
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
}
