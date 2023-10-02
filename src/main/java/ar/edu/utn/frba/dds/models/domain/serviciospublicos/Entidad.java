package ar.edu.utn.frba.dds.models.domain.serviciospublicos;

import ar.edu.utn.frba.dds.models.domain.incidentes.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.models.excepciones.LocalizacionEstablecimientoInvalidaExcepcion;
import ar.edu.utn.frba.dds.models.excepciones.TipoEstablecimientoInvalidoExcepcion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "entidad")
@Setter @Getter
public class Entidad {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_localizacion",referencedColumnName = "id")
  private Localizacion localizacion;

  @Transient
  private String nombre;
  @OneToMany(mappedBy = "entidad")
  private List<Establecimiento> establecimientos;
  public void agregarEstablecimientos(Establecimiento... establecimientos) throws TipoEstablecimientoInvalidoExcepcion {

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