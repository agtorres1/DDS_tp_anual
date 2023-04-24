package ar.edu.utn.frba.dds.domain.servicios;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Banio extends Servicio {
  String genero;
  Boolean discapacitado;
}
