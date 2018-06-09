package com.pelotero.mp.service;

import com.pelotero.mp.bean.Booking;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.generic.GenericService;

/**
 * Created by User on 12/12/2017.
 */
public interface BookingService extends GenericService<Booking> {

    public boolean editBookingPossible(Booking booking);
}