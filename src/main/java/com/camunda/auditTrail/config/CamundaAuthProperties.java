//package com.camunda.auditTrail.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import java.util.Map;
//
//@Configuration
//@ConfigurationProperties(prefix = "camunda.client.auth")
//public class CamundaAuthProperties {
//
//    private Map<String, ClientConfig> clients;
//
//    public Map<String, ClientConfig> getClients() {
//        return clients;
//    }
//
//    public void setClients(Map<String, ClientConfig> clients) {
//        this.clients = clients;
//    }
//
//    public static class ClientConfig {
//        private String clientId;
//        private String clientSecret;
//        private String audience;
//
//        public String getClientId() {
//            return clientId;
//        }
//
//        public void setClientId(String clientId) {
//            this.clientId = clientId;
//        }
//
//        public String getClientSecret() {
//            return clientSecret;
//        }
//
//        public void setClientSecret(String clientSecret) {
//            this.clientSecret = clientSecret;
//        }
//
//        public String getAudience() {
//            return audience;
//        }
//
//        public void setAudience(String audience) {
//            this.audience = audience;
//        }
//    }
//}
