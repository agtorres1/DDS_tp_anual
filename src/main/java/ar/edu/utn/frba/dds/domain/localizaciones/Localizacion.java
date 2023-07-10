package ar.edu.utn.frba.dds.domain.localizaciones;

import ar.edu.utn.frba.dds.domain.comunidades.Interes;
import ar.edu.utn.frba.dds.domain.services_api.georef.ServicioGeoref;
import ar.edu.utn.frba.dds.domain.services_api.georef.entities.*;
import lombok.Getter;

import java.io.IOException;

@Getter
public class Localizacion{
    private Provincia provincia;
    private Municipio municipio;
    private Departamento departamento;
    private ServicioGeoref servicioGeoref;
    private static Integer maxMunicipios = 200;
    private static Integer maxDepartamentos = 135;

    public Localizacion(String provincia) throws IOException {
        this.servicioGeoref = ServicioGeoref.getInstance();
        if(this.servicioGeoref.buscarProvincia(provincia) == null){
            throw new IllegalArgumentException("No es una provincia del listado requerido");
        }
        this.provincia = this.servicioGeoref.buscarProvincia(provincia);
    }

    public void setProvincia(String provincia) throws IOException {
        if(this.servicioGeoref.buscarProvincia(provincia) == null){
            throw new IllegalArgumentException("No es un municipio del listado requerido");
        }
        this.provincia = this.servicioGeoref.buscarProvincia(provincia);
    }
    public void setMunicipio(String municipio) throws IOException {
        if(this.servicioGeoref.buscarMunicipio(municipio,this.provincia.id,this.maxMunicipios) == null){
            throw new IllegalArgumentException("No es un municipio del listado requerido");
        }
        this.municipio = this.servicioGeoref.buscarMunicipio(municipio,this.provincia.id,this.maxMunicipios);
    }

    public void setDepartamento(String departamento) throws IOException {
        if(this.servicioGeoref.buscarDepartamento(departamento,this.provincia.id,this.maxDepartamentos) == null){
            throw new IllegalArgumentException("No es un departamento del listado requerido");
        }
        this.departamento = this.servicioGeoref.buscarDepartamento(departamento,this.provincia.id,this.maxDepartamentos);
    }

    public Integer getMaxMunicipios(){
        return this.maxMunicipios;
    }

    public Integer getMaxDepartamentos(){
        return this.maxDepartamentos;
    }
}
