package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicios;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Interes {
    private Entidad entidad;
    private List<PrestacionDeServicios> prestacionesDeInteres;

    public Interes(Entidad entidad){
        this.entidad = entidad;
        this.prestacionesDeInteres = new ArrayList<>();
    }

    public void actualizarInteres(){
        for(Establecimiento establecimiento : this.entidad.getEstablecimientos()){
            for(PrestacionDeServicios prestacionDeServicios : establecimiento.getPrestacionesDeServicios()){
                if(!prestacionDeServicios.getServicio().getFunciona()){
                    this.prestacionesDeInteres.add(prestacionDeServicios);
                }
            }
        }
    }
}
