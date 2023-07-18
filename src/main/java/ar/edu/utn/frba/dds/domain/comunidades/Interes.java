package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import lombok.Getter;

import java.io.IOException;
import java.util.*;

@Getter
public class Interes {
    private Entidad entidad;
    private Set<InteresEnPrestacion> prestacionesDeInteres;
    public Interes(Entidad entidad){
        this.entidad = entidad;
        this.prestacionesDeInteres = new HashSet<>();
    }

    public Boolean tieneLocalizacionEspecifica(Localizacion localizacion){
        return localizacion.getMunicipio() != null || localizacion.getDepartamento() != null;
    }
    public Boolean tieneLocalizacionValida(Localizacion localizacion, Establecimiento establecimiento) throws IOException {
        if(tieneLocalizacionEspecifica(localizacion)){
            return localizacion.getMunicipio() == establecimiento.getLocalizacion().getMunicipio()
                    ||
                    localizacion.getDepartamento() == establecimiento.getLocalizacion().getDepartamento();
        }
        return localizacion.getProvincia().equals(this.entidad.getLocalizacion().getProvincia());
    }

    public void actualizarInteres(Localizacion localizacion) throws IOException {
        this.prestacionesDeInteres.clear();
        for(Establecimiento establecimiento : this.entidad.getEstablecimientos()){
            if(tieneLocalizacionValida(localizacion, establecimiento)){
                for(PrestacionDeServicio prestacionDeServicio : establecimiento.getPrestacionesDeServicios()) {
                    if (!prestacionDeServicio.getFunciona()) {
                        var prestacionNueva = new InteresEnPrestacion (prestacionDeServicio);
                        this.prestacionesDeInteres.add(prestacionNueva);
                    }
                }
            }
        }
    }
}
