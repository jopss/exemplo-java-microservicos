package jopss.exemplo.microserviceproposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroservicePropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePropostaApplication.class, args);
	}

}
