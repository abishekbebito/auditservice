package com.camunda.auditTrail.controller;
import com.camunda.auditTrail.entity.ProcessInstanceEntity;
import com.camunda.auditTrail.entity.TasklistEntity;
import com.camunda.auditTrail.repository.TasklistRepo;
import com.camunda.auditTrail.request.CreateInstanceRequest;
import com.camunda.auditTrail.request.SearchTaskRequest;
import com.camunda.auditTrail.response.CreateInstanceResponse;
import com.camunda.auditTrail.response.SearchTaskResponse;
import com.camunda.auditTrail.security.AuthenticatedUserProvider;
import com.camunda.auditTrail.service.AuditTrialService;
import com.camunda.auditTrail.service.MjutDtoService;
import com.camunda.auditTrail.service.ProcessCounterService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
public class TaskController {

    @Autowired
    AuditTrialService auditTrialService;


    @Autowired
    TasklistRepo tasklistRepo;

    @Autowired
    ProcessCounterService processCounterService;

    @Autowired
    MjutDtoService mjutDtoService;

    @Value("${camunda.tasklist-url}")
    private String camundaTasklistUrl;

    @Value("${camunda.process-definition-id}")
    private String processDefinitionId;

    private String loggedInUser;

    public TaskController(AuthenticatedUserProvider userProvider) {
//        this.loggedInUser= userProvider.getEmail();

        this.loggedInUser="commoncamundaid@gmail.com";
    }


//    @GetMapping("/")
//    public String index(Model model){
//
//        SearchTaskRequest searchTaskRequestBody =SearchTaskRequest
//                .builder()
//                .assignee(loggedInUser)
//                .build();
//        List<SearchTaskResponse> searchTaskResponse =auditTrialService.fetchTasks(searchTaskRequestBody);
//        System.out.println(searchTaskResponse);
//        model.addAttribute("tasks", searchTaskResponse);
//        model.addAttribute("tasklistUrl",camundaTasklistUrl);
//
//        return "index";
//
//    }


    @GetMapping("/getAllRequests")
    public String filterIndex(Model model){
        SearchTaskRequest searchTaskRequestBody =SearchTaskRequest
                .builder()
                .assignee(loggedInUser)
                .build();
        List<SearchTaskResponse> searchTaskResponses =auditTrialService.fetchTasks(searchTaskRequestBody);


        System.out.println(mjutDtoService.prepareMjutDisplayData(searchTaskResponses));
        model.addAttribute("tasks", mjutDtoService.prepareMjutDisplayData(searchTaskResponses));
        model.addAttribute("tasklistUrl",camundaTasklistUrl);

        return "index2";


    }
    @PostMapping("/createRequest")
    public String createProcessInstance() throws  InterruptedException {
        String mjutId=processCounterService.getIncrementedcountMjutId();
        System.out.println("-----"+mjutId+"------");
        CreateInstanceRequest createInstanceRequestBody = CreateInstanceRequest
                .builder()
                .variables(Map.of("loggedInUser",loggedInUser,"mjutId",mjutId))
                .processDefinitionId(processDefinitionId)
                .build();

        CreateInstanceResponse createInstanceResponse=auditTrialService.createProcessInstance(createInstanceRequestBody);//this will create a processinstance by passing the assignee as the requestBody
        String processInstanceKey=createInstanceResponse.getProcessInstanceKey();//key fetch tick
        processCounterService.updateCount(//update processInstancecount in another table
                ProcessInstanceEntity
                        .builder()
                        .mjutId(mjutId)
                        .processInstanceKey(processInstanceKey)
                        .build()
        );
        SearchTaskRequest searchTaskRequestBody = SearchTaskRequest
                .builder()
                .processInstanceKey(processInstanceKey)
                .assignee(loggedInUser)
                .state("CREATED")
                .build();
        System.out.println(searchTaskRequestBody);
        TasklistEntity tasklistEntity= tasklistRepo.save(
                TasklistEntity
                        .builder()
                        .processInstanceKey(processInstanceKey)
                        .mjutId(mjutId)
                        .build()
        );

        Thread.sleep(5000);//processInstance created but task gets late to appear for that this sleep need to configure in future

        String id =auditTrialService
                .fetchTasks(searchTaskRequestBody)
                .stream()
                .findFirst()
                .orElseThrow(()-> new RuntimeException("No task found for the given search criteria!"))
                .getId();//gets the formKey

        tasklistEntity.setFormId(id);
        System.out.println(tasklistEntity);
        tasklistRepo.save(tasklistEntity);


        return "redirect:"+camundaTasklistUrl+ id;
    }


}
