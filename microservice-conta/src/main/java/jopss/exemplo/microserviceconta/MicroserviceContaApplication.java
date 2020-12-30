package jopss.exemplo.microserviceconta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceContaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceContaApplication.class, args);
	}

}
