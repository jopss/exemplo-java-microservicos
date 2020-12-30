package jopss.exemplo.microserviceproposta.integracao.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "microservico-cliente", url = "${microservico.cliente}")
public interface ClienteClient {

    @PostMapping()
    ClienteAPI cadastrar(ClienteRequisicao cliente);

}
