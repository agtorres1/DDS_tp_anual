package ar.edu.utn.frba.dds.domain.comunidades;

import ValidadorContrasenias.Validable;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Usuario{
  private String usuario;
  private String clave;

  public boolean esValida(String clave){
    return true;
  }
  public Usuario(String usuario, String clave) {
    this.usuario = usuario;
    this.clave = clave;
  }

  /**
   * @param usuario: Nombre de Usuario
   * @param clave: Contraseña
   * @return Nuevo Usuario
   */
  public Usuario registrarse(String usuario, String clave) {
    if (this.esValida(clave)) {
      return new Usuario(this.clave = clave, this.usuario = usuario);
    } else {
      return null;
    }
  }
}
