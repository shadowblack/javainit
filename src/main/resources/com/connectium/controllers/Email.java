package com.connectium.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by user on 23/6/2017.
 */
public class Email {


    public void sendMail(String message_user){

        Logger log = LoggerFactory.getLogger(Email.class);

        String asunto = "";

        try{

            String servidorSMTP =   "smtp.gmail.com";
            String puerto       =   "587";
            String usuario      =   "reportes@conectium.com";
            String password     =   "ResportesCNTM$$";
            String destino1     =   "mgutierrez@conectium.com";
            asunto              =   "Informacion del Site";
            String mensaje      =   message_user;

            Properties props = new Properties();

            props.put("mail.debug", "true");
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.host", servidorSMTP);
            props.put("mail.smtp.port", puerto);

            Session session = Session.getInstance(props, null);

            //log.debug("mensaje "+ mensaje);


            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino1));

            message.setSubject(asunto);
            message.setSentDate(new Date());
            message.setContent(mensaje, "text/html");

            Transport tr = session.getTransport("smtp");
            tr.connect(servidorSMTP, usuario, password);
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
            log.debug("El mensaje fue enviado exitosamente");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
