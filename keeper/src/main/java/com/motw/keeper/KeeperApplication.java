package com.motw.keeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class KeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeeperApplication.class, args);
	}
}
