package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Payment;
import com.pelotero.mp.repository.PaymentRepository;
import com.pelotero.mp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 13/12/2017.
 */
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment save(Payment entity) {
        return paymentRepository.save(entity);
    }

    @Override
    public Payment update(Payment entity) {
        return paymentRepository.save(entity);
    }

    @Override
    public void delete(Payment entity) {
        paymentRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<Payment> entities) {
        paymentRepository.deleteInBatch(entities);
    }

    @Override
    public Payment find(Long id) {
        return paymentRepository.findOne(id);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}
