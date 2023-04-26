package ar.edu.utn.frba.dds.domain.serviciospublicos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Linea {
  String nombre;
  Estacion origen;
  Estacion destino;
  List<Estacion> estaciones;
}
