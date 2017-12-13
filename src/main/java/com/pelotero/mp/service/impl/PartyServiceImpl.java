package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Party;
import com.pelotero.mp.repository.PartyRepository;
import com.pelotero.mp.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by User on 12/12/2017.
 */
@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    PartyRepository partyRepository;

    @Override
    public Party save(Party entity) {
        return partyRepository.save(entity);
    }

    @Override
    public Party update(Party entity) {
        return partyRepository.save(entity);
    }

    @Override
    public void delete(Party entity) {
        partyRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        partyRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<Party> entities) {
        partyRepository.deleteInBatch(entities);
    }

    @Override
    public Party find(Long id) {
        return partyRepository.findOne(id);
    }

    @Override
    public List<Party> findAll() {
        return partyRepository.findAll();
    }

    @Override
    public boolean isPartyAvailableForBooking(LocalDate date, String turn) {
        return null == partyRepository.findByDateAndTurn(date,turn);
    }
}
