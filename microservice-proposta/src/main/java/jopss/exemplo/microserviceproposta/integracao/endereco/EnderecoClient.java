package jopss.exemplo.microserviceproposta.integracao.endereco;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservico-endereco", url = "${microservico.endereco}")
public interface EnderecoClient {

    @GetMapping("{cep}")
    EnderecoAPI buscarCep(@PathVariable("cep") String cep);

}
