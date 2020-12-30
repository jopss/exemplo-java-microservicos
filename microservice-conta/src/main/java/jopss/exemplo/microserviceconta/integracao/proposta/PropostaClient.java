package jopss.exemplo.microserviceconta.integracao.proposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservico-proposta", url = "${microservico.proposta}")
public interface PropostaClient {

    @GetMapping("{id}")
    PropostaAPI buscar(@PathVariable Long id);

}
