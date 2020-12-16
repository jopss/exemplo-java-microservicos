package jopss.exemplo.microserviceemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MicroserviceEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEmailApplication.class, args);
	}

}
