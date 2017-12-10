package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Duty;
import com.pelotero.mp.repository.DutyRepository;
import com.pelotero.mp.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 10/12/2017.
 */
@Service
public class DutyServiceImp implements DutyService {

    @Autowired
    DutyRepository dutyRepository;

    @Override
    public Duty save(Duty entity) {
        return dutyRepository.save(entity);
    }

    @Override
    public Duty update(Duty entity) {
        return dutyRepository.save(entity);
    }

    @Override
    public void delete(Duty entity) {
        dutyRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        dutyRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<Duty> entities) {
        dutyRepository.deleteInBatch(entities);
    }

    @Override
    public Duty find(Long id) {
        return dutyRepository.findOne(id);
    }

    @Override
    public List<Duty> findAll() {
        return dutyRepository.findAll();
    }
}
