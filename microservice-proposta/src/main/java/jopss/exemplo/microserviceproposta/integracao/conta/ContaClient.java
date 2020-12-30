package jopss.exemplo.microserviceproposta.integracao.conta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "microservico-conta", url = "${microservico.conta}")
public interface ContaClient {

    @PostMapping()
    ContaAPI cadastrar(ContaRequisicao conta);

}
