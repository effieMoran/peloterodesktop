package com.pelotero.mp.repository;

import com.pelotero.mp.bean.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 12/12/2017.
 */
@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
}
