package jopss.exemplo.microserviceconta.integracao.email;

import jopss.exemplo.microserviceconta.integracao.IntegracaoMicroservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class EmailAdapter extends IntegracaoMicroservices<EmailAPI, Boolean> {

    @Autowired
    private EmailClient client;

    protected Boolean tratar(EmailAPI email) {
        this.client.enviarEmail(email);
        return true;
    }
}
