package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.TipoEstablecimiento;
import ar.edu.utn.frba.dds.excepciones.SinLocalizacionValida;
import ar.edu.utn.frba.dds.excepciones.SinNombreExcepcion;
import ar.edu.utn.frba.dds.excepciones.SinTipoExcepcion;

import java.util.ArrayList;


public class EntidadBuilder {
    private Entidad entidad = new Entidad();

    public EntidadBuilder conNombre(String nombre){
        this.entidad.setNombre(nombre);
        return this;
    }
    public EntidadBuilder conTipo(TipoEstablecimiento tipoEstablecimiento){
        this.entidad.setTipoEstablecimiento(tipoEstablecimiento);
        return this;
    }
    public EntidadBuilder conLocalizacion(Localizacion localizacion){
        this.entidad.setLocalizacion(localizacion);
        return this;
    }
    public Entidad construir(){
        if(this.entidad.getNombre() == null){
            throw new SinNombreExcepcion();
        }
        if(this.entidad.getTipoEstablecimiento() == null){
            throw new SinTipoExcepcion();
        }
        if(this.entidad.getLocalizacion() == null){
            throw new SinLocalizacionValida();
        }
        this.entidad.setEstablecimientos(new ArrayList<>());
        return this.entidad;
    }
}
