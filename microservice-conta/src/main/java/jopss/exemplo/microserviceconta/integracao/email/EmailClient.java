package jopss.exemplo.microserviceconta.integracao.email;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservico-email", url = "${microservico.email}")
public interface EmailClient {

    @PostMapping()
    EmailAPI enviarEmail (@RequestBody EmailAPI email);

}
