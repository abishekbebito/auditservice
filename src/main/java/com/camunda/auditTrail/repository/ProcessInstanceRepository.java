package com.camunda.auditTrail.repository;

import com.camunda.auditTrail.entity.ProcessInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessInstanceRepository extends JpaRepository<ProcessInstanceEntity, Long> {
    ProcessInstanceEntity findByprocessInstanceKey(String processInstanceKey);
}