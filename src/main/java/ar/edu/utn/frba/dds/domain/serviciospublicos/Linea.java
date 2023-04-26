package ar.edu.utn.frba.dds.domain.serviciospublicos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Linea {
  private Character nombre;
  private Estacion origen;
  private Estacion destino;
  private List<Estacion> estaciones;
  public Linea(List<Estacion> estaciones, Estacion origen, Estacion destino) {
    if (!estaciones.contains(origen) || !estaciones.contains(destino)) {
      throw new IllegalArgumentException("La lista de estaciones debe incluir el origen y el destino.");
    }
    this.origen = origen;
    this.destino = destino;
    this.estaciones=estaciones;
  }
}
