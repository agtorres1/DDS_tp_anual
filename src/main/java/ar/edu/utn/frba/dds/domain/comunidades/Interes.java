package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicios;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import lombok.Getter;

import java.util.*;

@Getter
public class Interes {
    private Entidad entidad;
    private Set<PrestacionDeServicios> prestacionesDeInteres;

    public Interes(Entidad entidad){
        this.entidad = entidad;
        this.prestacionesDeInteres = new HashSet<PrestacionDeServicios>();
    }


    public void actualizarInteres(Localizacion localizacion){
        this.prestacionesDeInteres.clear();
        if(Objects.equals(localizacion.getProvincia(), this.entidad.getLocalizacion().getProvincia())){
            for(Establecimiento establecimiento : this.entidad.getEstablecimientos()){
                for(PrestacionDeServicios prestacionDeServicios : establecimiento.getPrestacionesDeServicios()){
                    if(!prestacionDeServicios.getServicio().getFunciona()){
                        this.prestacionesDeInteres.add(prestacionDeServicios);
                    }
                }
            }
        }
    }
}
