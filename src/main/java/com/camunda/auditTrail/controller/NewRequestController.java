package com.camunda.auditTrail.controller;

import com.camunda.auditTrail.entity.TasklistEntity;
import com.camunda.auditTrail.repository.TasklistRepo;
import com.camunda.auditTrail.response.CreateInstanceResponse;
import com.camunda.auditTrail.service.AuditTrialService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Transactional
public class NewRequestController {
    @Autowired
    AuditTrialService auditTrialService;

    @Value("${camunda.process-definition-id}")
    private String processDefinitionId;;





}
