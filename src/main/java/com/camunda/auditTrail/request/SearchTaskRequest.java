package com.camunda.auditTrail.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchTaskRequest {
    private String state;
    private String assignee;
    private String processInstanceKey;
}
