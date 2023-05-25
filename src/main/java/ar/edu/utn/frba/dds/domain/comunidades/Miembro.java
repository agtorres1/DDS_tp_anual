package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.ValidadorContrasenias.ValidadorDeContrasenias;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Miembro {
  private String usuario;
  private String clave;

  public boolean esValida(String clave){
    return true;
  }
  public Miembro(String usuario, String clave) {
    this.usuario = usuario;
    this.clave = clave;
  }

  /**
   * @param usuario: Nombre de Usuario
   * @param clave: Contraseña
   * @return Nuevo Usuario
   */
  public Miembro registrarse(String usuario, String clave, ValidadorDeContrasenias validador) {
    if (validador.esValida(clave)){
      return new Miembro(this.clave = clave, this.usuario = usuario);
    }
    else {
      return null;
    }
  }
}
