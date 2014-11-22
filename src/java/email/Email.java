package email;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
    private String subject;
    private String body;
    private String from;
    private String to;

    public Email() {
    }

    public Email(String subject, String body, String from, String to) {
        this.subject = subject;
        this.body = body;
        this.from = from;
        this.to = to;
    }
    
    public void send() {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.port", "465");
            props.put("mail.smtps.auth", "true");
            props.put("mail.smtps.quitwait", "false");
            
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
            Message m = new MimeMessage(session);
            
            m.setSubject(subject);
            m.setText(body);
            
            Address fromA = new InternetAddress(from);
            Address toA = new InternetAddress(to);
            
            m.setFrom(fromA);
            m.setRecipient(Message.RecipientType.TO, toA);
            
            Transport transport = session.getTransport();
            transport.connect("szabolcs.remenyi", "mrzfstqjgqwkxpdf");
            transport.sendMessage(m, m.getAllRecipients());
            transport.close();
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
