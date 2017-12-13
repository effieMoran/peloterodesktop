package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Bill;
import com.pelotero.mp.bean.BillLine;
import com.pelotero.mp.bean.Booking;
import com.pelotero.mp.repository.BillRepository;
import com.pelotero.mp.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by User on 13/12/2017.
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Override
    public Bill save(Bill entity) {
        return billRepository.save(entity);
    }

    @Override
    public Bill update(Bill entity) {
        return billRepository.save(entity);
    }

    @Override
    public void delete(Bill entity) {
        billRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        billRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<Bill> entities) {
        billRepository.deleteInBatch(entities);
    }

    @Override
    public Bill find(Long id) {
        return billRepository.findOne(id);
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill generateBill(Booking booking) {
        Bill bill = new Bill();

        bill.setClient(booking.getClient());
        BillLine billLine = new BillLine(booking.getCombo().getPrice(), "Combo: "+booking.getCombo().toString());
        if(null == bill.getBillLines()){
            bill.setBillLines(new HashSet<BillLine>());
        }

        bill.getBillLines().add(billLine);
        bill.setTotal(bill.getTotal() + billLine.getPrice());

        billLine = new BillLine(booking.getDuty().getPrice(),"Servicio: " +booking.getDuty().toString());
        bill.getBillLines().add(billLine);
        bill.setTotal(bill.getTotal() + billLine.getPrice());

        if(15 >booking.getKidsInvited()){
            int extraKids = booking.getKidsInvited() - 15;
            billLine= new BillLine( extraKids * 100,"Invitados extra: " + extraKids);
            bill.setTotal(bill.getTotal() + billLine.getPrice());
        }
        return bill;
    }
}
