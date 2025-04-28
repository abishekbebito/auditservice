package com.camunda.auditTrail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MJUTDisplayDTO {
    private String mjutId;
    private String formId;
    private String assignee;
}
