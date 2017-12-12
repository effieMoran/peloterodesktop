package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Client;
import com.pelotero.mp.repository.ClientRepository;
import com.pelotero.mp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 11/12/2017.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client save(Client entity) {
        return clientRepository.save(entity);
    }

    @Override
    public Client update(Client entity) {
        return clientRepository.save(entity);
    }

    @Override
    public void delete(Client entity) {
        clientRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<Client> entities) {
        clientRepository.deleteInBatch(entities);
    }

    @Override
    public Client find(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
