package com.camunda.auditTrail.request;

import lombok.*;

import java.util.Map;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
public class CreateInstanceRequest {

    private  String processDefinitionId;
    private Map<String,String> variables;

}
