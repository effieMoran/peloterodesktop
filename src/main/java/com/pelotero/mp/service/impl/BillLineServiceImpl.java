package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.BillLine;
import com.pelotero.mp.repository.BillLineRepository;
import com.pelotero.mp.repository.BillRepository;
import com.pelotero.mp.service.BillLineService;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 13/12/2017.
 */
@Service
public class BillLineServiceImpl implements BillLineService {
    @Autowired
    BillLineRepository billLineRepository;

    @Override
    public BillLine save(BillLine entity) {
        return billLineRepository.save(entity);
    }

    @Override
    public BillLine update(BillLine entity) {
        return billLineRepository.save(entity);
    }

    @Override
    public void delete(BillLine entity) {
        billLineRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        billLineRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<BillLine> entities) {
        billLineRepository.deleteInBatch(entities);
    }

    @Override
    public BillLine find(Long id) {
        return billLineRepository.findOne(id);
    }

    @Override
    public List<BillLine> findAll() {
        return billLineRepository.findAll();
    }

    @Transactional
    @Override
    public Set<BillLine> saveAll(Set<BillLine> billLines) {
        Iterator<BillLine> billLineIterator = billLines.iterator();
        while (billLineIterator.hasNext()){
            billLineRepository.save(billLineIterator.next());
        }
        return billLines;
    }
}
