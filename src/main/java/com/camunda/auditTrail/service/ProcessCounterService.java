package com.camunda.auditTrail.service;


import com.camunda.auditTrail.entity.ProcessInstanceEntity;
import com.camunda.auditTrail.repository.ProcessInstanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessCounterService {

    @Autowired
    private ProcessInstanceRepository repository;

    @Transactional
    public String getIncrementedcountMjutId() {
        String newMjut="mjutId-"+(repository.count()+1);
        return newMjut;
    }

    @Transactional
    public ProcessInstanceEntity updateCount(ProcessInstanceEntity processInstanceEntity){
         return repository.save(
                 processInstanceEntity
        );
    }
}
