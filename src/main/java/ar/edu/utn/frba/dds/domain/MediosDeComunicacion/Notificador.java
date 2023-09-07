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
        miembrosNotificados.clear();
        for(Comunidad comunidad : emisor.getComunidades()){
            Incidente incidente = new Incidente();
            incidente.meAbro(emisor,aperturaIncidente);
            filtrarYaNotificados(comunidad.getMiembros(),incidente)
                    .forEach(m->m.getMedioDeNotificacion().evaluarEnvioDeNotificacion(incidente.getNotificacion()));
        }
        notificarInteresados(aperturaIncidente,emisor);
    }

    public void notificarInteresados(AperturaIncidente aperturaIncidente,Miembro emisor){
        Incidente incidente = new Incidente();
        incidente.meAbro(emisor,aperturaIncidente);
        filtrarYaNotificados(aperturaIncidente.getPrestacionDeServicio().getInteresados(),incidente)
                .forEach(m->m.getMedioDeNotificacion().evaluarEnvioDeNotificacion(incidente.getNotificacion()));
    }

    public List<Miembro> filtrarYaNotificados(List<Miembro> notificables,Incidente incidente){
        miembrosNotificados.addAll(notificables);
        return (List<Miembro>) notificables.stream().filter(miembro -> miembro.getUsuario() != incidente.getAbridor().getUsuario());
    }

}
