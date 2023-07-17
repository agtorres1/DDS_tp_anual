package ar.edu.utn.frba.dds.domain.MediosDeComunicacion;

import java.time.LocalTime;
import java.util.List;


public abstract class MedioDeNotificacion {

  List<Notificacion> notificacionesRecientes;

  List<RangoHorario> rangosHorariosElegidos;

  public void evaluarEnvioDeNotificacion(Notificacion notificacion){

    if (rangosHorariosElegidos.isEmpty()){
      this.enviarNotificacion(notificacion);
    }else if (notificacionesRecientes.isEmpty()) {
      LocalTime horaActual = LocalTime.now();

                if(rangosHorariosElegidos.stream().anyMatch(rangoHorario -> rangoHorario.contiene(horaActual)))/*chequeo que se encuentre dentro del horario*/{
                    this.enviarNotificacion(notificacion);

                 }else {
                  List<RangoHorario> rangosHorariosProximos = rangosHorariosElegidos.stream()
                      .filter(rangoHorario -> rangoHorario.getHoraInicio().isAfter(horaActual)).toList();

                  if (rangosHorariosProximos.isEmpty()){


                  }
                }



            }else{ //Si ya hay notificaciones recientes simplemente la agrego a la lista porque la cron task ya fue creada
                this.notificacionesRecientes.add(notificacion);
              }



}

  protected abstract void enviarNotificacion(Notificacion notificacion);
