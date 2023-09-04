package vn.devsamurai.codingchallenge.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
public class TaskmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

	@Bean
	JsonMessageConverter converter() {
		return new JsonMessageConverter();
	}

	@Bean
//	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
