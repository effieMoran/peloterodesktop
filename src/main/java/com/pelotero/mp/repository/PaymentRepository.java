package com.pelotero.mp.repository;

import com.pelotero.mp.bean.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 13/12/2017.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
