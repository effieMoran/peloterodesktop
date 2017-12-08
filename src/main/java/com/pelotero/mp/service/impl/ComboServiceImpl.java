package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Combo;
import com.pelotero.mp.repository.ComboRepository;
import com.pelotero.mp.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eli on 8/12/2017.
 */
@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    ComboRepository comboRepository;
    @Override
    public Combo save(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public Combo update(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public void delete(Combo combo) {
        comboRepository.delete(combo);
    }

    @Override
    public void delete(Long id) {
        comboRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<Combo> comboList) {
        comboRepository.deleteInBatch(comboList);
    }

    @Override
    public Combo find(Long id) {
        return comboRepository.findById(id);
    }

    @Override
    public List<Combo> findAll() {
        return comboRepository.findAll();
    }
}
