package com.pelotero.mp.repository;

import com.pelotero.mp.bean.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Eli on 9/12/2017.
 */
@Repository
public interface DutyRepository extends JpaRepository<Duty, Long> {
}
