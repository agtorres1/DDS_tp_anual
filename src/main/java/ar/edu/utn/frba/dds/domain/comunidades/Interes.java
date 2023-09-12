package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;

import java.io.IOException;
import java.util.*;
@Entity
@Table(name = "Intereses")
@Getter
public class Interes {
    @Id
    @GeneratedValue
    private Long id;
    @Transient
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
                        this.prestacionesDeInteres.add(new InteresEnPrestacion (prestacionDeServicio));
                    }
                }
            }
        }
    }

    public void actualizarInteresados(Miembro miembro) {
        for(InteresEnPrestacion interesEnPrestacion : this.prestacionesDeInteres ){
            interesEnPrestacion.getPrestacion().getInteresados().add(miembro);
        }
    }
}
