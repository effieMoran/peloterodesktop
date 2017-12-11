package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Item;
import com.pelotero.mp.repository.ItemRepository;
import com.pelotero.mp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eli on 8/12/2017.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item update(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public void delete(Long id) {
        itemRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<Item> items) {
        itemRepository.deleteInBatch(items);
    }

    @Override
    public Item find(Long id) {
        return find(id);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findByName(String name){
        return itemRepository.findByName(name);
    }
}
