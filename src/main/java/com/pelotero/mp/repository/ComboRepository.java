package com.pelotero.mp.repository;

import com.pelotero.mp.bean.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Eli on 8/12/2017.
 */
@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {

    Combo findById(long id);
}
