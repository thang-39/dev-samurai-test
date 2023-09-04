package vn.devsamurai.codingchallenge.taskcreation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TaskcreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskcreationApplication.class, args);
	}

}
