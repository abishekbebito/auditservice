package com.camunda.auditTrail.repository;

import com.camunda.auditTrail.entity.TasklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasklistRepo extends JpaRepository<TasklistEntity,Integer> {

}
