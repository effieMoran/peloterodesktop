package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Product;
import com.pelotero.mp.repository.ProductRepository;
import com.pelotero.mp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 14/12/2017.
 */
@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product update(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public void deleteInBatch(List<Product> entities) {
        productRepository.deleteInBatch(entities);
    }

    @Override
    public Product find(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        return  productRepository.findAll();
    }
}
