package ar.edu.utn.frba.dds.domain.serviciospublicos;

import ar.edu.utn.frba.dds.excepciones.TipoEstablecimientoInvalidoExcepcion;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Setter @Getter
public class Entidad {
  private Localizacion localizacion;
  private TipoEntidad tipoEntidad;
  private TipoEstablecimiento tipoEstablecimiento;
  private String nombre;
  private List<Establecimiento> establecimientos;

  public void agregarEstablecimientos(Establecimiento... establecimientos) throws TipoEstablecimientoInvalidoExcepcion {
    if(Arrays.stream(establecimientos).anyMatch(e->e.getTipoEstablecimiento()!=this.tipoEstablecimiento)){
      throw new TipoEstablecimientoInvalidoExcepcion();
    }
    Collections.addAll(this.establecimientos,establecimientos);
  }

  /**
   * param servicio: Lista de servicios
   */
  public void eliminarEstablecimientos(Establecimiento ... establecimiento) {
    for (Establecimiento value : establecimiento) {
      this.establecimientos.remove(value);
    }
  }
}
