package jopss.exemplo.microserviceproposta.integracao.email;

import feign.FeignException;
import feign.RetryableException;
import jopss.exemplo.microserviceproposta.excecao.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailAdapter {

    @Autowired
    private EmailClient client;

    public void enviarEmail(String para, String titulo, String mensagem) {
        try {
            this.client.enviarEmail(new EmailAPI(para, titulo, mensagem));
        }catch(FeignException.FeignClientException e){
            if(e.status() == 400){
                throw new EmailException("Email invalido");
            }
            throw e;
        }catch(RetryableException e){
            throw new EmailException("Microservico Email fora do ar. Tente novamente.");
        }
    }
}
