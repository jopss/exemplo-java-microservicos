package jopss.exemplo.microserviceendereco.integracao;

import feign.FeignException;
import feign.RetryableException;
import jopss.exemplo.microserviceendereco.excecao.EnderecoException;
import jopss.exemplo.microserviceendereco.negocio.modelo.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaCepAdapter {

    @Autowired
    private ViaCepClient client;

    @Autowired
    private EnderecoConverter converter;

    public Endereco buscarExterno(String cep) {
        try {
            ViaCep viaCep = this.client.buscarCep(cep);
            return this.converter.viaCepParaEndereco(viaCep);
        }catch(FeignException.FeignClientException e){
            if(e.status() == 400){
                throw new EnderecoException("Cep invalido");
            }
            throw e;
        }catch(RetryableException e){
            throw new EnderecoException("ViaCep fora do ar. Tente novamente.");
        }
    }
}
