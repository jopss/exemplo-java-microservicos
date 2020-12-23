package jopss.exemplo.microserviceproposta.integracao.cpf;

import feign.FeignException;
import feign.RetryableException;
import jopss.exemplo.microserviceproposta.excecao.CPFException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CPFAdapter {

    @Autowired
    private CPFClient client;

    public String tratarEValidarCPF(String cpf) {
        try {
            return this.client.tratarEValidarCPF(cpf);
        }catch(FeignException.FeignClientException e){
            if(e.status() == 400){
                throw new CPFException("CPF invalido");
            }
            throw e;
        }catch(RetryableException e){
            throw new CPFException("Microservico CPF fora do ar. Tente novamente.");
        }
    }
}
