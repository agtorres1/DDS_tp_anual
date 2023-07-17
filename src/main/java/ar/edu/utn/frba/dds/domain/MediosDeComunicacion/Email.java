package ar.edu.utn.frba.dds.domain.MediosDeComunicacion;


import javax.swing.*;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email  {





  public Email() {
  }





  public void enviarNotificacion(String notificacion) {
    try {
      Properties propiedades = new Properties();
      propiedades.setProperty("mail.smtp.host", "smtp.office365.com");
      propiedades.setProperty("mail.smtp.starttls.enable", "true");
      propiedades.setProperty("mail.smtp.port", "587");
      propiedades.setProperty("mail.smtp.auth", "true");

      propiedades.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
      Session sesion = Session.getDefaultInstance(propiedades);
      String correo_emisor = "tpddsgrupo42023@outlook.es";
      String contrasenia_emisor = "tpdds42023";

      String correo_receptor = "facuantelo.fa@gmail.com";
      String asunto = "Notificacion TP 2023";
      String mensaje = notificacion;


      MimeMessage message = new MimeMessage(sesion);
      message.setFrom(new InternetAddress(correo_emisor));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo_receptor));
      message.setSubject(asunto);
      message.setText(mensaje);

      Transport transporte = sesion.getTransport("smtp");
      transporte.connect(correo_emisor,contrasenia_emisor);
      transporte.sendMessage(message , message.getRecipients(Message.RecipientType.TO));
      transporte.close();

      System.out.println("Se ha enviado notificacion al Email - " + new Date());

    } catch (AddressException ex) {
      JOptionPane.showMessageDialog(null,"Error : " +ex);
    } catch (MessagingException ex) {
      JOptionPane.showMessageDialog(null,"Error : " +ex);
    }
  }
}
