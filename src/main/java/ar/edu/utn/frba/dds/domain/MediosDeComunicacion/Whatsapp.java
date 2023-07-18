package ar.edu.utn.frba.dds.domain.MediosDeComunicacion;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;






public class Whatsapp extends MedioDeNotificacion{



  public static final String ACCOUNT_SID = "AC8248ee633b83070844ab4267e9269cb8";

  public static final String AUTH_TOKEN = "fa45e420a9fe4186a4fe2f8e35a7c139";





  @Override
  public void enviarNotificacion(Notificacion notificacion) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber("whatsapp:+5491144134775"),
            new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
            notificacion.getObservaciones())
        .create();
    System.out.println(message.getSid());
    System.out.println("Se ha enviado notificacion al WhatssApp - ");


  }
}
