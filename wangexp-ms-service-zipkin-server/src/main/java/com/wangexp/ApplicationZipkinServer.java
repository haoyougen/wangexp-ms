package com.wangexp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

 
@SpringBootApplication
@EnableZipkinStreamServer
public class ApplicationZipkinServer {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationZipkinServer.class, args);
	}
}
