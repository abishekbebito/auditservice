package com.camunda.auditTrail.service;

import com.camunda.auditTrail.request.CreateInstanceRequest;
import com.camunda.auditTrail.request.SearchTaskRequest;
import com.camunda.auditTrail.response.CreateInstanceResponse;
import com.camunda.auditTrail.response.SearchTaskResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AuditTrialService {


    @Autowired
    private RestClient client;



    @Value("${camunda.client.auth.client-id}")
    private String camundaClientId;

    @Value("${camunda.client.auth.client-secret}")
    private String camundaClientSecret;

    @Value("${camunda.client.cloud.cluster-id}")
    private String clusterId;

    @Value("${camunda.client.cloud.region}")
    private String clusterRegion;

    @Value("${camunda.tasklist-url}")
    private String camundaTasklistUrl;
    @Value("${camunda.zeebe-rest-address}")
    private String zeebeRestAddress;

    private String tasklistAccessToken;
    private String zeebeAccessToken;


    public AuditTrialService(RestClient client) {
        this.client=client;

    }

    @PostConstruct
    public void init(){
        this.tasklistAccessToken =fetchAccessToken("tasklist.camunda.io");
        this.zeebeAccessToken =fetchAccessToken("zeebe.camunda.io");
    }


    public String fetchAccessToken(String audience){

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", camundaClientId);
        requestBody.add("client_secret", camundaClientSecret);
        requestBody.add("audience", audience);
        return client.post()
                .uri("https://login.cloud.camunda.io/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(requestBody)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {})
                .get("access_token").toString();

    }

    public List<SearchTaskResponse> fetchTasks(SearchTaskRequest requestBody) {


        return client.post()
                .uri(camundaTasklistUrl+"v1/tasks/search")
                .header("Authorization","Bearer "+ tasklistAccessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(new ParameterizedTypeReference<List<SearchTaskResponse>>() {}
                );


    }

    public CreateInstanceResponse createProcessInstance(CreateInstanceRequest requestBody){
        return client.post()
                .uri(zeebeRestAddress +"v2/process-instances")
                .header("Authorization","Bearer "+ zeebeAccessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(new ParameterizedTypeReference<CreateInstanceResponse>(){});
    }





}
