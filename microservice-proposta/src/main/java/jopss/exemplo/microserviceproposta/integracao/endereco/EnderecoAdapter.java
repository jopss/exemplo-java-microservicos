package jopss.exemplo.microserviceproposta.integracao.endereco;

import feign.FeignException;
import feign.RetryableException;
import jopss.exemplo.microserviceproposta.excecao.EnderecoException;
import jopss.exemplo.microserviceproposta.negocio.modelo.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoAdapter {

    @Autowired
    private EnderecoClient client;

    @Autowired
    private EnderecoConverter converter;

    public Endereco validarETratarEndereco(String cep) {
        try {
            EnderecoAPI api = this.client.buscarCep(cep);
            return this.converter.enderecoAPIParaModelo(api);
        }catch(FeignException.FeignClientException e){
            if(e.status() == 400){
                throw new EnderecoException("Cep invalido");
            }
            throw e;
        }catch(RetryableException e){
            throw new EnderecoException("Microservico Endereco fora do ar. Tente novamente.");
        }
    }
}
