package com.pelotero.mp.constants;

/**
 * Created by User on 7/12/2017.
 */
public class Constants {

    public static final String EMAIL_PATTERN = "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";
    public static final String NAME_PATTERN = "[a-zA-Z]+";
    public static final String NUMBER_PATTERN = "[0-9]+";
    public static final String EDIT_BUTTON_URL = "/images/edit.png";
    public static final String TRANSPARENT_BACKGROUND = "-fx-background-color: transparent;";
    public static final String BOOKING_NOT_AVAILABLE ="Lamentamos informarle que la fecha o turno no están disponibles. " +
            "Por favor, intente otra combinación.";

    public static final String EMPTY_KIDS_INVITED = "Debe ingresar la cantidad de niños invitados\n";
    public static final String EMPTY_DATE= "Debe seleccionar una fecha\n";
    public static final String EMPTY_CLIENT= "Debe seleccionar un Cliente.\n";
    public static final String EMPTY_COMBO=  "Debe seleccionar un Combo.\n";
    public static final String EMPTY_DUTY= "Debe seleccionar un Servicio.\n";
    public static final String EMPTY_TOPIC= "Debe seleccionar una Temática.\n";

    public static final int MINIMUN_AGE = 18;


    public static final String BOOKING_CANCELATION_EXPIRED_TITLE = "Tiempo de modificación expirado";
    public static final String BOOKING_CANCELATION_EXPIRED_HEADER = "Las reservas solo se pueden modificar con " +
            "72hs de anticipación";
    public static final String BOOKING_CANCELATION_EXPIRED_MESSAGE = "Lo sentimos, tampoco es posible realizar un " +
            "reintegro";

    public static final String DELETE_WARNING = "¿Está seguro de que desea borrar las filas seleccionados?";


    public static final String BOOKING_WAITING = "En espera";
    public static final String BOOKING_NO_REFUND = "No cancelable";
    public static final String BOOKING_CANCELLED = "Cancelada";
    public static final String BOOKING_FINALIZED = "Finalizada";



}
