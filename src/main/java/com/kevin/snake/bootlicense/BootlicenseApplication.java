package com.kevin.snake.bootlicense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
//@EnableDiscoveryClient
public class BootlicenseApplication {

	public static void main(String[] args) {
		System.setProperty("druid.logType", "log4j2");
        System.setProperty("spring.config.location", "classpath:application.properties");
        SpringApplication.run(BootlicenseApplication.class, args);
	}
}
