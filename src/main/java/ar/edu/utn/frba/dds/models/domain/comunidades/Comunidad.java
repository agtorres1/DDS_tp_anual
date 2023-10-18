package ar.edu.utn.frba.dds.models.domain.comunidades;

import ar.edu.utn.frba.dds.models.builders.puntajes.ComunidadPuntajeBuilder;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Notificacion;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.utn.frba.dds.models.domain.incidentes.TipoFiltrado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.ComunidadPuntaje;
import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.MiembroPuntaje;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Comunidades")
@Getter @Setter
public class Comunidad{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Opciones: IDENTITY, SEQUENCE, TABLE, etc.
  @Column(name = "id")
  private Long id;
  @ManyToMany
  @JoinTable(name = "administradores_por_comunidad",
          joinColumns = @JoinColumn(name = "miembro_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  )
  private List<Miembro> administradores;
  @ManyToMany
  @JoinTable(name = "miembros_por_comunidad",
      joinColumns = @JoinColumn(name = "miembro_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  )
  private List<Miembro> miembros;

  @OneToMany
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private List<Incidente> incidentes;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  public Comunidad() {
    this.administradores = new ArrayList<>();
    this.miembros = new ArrayList<>();
    this.incidentes = new ArrayList<>();
  }

   //esto es para fijarse el estado de los incidentes abiertos
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

  public List<> incidentesSemanales(TipoFiltrado tipoFiltrado) {

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
    this.miembros.forEach(miembro -> miembro.getComunidades().add(this));
  }

  public void removerUsuarios(Miembro... miembros) {
    for (Miembro value : miembros) {
      this.miembros.remove(value);
    }
  }

  public int cantidadDeMiembros(){
    return this.miembros.size();
  }

  public int cantidadDeIncidentes(){
    return this.incidentes.size();
  }



  public void notificarMiembros(Incidente incidente){
    getIncidentes().add(incidente);
    Notificacion notificacion = new Notificacion();
    notificacion.crearNotificacion(incidente);
    this.miembros.stream().filter(miembro -> miembro.getUsuario() != incidente.getAbridor().getUsuario())
                          .forEach(m->m.getMedioDeNotificacion().evaluarEnvioDeNotificacion(notificacion));
    System.out.println("Se ha enviado notificacion al WhatssApp - ");
  }

  public void cerrarIncidente(Miembro autor,Incidente incidente){
    incidente.meCierro(autor);
    this.notificarMiembros(incidente);
  }
  public ComunidadPuntaje comunidadPuntaje(double puntajeComunidad){
    return new ComunidadPuntajeBuilder().conId(getId()).conPuntaje(puntajeComunidad).construir();
  }



}
