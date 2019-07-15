package com.pranav.microServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MicroServicesCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesCloudConfigApplication.class, args);
	}

}
