package ar.edu.utn.frba.dds.domain.comunidades;


import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.MedioDeNotificacion;
import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.Notificacion;
import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.Notificador;
import ar.edu.utn.frba.dds.domain.incidentes.AperturaIncidente;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;

import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.excepciones.NoEsUnaPrestacionValidaExcepcion;
import ar.edu.utn.frba.dds.excepciones.PrestacionFuncionaExcepcion;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.*;

@Setter @Getter
public class Miembro {
  private String usuario;
  private String clave;
  private List<Interes> intereses;
  private Localizacion localizacion;
  public MedioDeNotificacion medioDeNotificacion;
  private Set<Comunidad> comunidades;

  public Miembro(){
    this.comunidades = new HashSet<>();
    this.intereses = new ArrayList<>();
  }
  public void agregarIntereses(Interes ... interes){
    Collections.addAll(this.intereses,interes);
  }

  public void eliminarIntereses(Interes ... interes){
    for (Interes value : interes) {
      this.intereses.remove(value);
    }
  }

  public void actualizarIntereses() throws IOException {
    for(Interes interes : this.intereses){
      interes.actualizarInteres(this.localizacion);
      interes.actualizarInteresados(this);
    }
  }

  public Boolean actualizarPrestacionDeServicio(Establecimiento establecimiento, PrestacionDeServicio prestacionDeServicio){
    if(!establecimiento.getPrestacionesDeServicios().contains(prestacionDeServicio)){
      throw new NoEsUnaPrestacionValidaExcepcion();
    }
    return prestacionDeServicio.getFunciona();
  }


  public void abrirIncidente(AperturaIncidente aperturaIncidente, Notificador notificador){
    if(actualizarPrestacionDeServicio(aperturaIncidente.getEstablecimiento(),aperturaIncidente.getPrestacionDeServicio())){
      throw new PrestacionFuncionaExcepcion();
    }
    notificador.notificar(this,aperturaIncidente);


  }


  /**
   * @param usuario: Nombre de Usuario
   * @param clave: Contraseña
   * @return Nuevo Usuario
   */
}
