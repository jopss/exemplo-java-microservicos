package jopss.exemplo.microserviceproposta.integracao.cpf;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservico-cpf", url = "${microservico.cpf}")
public interface CPFClient {

    @GetMapping("{numero}/validar-tratar")
    String tratarEValidarCPF(@PathVariable("numero") String numero);

}
