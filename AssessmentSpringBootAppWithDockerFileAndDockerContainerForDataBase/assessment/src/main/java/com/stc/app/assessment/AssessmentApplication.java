package com.stc.app.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.stc.app.assessment.model"})
public class AssessmentApplication {

	private static ConfigurableApplicationContext applicationContext;
	public static void main(String[] args) {
		AssessmentApplication.applicationContext = SpringApplication.run(AssessmentApplication.class, args);
	}

}
