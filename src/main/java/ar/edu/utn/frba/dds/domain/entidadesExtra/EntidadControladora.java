package ar.edu.utn.frba.dds.domain.entidadesExtra;

import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntidadControladora {
    String nombre;
    @Setter String descripcion;
    @Setter String direccion;
    @Setter Localizacion localizacion;
    @Getter List<Entidad> entidades;
    public EntidadControladora(String nombre){
        this.nombre = nombre;
        this.entidades = new ArrayList<>();
    }
    public void agregarEntidades(Entidad ... entidades ){
        this.entidades.addAll(Arrays.asList(entidades));
    }

    public void eliminarEntidades(Entidad ... entidades){
        for (Entidad entidad : entidades) {
            this.entidades.remove(entidad);
        }
    }


}
