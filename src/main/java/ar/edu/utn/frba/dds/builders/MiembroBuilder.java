package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.excepciones.*;

import java.util.ArrayList;

public class MiembroBuilder {
    private Miembro miembro = new Miembro();

    public MiembroBuilder conCuenta(String usuario, String clave){
        this.miembro.setUsuario(usuario);
        this.miembro.setClave(clave);
        return this;
    }
    public MiembroBuilder conLocalizacion(Localizacion localizacion){
        this.miembro.setLocalizacion(localizacion);
        return this;
    }
    public Miembro construir(){
        if(this.miembro.getUsuario() == null){
            throw new SinUsuarioExcepcion();
        }
        if(this.miembro.getClave() == null){
            throw new SinContraseniaExcepcion();
        }
        if(this.miembro.getLocalizacion() == null){
            throw new SinLocalizacionValida();
        }
        return this.miembro;
    }
}