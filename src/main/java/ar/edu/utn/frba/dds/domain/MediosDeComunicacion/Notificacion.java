package ar.edu.utn.frba.dds.domain.MediosDeComunicacion;


import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import java.time.LocalDateTime;

public class Notificacion {
  private LocalDateTime fachaYHoraApertura;
  private PrestacionDeServicio prestacionDeServicio;
  private Establecimiento establecimiento;
  private String observaciones;
  private Boolean abierto;

  public Notificacion(Incidente incidente){
    this.fachaYHoraApertura = incidente.getFachaYHoraApertura();
    this.prestacionDeServicio = incidente.getPrestacionDeServicio();
    this.establecimiento = incidente.getEstablecimiento();
    this.observaciones = incidente.getObservaciones();
    this.abierto = incidente.getAbierto();
  }

  public LocalDateTime getFachaYHoraApertura() {
    return fachaYHoraApertura;
  }

  public PrestacionDeServicio getPrestacionDeServicio() {
    return prestacionDeServicio;
  }

  public Establecimiento getEstablecimiento() {
    return establecimiento;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public Boolean getAbierto() {
    return abierto;
  }
}
