package com.pelotero.mp.service;

import com.pelotero.mp.bean.Bill;
import com.pelotero.mp.bean.Booking;
import com.pelotero.mp.generic.GenericService;

/**
 * Created by User on 13/12/2017.
 */
public interface BillService extends GenericService<Bill> {

    Bill generateBill(Booking booking);
}
