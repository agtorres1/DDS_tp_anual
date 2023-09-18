package ar.edu.utn.frba.dds.domain.serviciospublicos;

import ar.edu.utn.frba.dds.excepciones.LocalizacionEstablecimientoInvalidaExcepcion;
import ar.edu.utn.frba.dds.excepciones.TipoEstablecimientoInvalidoExcepcion;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Setter @Getter
public class Entidad {
  @Id
  @GeneratedValue
  private Long id;
  @Transient
  private Localizacion localizacion;
  @Transient
  private TipoEntidad tipoEntidad;
  @Transient
  private TipoEstablecimiento tipoEstablecimiento;
  @Transient
  private String nombre;
  @Transient
  private List<Establecimiento> establecimientos;

  public void agregarEstablecimientos(Establecimiento... establecimientos) throws TipoEstablecimientoInvalidoExcepcion {
    if(Arrays.stream(establecimientos).anyMatch(e->e.getTipoEstablecimiento()!=this.tipoEstablecimiento)){
      throw new TipoEstablecimientoInvalidoExcepcion();
    }
    if(Arrays.stream(establecimientos).anyMatch(e->e.getLocalizacion().getProvincia()!=this.localizacion.getProvincia())){
      throw new LocalizacionEstablecimientoInvalidaExcepcion();
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
