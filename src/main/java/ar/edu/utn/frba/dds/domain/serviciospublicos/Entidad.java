package ar.edu.utn.frba.dds.domain.serviciospublicos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import static ar.edu.utn.frba.dds.domain.serviciospublicos.TipoEntidad.LINEA_TRASPORTE;

@Setter @Getter
public class Entidad {
  private TipoEntidad tipoEntidad;
  private TipoEstablecimiento tipoEstablecimiento;
  private String nombre;
  private List<Establecimiento> establecimientos;
  public Entidad(TipoEntidad tipoEntidad) {
    this.tipoEntidad = tipoEntidad;
    this.establecimientos = new ArrayList<>();
  }
}
