package ar.edu.utn.frba.dds.models.domain.serviciospublicos;

import ar.edu.utn.frba.dds.models.domain.incidentes.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter @Getter
@Entity
@Table(name = "establecimientos")
public class Establecimiento {
  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "nombre")
  String nombre;
  @Transient
  Ubicacion centroide;
  @OneToMany
  @JoinColumn(name = "id_establecimiento",referencedColumnName = "id")
  Set<PrestacionDeServicio> prestacionesDeServicios;
  @Enumerated(EnumType.STRING)
  TipoEstablecimiento tipoEstablecimiento;
  @ManyToOne
  @JoinColumn(name = "id_localizacion",referencedColumnName = "id")
  Localizacion localizacion;
  @ManyToOne
  @JoinColumn(name = "id_entidad", referencedColumnName = "id")
  Entidad entidad;

  @Transient
  Ubicacion ubicacion;


  public void agregarPrestaciones(PrestacionDeServicio... prestacionDeServicios) {
    Collections.addAll(this.prestacionesDeServicios, prestacionDeServicios);
  }


  public void darDeBajaPrestaciones(PrestacionDeServicio... prestacionesDeServicios) {
    for (PrestacionDeServicio value : prestacionesDeServicios) {
      this.prestacionesDeServicios.remove(value);
    }
  }

  public void cambiarPrestacionDeServicio(Boolean funciona, PrestacionDeServicio... prestacionesDeServicios) {
    for (PrestacionDeServicio value : prestacionesDeServicios) {
      value.setFunciona(funciona);
    }
  }

}
