package jopss.exemplo.microserviceemail.integracao;

import jopss.exemplo.microserviceemail.excecao.EmailException;
import jopss.exemplo.microserviceemail.rest.dto.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailAdapter {

    private Logger log = LoggerFactory.getLogger(EmailAdapter.class);

    @Value("${mail.user}")
    private String mailUser;

    @Value("${mail.pass}")
    private String mailPass;

    @Value("${mail.smtp.host}")
    private String mailHost;

    @Value("${mail.smtp.auth}")
    private String mailAuth;

    @Value("${mail.smtp.port}")
    private String mailPort;

    @Async
    public void integrar(Email email){
        log.debug("--------------------------");
        log.debug(" INICIANDO ENVIO DE EMAIL");
        log.debug("--------------------------");
        Session session = getSession();
        session.setDebug(true);

        try {
            log.debug(" --> criando enderecos e configuracoes");
            Address[] to = InternetAddress.parse(email.getParaTextual());

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getDe()));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(email.getTitulo());
            message.setText(email.getMensagem());

            log.debug(" --> enviando");
            Transport.send(message);
            log.debug(" --> OK!");
            log.debug("--------------------------");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new EmailException(e);
        }
    }

    private Session getSession() {
        log.debug(" --> instanciando sessao com login");
        Session session = Session.getDefaultInstance(this.getProps(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(mailUser, mailPass);
                    }
                });
        return session;
    }

    private Properties getProps() {
        Properties props = new Properties();
        props.put("mail.smtp.host", mailHost);
        props.put("mail.smtp.auth", mailAuth);
        props.put("mail.smtp.port", mailPort);
        return props;
    }

}
