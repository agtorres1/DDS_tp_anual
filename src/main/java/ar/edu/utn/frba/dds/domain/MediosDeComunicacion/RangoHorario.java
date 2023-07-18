package ar.edu.utn.frba.dds.domain.MediosDeComunicacion;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RangoHorario {

    public LocalTime horaInicio;
    public LocalTime horaFin;

  public LocalTime getHoraFin() {
    return horaFin;
  }

  public LocalTime getHoraInicio() {
    return horaInicio;
  }

  public RangoHorario(LocalTime inicio, LocalTime fin) {
      this.horaInicio = inicio;
      this.horaFin = fin;
    }


    public boolean contiene(LocalTime hora) {
      return !hora.isBefore(horaInicio) && !hora.isAfter(horaFin);
    }

}
