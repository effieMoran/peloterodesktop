package com.pelotero.mp.repository;

import com.pelotero.mp.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 14/12/2017.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
