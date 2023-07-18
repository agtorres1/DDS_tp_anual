package ar.edu.utn.frba.dds.domain.comunidades;


import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.MedioDeNotificacion;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Setter @Getter
public class Miembro {
  private String usuario;
  private String clave;
  private List<Interes> intereses;
  private Localizacion localizacion;
  public MedioDeNotificacion medioDeNotificacion;


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
    }
  }


  /**
   * @param usuario: Nombre de Usuario
   * @param clave: Contraseña
   * @return Nuevo Usuario
   */
}
