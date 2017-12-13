package com.pelotero.mp.service;

import com.pelotero.mp.bean.Party;
import com.pelotero.mp.generic.GenericService;

import java.time.LocalDate;

/**
 * Created by User on 12/12/2017.
 */
public interface PartyService extends GenericService<Party> {

    boolean isPartyAvailableForBooking(LocalDate date, String turn);
}
