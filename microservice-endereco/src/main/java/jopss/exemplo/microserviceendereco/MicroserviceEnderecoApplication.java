package jopss.exemplo.microserviceendereco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceEnderecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEnderecoApplication.class, args);
	}

}
