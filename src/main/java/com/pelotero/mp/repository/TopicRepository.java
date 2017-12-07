package com.pelotero.mp.repository;

import com.pelotero.mp.bean.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findById(long id);
}
