package com.camunda.auditTrail;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuditTrailApplication {

	@Autowired
    public ZeebeClient zeebeClient;

	public static void main(String[] args) {
		SpringApplication.run(AuditTrailApplication.class, args);

	}



}
