/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.email;

import equipo2_crudapp_ciphering.CipheringManager;

import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Class to manage the sending of emails to the users of the app.
 *
 * @author iker lopez carrillo
 */
public class Email {

    private static final Logger LOGGER = Logger.getLogger("pspcifrado.email.Email");

    private static final String sender = "noreply.ofertapps";
    private String password;

    private String smtp_host = null;
    private int smtp_port = 0;

    private static final String DEFAULT_SMTP_HOST = "smtp.gmail.com";
    private static final int DEFAULT_SMTP_PORT = 25;

    /**
     * Empty constructor for Email. It sets the host and port to the default.It
     * uses the class CipheringManager to retrieve the password of the account
     * from the file credentials.dat and deciphers it.
     */
    public Email() {
        this.smtp_host = DEFAULT_SMTP_HOST;
        this.smtp_port = DEFAULT_SMTP_PORT;

        this.password = new String(CipheringManager.decipherText(CipheringManager.fileReader("credentials.dat")));
    }

    /**
     * Constructor for email specifying a host and a port. It uses the class
     * CipheringManager to retrieve the password of the account from the file
     * credentials.dat and deciphers it.
     *
     * @param host Host to set.
     * @param port Port to set.
     */
    public Email(String host, String port) {
        this.smtp_host = (host == null ? DEFAULT_SMTP_HOST : host);
        this.smtp_port = (port == null ? DEFAULT_SMTP_PORT : new Integer(port).intValue());

        this.password = new String(CipheringManager.decipherText(CipheringManager.fileReader("credentials.dat")));
    }

    /**
     * Sends a mail to the specified e-mail account.
     *
     * @param receiver E-mail account receiving the mail.
     * @param subject Subject of the mail.
     * @param text Text of the mail.
     */
    private void sendMail(String receiver, String subject, String text) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", true);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", smtp_host);
            properties.put("mail.smtp.port", smtp_port);
            properties.put("mail.smtp.ssl.trust", smtp_host);
            properties.put("mail.imap.partialfetch", false);
            properties.put("mail.smtp.ssl.enable", false);

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(text, "text/html");
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException exception) {
            LOGGER.warning("There was an error sending the email. " + exception.getMessage());
        }
    }

    /**
     * True if the mail is not null and a valid email address, false otherwise.
     *
     * @param mail The email address
     * @return True or False
     */
    public static boolean isValid(String mail) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$");
        if (mail == null) {
            return false;
        }
        return pattern.matcher(mail).matches();
    }

    /**
     * This method sends an email to the specified account with a temporal
     * password they can use to enter their account.
     *
     * @param receiver Receiver of the mail.
     * @param tempPassword Temporary password for the account.
     */
    public void sendRecoveryMail(String receiver, String tempPassword) {
        String message = "Your password has been reset. You can access your account using this temporal password: " + tempPassword;

        sendMail(receiver, "Password Reset", message);
    }

    /**
     * This method sends a mail to the specified account notifying them that
     * their password has been changed.
     *
     * @param receiver Receiver of the mail.
     */
    public void sendPasswordChangeMail(String receiver) {
        String message = "Your password has been changed.";

        sendMail(receiver, "Password Changed", message);
    }
}
