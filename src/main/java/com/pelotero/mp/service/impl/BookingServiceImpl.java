package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Booking;
import com.pelotero.mp.constants.Constants;
import com.pelotero.mp.repository.BookingRepository;
import com.pelotero.mp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by User on 12/12/2017.
 */
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Booking save(Booking entity) {
        return bookingRepository.save(entity);
    }

    @Override
    public Booking update(Booking entity) {
        return bookingRepository.save(entity);
    }

    @Override
    public void delete(Booking entity) {
        bookingRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<Booking> entities) {
        bookingRepository.deleteInBatch(entities);
    }

    @Override
    public Booking find(Long id) {
        return bookingRepository.findOne(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public boolean editBookingPossible(Booking booking){

        if(booking.getPartyDate().isAfter(LocalDate.now())){

        }
        if(!Constants.BOOKING_FINALIZED.equals(booking.getStatus())) {

        }

        if (!Constants.BOOKING_CANCELLED.equals(booking.getStatus())) {

        }

        return true;
    }
}
