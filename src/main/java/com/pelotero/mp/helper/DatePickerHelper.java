package com.pelotero.mp.helper;

import com.pelotero.mp.constants.Constants;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

/**
 * Created by User on 13/12/2017.
 */

public class DatePickerHelper {

    public static void setCalendarFonOnlyAdults(DatePicker datePicker) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isAfter(LocalDate.now().minusYears(Constants.MINIMUN_AGE))) {
                    setDisable(true);
                }
            }
        });
        datePicker.setValue(LocalDate.now().minusYears(Constants.MINIMUN_AGE));
    }

    public static void disableRangeDates(DatePicker datePicker){
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusDays(29))) {
                    setDisable(true);
                }
            }
        });
    }
}
