package ar.edu.utn.frba.dds.domain.entidadesExtra;

import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class OrganismoDeControl {
    String nombre;
    @Setter String descripcion;
    @Setter Localizacion localizacion;
    List<Entidad> entidades;
    public OrganismoDeControl(String nombre){
        this.nombre = nombre;
        this.entidades = new ArrayList<>();
    }
}
