package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.Notificacion;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.utn.frba.dds.domain.incidentes.TipoFiltrado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Comunidades")
@Getter @Setter
public class Comunidad{
  private List<Miembro> administradores;
  @ManyToMany
  @JoinTable(name = "miembros_por_comunidad",
      joinColumns = @JoinColumn(name = "miembro_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  )
  private List<Miembro> miembros;
  @OneToMany
  private List<Incidente> incidentes;
  @Column(name = "nombre")
  private String nombre;

  public Comunidad() {
    this.nombre = "HOLA";
    this.administradores = new ArrayList<>();
    this.miembros = new ArrayList<>();
    this.incidentes = new ArrayList<>();
  }

   //esto s para fijarse el estado de los incidentes abiertos
  public void imprimirIncidentes(TipoFiltrado tipoFiltrado) {
    for (Incidente incidente : incidentes) {
      if (tipoFiltrado == TipoFiltrado.SOLO_ABIERTOS && incidente.getAbierto()) {
        System.out.println(incidente);
      } else if (tipoFiltrado == TipoFiltrado.SOLO_CERRADOS && !incidente.getAbierto()) {
        System.out.println(incidente);
      } else if (tipoFiltrado == TipoFiltrado.TODOS) {
        System.out.println(incidente);
      }
    }
  }
  
/*



  public void ingresarServicioNuevo(Establecimiento establecimiento, Miembro administrador, String nombre, String descripcion, int cantidad) throws NoEsAdministradorExcepcion {
    this.verificarQueEsAdministrador(administrador);
    ServicioComunitario servicio = new ServicioComunitario();
    servicio.setNombre(nombre);
    servicio.setDescripcion(descripcion);
    PrestacionDeServicio prestacionDeServicio = new PrestacionDeServicio(servicio,cantidad);
    prestacionDeServicio.setFunciona(false);
    establecimiento.agregarPrestaciones(prestacionDeServicio);
  }

  public void verificarQueEsAdministrador(Miembro administrador) throws NoEsAdministradorExcepcion{
    if (!this.administradores.contains(administrador)) {
      throw new NoEsAdministradorExcepcion();
    }
  }
*/
  public void agregarUsuarios(Miembro... miembros) {
    this.miembros.addAll(Arrays.asList(miembros));
  }

  public void agregarAdministradores(Miembro... administradores) {
    this.administradores.addAll(Arrays.asList(administradores));
  }

  public void removerUsuarios(Miembro... miembros) {
    for (Miembro value : miembros) {
      this.miembros.remove(value);
    }
  }
  public void removerAdministradores(Miembro... administradores) {
      for (Miembro value : administradores){
        this.administradores.remove(value);
      }
  }



  public void notificarMiembros(Incidente incidente){
    getIncidentes().add(incidente);
    Notificacion notificacion = new Notificacion(incidente);
    this.miembros.stream().filter(miembro -> miembro.getUsuario() != incidente.getAbridor().getUsuario())
                          .forEach(m->m.getMedioDeNotificacion().evaluarEnvioDeNotificacion(notificacion));
    System.out.println("Se ha enviado notificacion al WhatssApp - ");
  }

  public void cerrarIncidente(Miembro autor,Incidente incidente){
    incidente.meCierro(autor);
    this.notificarMiembros(incidente);
  }

}
