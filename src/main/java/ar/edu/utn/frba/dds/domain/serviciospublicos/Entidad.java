package ar.edu.utn.frba.dds.domain.serviciospublicos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import static ar.edu.utn.frba.dds.domain.serviciospublicos.TipoEntidad.LINEA;

@Setter @Getter
public class Entidad {
  private TipoEntidad nombre;

  private List<Establecimiento> establecimientos;
  public Entidad(@NotNull List<Establecimiento> establecimientos, Establecimiento origen, Establecimiento destino, TipoEntidad nombre) {
    if ((!establecimientos.contains(origen) || !establecimientos.contains(destino)) && (nombre == LINEA)) {
      throw new IllegalArgumentException("La lista de estaciones debe incluir el origen y el destino.");
    }
    this.establecimientos = establecimientos;
    this.nombre = nombre;
  }
}
