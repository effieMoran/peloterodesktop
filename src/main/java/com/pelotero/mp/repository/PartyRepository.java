package com.pelotero.mp.repository;

import com.pelotero.mp.bean.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Created by User on 12/12/2017.
 */
@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    Party findByDateAndTurn(LocalDate date, String turn);
}
