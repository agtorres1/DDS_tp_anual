package ar.edu.utn.frba.dds.domain.MediosDeComunicacion;


import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "notificacion")
@Getter@Setter
public class Notificacion {
  @Id
  @GeneratedValue
  private Long id;




  //public String observaciones;
  //public Notificacion(String obs) {
  //  this.observaciones = obs;
 // }

//  public String getObservaciones() {
  //  return observaciones;
 // }
  @Column(name = "fecha_y_hora_Apertura")
  private LocalDateTime fachaYHoraApertura;
  @Column(name = "prestacion_De_servicio")
  private String prestacionDeServicio;
  @Column(name = "establecimiento")
  private String establecimiento;
  @Column(name = "observaciones", columnDefinition = "text")
  private String observaciones;
  @Column(name = "estado_de_incidente")
  private String abierto;

  public void crearNotificacion(Incidente incidente){

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