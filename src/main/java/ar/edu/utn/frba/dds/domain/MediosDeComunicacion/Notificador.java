package ar.edu.utn.frba.dds.domain.MediosDeComunicacion;

import ar.edu.utn.frba.dds.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.domain.incidentes.AperturaIncidente;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;

import java.util.List;
import java.util.Set;

public class Notificador {
    private Set<Miembro> miembrosNotificados;
    public void notificar(Miembro emisor, AperturaIncidente aperturaIncidente){
        notificarComunidades(emisor,aperturaIncidente);
        notificarInteresados(emisor,aperturaIncidente);
        miembrosNotificados.clear();
    }

    public void notificarComunidades(Miembro emisor, AperturaIncidente aperturaIncidente){
        for(Comunidad comunidad : emisor.getComunidades()){
            Incidente incidente = new Incidente();
            incidente.meAbro(emisor,aperturaIncidente);
            filtrarYaNotificados(comunidad.getMiembros(),incidente)
                    .forEach(m->m.getMedioDeNotificacion().evaluarEnvioDeNotificacion(new Notificacion(incidente)));
            comunidad.getIncidentes().add(incidente);
        }

    }

    public void notificarInteresados(Miembro emisor, AperturaIncidente aperturaIncidente){
        Incidente incidente = new Incidente();
        incidente.meAbro(emisor,aperturaIncidente);
        filtrarYaNotificados(aperturaIncidente.getPrestacionDeServicio().getInteresados(),incidente)
                .forEach(m->m.getMedioDeNotificacion().evaluarEnvioDeNotificacion(new Notificacion(incidente)));
    }

    public List<Miembro> filtrarYaNotificados(List<Miembro> notificables,Incidente incidente){
        miembrosNotificados.addAll(notificables);
        return (List<Miembro>) notificables.stream().filter(miembro -> miembro.getUsuario() != incidente.getAbridor().getUsuario());
    }

}
