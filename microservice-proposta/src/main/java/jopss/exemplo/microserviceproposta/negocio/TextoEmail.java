package jopss.exemplo.microserviceproposta.negocio;

import jopss.exemplo.microserviceproposta.integracao.email.EmailAPI;
import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import org.springframework.stereotype.Service;

@Service
public class TextoEmail {

    public EmailAPI aceite(Proposta proposta){
        StringBuilder str = new StringBuilder();

        return new EmailAPI(proposta.getCliente().getEmail(),  "Proposta Aceita", "Sua conta foi criada. Acesse para logar.");
    }

    public EmailAPI recusa(Proposta proposta){
        StringBuilder str = new StringBuilder();

        return new EmailAPI(proposta.getCliente().getEmail(),  "Proposta Recusada", "Voce recusou a proposta de conta. Caso mude e opiniao aceita aqui por favor.");
    }
}
