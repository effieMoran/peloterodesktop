package com.pelotero.mp.repository;

import com.pelotero.mp.bean.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 11/12/2017.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


}
