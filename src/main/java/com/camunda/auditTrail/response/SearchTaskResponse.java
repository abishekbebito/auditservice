package com.camunda.auditTrail.response;

import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchTaskResponse {

    private String id;//gives formid that helps to links to tasklist page
    private String name;
    private String assignee;
    private String state;
    private boolean assigned;
    private String[] assignees;
    private String taskDefinitionId;
    private String candidateGroup;
    private String[] candidateGroups;
    private String processDefinitionKey;
    private String processInstanceKey;
    private String implementation;
    private String formKey;
    private String formId;
    private String taskState;

    public SearchTaskResponse(String id, String formKey, String formId, boolean assigned, String assignee, String[] assignees, String candidateGroup, String[] candidateGroups, String implementation, String name, String processDefinitionKey, String processInstanceKey, String state, String taskDefinitionId) {
        this.id=id;
        this.assigned = assigned;
        this.assignee = assignee;
        this.assignees = assignees;
        this.candidateGroup = candidateGroup;
        this.candidateGroups = candidateGroups;
        this.formKey=formKey;
        this.formId=formId;
        this.implementation = implementation;
        this.name = name;
        this.processDefinitionKey = processDefinitionKey;
        this.processInstanceKey = processInstanceKey;
        this.state = state;
        this.taskDefinitionId = taskDefinitionId;
    }




}
