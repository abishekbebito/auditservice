package com.camunda.auditTrail.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class CreateInstanceResponse {
    private String processDefinitionId;
    private String processInstanceKey;
    private String processDefinitionKey;

}
