package com.pelotero.mp.service.impl;

import com.pelotero.mp.bean.Topic;
import com.pelotero.mp.repository.TopicRepository;
import com.pelotero.mp.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic save(Topic topic){
        return topicRepository.save(topic);
    }

     public Topic update(Topic topic){
        return topicRepository.save(topic);
     }

    public void delete(Topic topic){
         topicRepository.delete(topic);
    }

    public void delete(Long id) {
        topicRepository.delete(id);
    }

    public void deleteInBatch(List<Topic> topics) {
        topicRepository.deleteInBatch(topics);
    }

    public Topic find(Long id){
        return topicRepository.findById(id);
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }
}
