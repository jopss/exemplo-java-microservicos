package jopss.exemplo.microserviceemail.integracao;

import jopss.exemplo.microserviceemail.excecao.EmailException;
import jopss.exemplo.microserviceemail.rest.dto.Email;
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

    @Async
    public void integrar(Email email){
        Session session = getSession();
        session.setDebug(true);

        try {
            Address[] to = InternetAddress.parse(email.getParaTextual());

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getDe()));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(email.getTitulo());
            message.setText(email.getMensagem());

            Transport.send(message);

        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }

    private Session getSession() {
        Session session = Session.getDefaultInstance(this.getProps(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("seuemail@gmail.com", "suasenha123");
                    }
                });
        return session;
    }

    private Properties getProps() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        return props;
    }

}
