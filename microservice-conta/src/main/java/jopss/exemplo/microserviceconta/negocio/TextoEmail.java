package jopss.exemplo.microserviceconta.negocio;

import jopss.exemplo.microserviceconta.integracao.email.EmailAPI;
import jopss.exemplo.microserviceconta.negocio.modelo.Conta;
import org.springframework.stereotype.Service;

@Service
public class TextoEmail {

    public EmailAPI criacaoConta(String email, Conta conta){
        StringBuilder str = new StringBuilder();
        str.append("Parabens! Foi criado uma conta nova para você em nosso banco.").append("<br><br>");
        str.append("Banco: " + conta.getBanco()).append("<br>");
        str.append("Agencia: " + conta.getAgencia()).append("<br>");
        str.append("Numero: " + conta.getNumero());

        return new EmailAPI(email,  "Nova Conta", str.toString());
    }

}
