package ar.edu.utn.frba.dds.domain.servicios;


import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Banio implements Servicio {
  private Genero genero;
  private Boolean discapacitado;

}
