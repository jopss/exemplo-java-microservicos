package jopss.exemplo.microserviceemail.negocio;

import jopss.exemplo.microserviceemail.excecao.EmailException;
import jopss.exemplo.microserviceemail.integracao.EmailAdapter;
import jopss.exemplo.microserviceemail.rest.dto.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class EmailService {

    private Logger log = LoggerFactory.getLogger(EmailService.class);

    @Value("${mail.default}")
    private String emailPadrao;

    @Autowired
    private EmailAdapter adapter;

    @Value("${spring.profiles.active:desenv}")
    private String profileAtivo;

    private static final String ENV_DESENV = "desenv";

    public void enviarEmail(Email email) {
        if (email.getDe() == null || email.getDe().isEmpty()) {
            email.setDe(emailPadrao);
        }
        if (email.getPara().isEmpty()) {
            throw new EmailException("Lista de emails a enviar vazio");
        }
        if (ENV_DESENV.equalsIgnoreCase(profileAtivo)) {
            this.criarArquivoComEmail(email);
        } else {
            this.adapter.integrar(email);
        }
    }

    private void criarArquivoComEmail(Email email) {
        String arquivo = System.getProperty("user.home") + File.separator + "microservico-email_" + (new Date()).getTime() + ".html";
        log.info(" --> ARQUIVANDO EMAIL [para '" + email.getParaTextual() + "', titulo '" + email.getTitulo() + "']: " + arquivo);

        try (FileOutputStream file = new FileOutputStream(arquivo, true);
             OutputStreamWriter stream = new OutputStreamWriter(file, StandardCharsets.UTF_8);
             PrintWriter out = new PrintWriter(stream, true)) {
            out.println(email.getMensagem());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
