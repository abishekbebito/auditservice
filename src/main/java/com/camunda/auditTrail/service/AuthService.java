//package com.camunda.auditTrail.service;
//
//import com.camunda.auditTrail.config.CamundaAuthProperties;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestClient;
//
//import java.util.Map;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private RestClient client;
//
//    private final CamundaAuthProperties authProperties;
//
//    public AuthService(CamundaAuthProperties authProperties) {
//        this.authProperties = authProperties;
//    }
//
//
//    public String fetchAccessToken(String clientName) {
//        CamundaAuthProperties.ClientConfig clientConfig = authProperties.getClients().get(clientName);
//        if (clientConfig == null) {
//            throw new IllegalArgumentException("Client configuration not found for: " + clientName);
//        }
//
//        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
//        requestBody.add("grant_type", "client_credentials");
//        requestBody.add("client_id", clientConfig.getClientId());
//        requestBody.add("client_secret", clientConfig.getClientSecret());
//        requestBody.add("audience", clientConfig.getAudience());
//
//        return client.post()
//                .uri("https://login.cloud.camunda.io/oauth/token")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .body(requestBody)
//                .retrieve()
//                .body(new ParameterizedTypeReference<Map<String, Object>>() {})
//                .get("access_token").toString();
//    }
//
//    public String fetchTasklistAccessToken() {
//        return fetchAccessToken("tasklist");
//    }
//
//    public String fetchZeebeAccessToken() {
//        return fetchAccessToken("zeebe");
//    }
//}