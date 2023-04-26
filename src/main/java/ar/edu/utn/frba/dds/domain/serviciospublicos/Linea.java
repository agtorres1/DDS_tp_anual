package ar.edu.utn.frba.dds.domain.serviciospublicos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Linea {
  Character nombre;
  Estacion origen;
  Estacion destino;
  List<Estacion> estaciones;
  public Linea(List<Estacion> estaciones, Estacion origen, Estacion destino) {
    if (!estaciones.contains(origen) || !estaciones.contains(destino)) {
      throw new IllegalArgumentException("La lista de estaciones debe incluir el origen y el destino.");
    }
    this.estaciones = estaciones;
    this.origen = origen;
    this.destino = destino;
  }
}
