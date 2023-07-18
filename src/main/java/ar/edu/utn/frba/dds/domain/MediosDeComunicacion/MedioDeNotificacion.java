package ar.edu.utn.frba.dds.domain.MediosDeComunicacion;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;


public abstract class MedioDeNotificacion {


  List<Notificacion> notificacionesRecientes;

  List<RangoHorario> rangosHorariosElegidos;

  public MedioDeNotificacion(){
    this.notificacionesRecientes = new ArrayList<>();
    this.rangosHorariosElegidos = new ArrayList<>();
  }

  public List<Notificacion> getNotificacionesRecientes() {
    return notificacionesRecientes;
  }

  public void setRangosHorariosElegidos(List<RangoHorario> rangosHorariosElegidos) {
    this.rangosHorariosElegidos = rangosHorariosElegidos;
  }

  public void evaluarEnvioDeNotificacion(Notificacion notificacion) {

      if (rangosHorariosElegidos.isEmpty()) {
        this.enviarNotificacion(notificacion);
      } else if (notificacionesRecientes.isEmpty()) {
        LocalTime horaActual = LocalTime.now();
        Optional<LocalTime> horaInicioProxima;
        long diferenciaEnSegundos;
        if (rangosHorariosElegidos.stream().anyMatch(rangoHorario -> rangoHorario.contiene(horaActual)))/*chequeo que se encuentre dentro del horario*/ {
          this.enviarNotificacion(notificacion);

        } else {
                  List<RangoHorario> rangosHorariosProximos = rangosHorariosElegidos.stream()
                                                              .filter(rangoHorario -> rangoHorario.getHoraInicio().isAfter(horaActual)).toList();

                  if (rangosHorariosProximos.isEmpty()) { //Quiere decir que el horario actual esta despues que los rangos horarios
                    horaInicioProxima = rangosHorariosElegidos.stream()
                                                              .map(RangoHorario::getHoraInicio)
                                                              .min(Comparator.naturalOrder());
                    diferenciaEnSegundos =  24 * 3600 - horaInicioProxima.orElse(LocalTime.of(0, 0)).until(horaActual, ChronoUnit.SECONDS);

                  }else{//Quiere decir que el horario actual esta antes que algun rango horario
                    horaInicioProxima = rangosHorariosProximos.stream()
                                                              .map(RangoHorario::getHoraInicio)
                                                              .min(Comparator.naturalOrder());
                    diferenciaEnSegundos = horaActual.until(horaInicioProxima.orElse(LocalTime.of(0, 0)), ChronoUnit.SECONDS);
                  }

                  TimerTask tareaCalendarizada = new TimerTask() {
                    public void run() {
                      enviarNotificacion(notificacion);
                    }
                  };
                  Timer timer = new Timer();
                  timer.schedule(tareaCalendarizada, diferenciaEnSegundos * 1000); // 1000 ms = 1 segundo
        }


      } else { //Si ya hay notificaciones recientes simplemente la agrego a la lista porque la cron task ya fue creada
        this.notificacionesRecientes.add(notificacion);
      }

  }
  protected abstract void enviarNotificacion(Notificacion notificacion);
}
