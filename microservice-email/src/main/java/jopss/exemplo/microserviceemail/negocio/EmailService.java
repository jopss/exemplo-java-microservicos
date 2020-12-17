package jopss.exemplo.microserviceemail.negocio;

import jopss.exemplo.microserviceemail.excecao.EmailException;
import jopss.exemplo.microserviceemail.integracao.EmailAdapter;
import jopss.exemplo.microserviceemail.rest.dto.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${mail.default}")
    private String emailPadrao;

    @Autowired
    private EmailAdapter adapter;

    public void enviarEmail(Email email){
        if(email.getDe() == null || email.getDe().isEmpty()){
            email.setDe(emailPadrao);
        }
        if(email.getPara().isEmpty()){
            throw new EmailException("Lista de emails a enviar vazio");
        }
        this.adapter.integrar(email);
    }

}
