package ar.edu.utn.frba.dds.domain.MediosDeComunicacion;


import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Notificacion {

  //public String observaciones;
  //public Notificacion(String obs) {
  //  this.observaciones = obs;
 // }

//  public String getObservaciones() {
  //  return observaciones;
 // }

  private LocalDateTime fachaYHoraApertura;
  private String prestacionDeServicio;
  private String establecimiento;
  private String observaciones;
  private String abierto;

  public Notificacion(Incidente incidente){

    this.fachaYHoraApertura = incidente.getFachaYHoraApertura();
    this.prestacionDeServicio = incidente.getPrestacionDeServicio().getNombreServicio();
    this.establecimiento = incidente.getEstablecimiento().getNombre();
    this.observaciones = incidente.getObservaciones();
    if (incidente.getAbierto()){
      this.abierto = "INCIDENTE ABIERTO";
    }else{
      this.abierto = "INDICENTE CERRADO";
    }

  }

}