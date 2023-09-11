package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.excepciones.SinLocalizacionValida;
import ar.edu.utn.frba.dds.excepciones.SinNombreExcepcion;
import ar.edu.utn.frba.dds.excepciones.SinTipoExcepcion;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.TipoEstablecimiento;

import java.util.HashSet;

public class EstablecimientoBuilder {
    private Establecimiento establecimiento = new Establecimiento();

    public EstablecimientoBuilder conNombre(String nombre){
        this.establecimiento.setNombre(nombre);
        return this;
    }
    public EstablecimientoBuilder conTipo(TipoEstablecimiento tipoEstablecimiento){
        this.establecimiento.setTipoEstablecimiento(tipoEstablecimiento);
        return this;
    }
    public EstablecimientoBuilder conLocalizacion(Localizacion localizacion){
        this.establecimiento.setLocalizacion(localizacion);
        return this;
    }
    public Establecimiento construir(){
        if(this.establecimiento.getNombre() == null){
            throw new SinNombreExcepcion();
        }
        if(this.establecimiento.getTipoEstablecimiento() == null){
            throw new SinTipoExcepcion();
        }
        if(this.establecimiento.getLocalizacion() == null){
            throw new SinLocalizacionValida();
        }
        this.establecimiento.setPrestacionesDeServicios(new HashSet<>());
        return this.establecimiento;
    }


}
