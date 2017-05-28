/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.prestamos.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jnda
 */
public class sendEmail extends Thread {

    private String to = "prestamodispositivos@gmail.com";

    // Sender's email ID needs to be mentioned
    private String from = "prestamodispositivos@gmail.com";

    // Assuming you are sending email from localhost
    private String host = "localhost";
    // Get system properties
    private Properties properties;
    private String mensaje;
    private String asunto;

    public sendEmail() {
        this.properties = System.getProperties();
        this.asunto = "Mensaje de Prestamo de dispositivos";
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMessage() {
        return mensaje;
    }

    public void setMessage(String message) {
        this.mensaje = message;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    @Override
    public void run() {
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //properties.put("mail.smtp.user", "freaklab69@gmail.com"); // User name
        properties.put("mail.smtp.user", "info@helpbox.com.co"); // User name       
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            SmtpAuthenticator authentication = new SmtpAuthenticator();
            javax.mail.Message message = new MimeMessage(Session
                    .getInstance(properties, authentication));

            //MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(this.asunto);

            // Now set the actual message
            message.setText(mensaje);

            // Send message
            Transport.send(message);
            System.out.println("Se ha enviado un mensaje con exito....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
