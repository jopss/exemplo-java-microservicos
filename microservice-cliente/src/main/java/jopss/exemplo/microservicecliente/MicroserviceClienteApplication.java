package jopss.exemplo.microservicecliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceClienteApplication.class, args);
	}

}
