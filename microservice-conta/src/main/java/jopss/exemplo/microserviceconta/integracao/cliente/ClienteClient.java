package jopss.exemplo.microserviceconta.integracao.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservico-cliente", url = "${microservico.cliente}")
public interface ClienteClient {

    @GetMapping("{id}")
    ClienteAPI buscar(@PathVariable Long id);

}
