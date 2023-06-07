package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.ValidadorContrasenias.ValidadorDeContrasenias;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter @Getter
public class Miembro {
  private String usuario;
  private String clave;
  private List<Interes> intereses;
  private Localizacion localizacion;

  public Miembro(String usuario, String clave, Localizacion localizacion) {
    this.usuario = usuario;
    this.clave = clave;
    List<Interes> intereses = new ArrayList<>();
    this.localizacion = localizacion;
  }

  public void agregarInteres(Interes ... interes){
    Collections.addAll(this.intereses,interes);
  }

  public void actualizarIntereses(){
    for(Interes interes : this.intereses){
      interes.actualizarInteres();
    }
  }

  /**
   * @param usuario: Nombre de Usuario
   * @param clave: Contraseña
   * @return Nuevo Usuario
   */
}
