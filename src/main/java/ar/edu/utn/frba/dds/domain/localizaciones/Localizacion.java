package ar.edu.utn.frba.dds.domain.localizaciones;

import ar.edu.utn.frba.dds.domain.services_api.georef.ServicioGeoref;
import ar.edu.utn.frba.dds.domain.services_api.georef.entities.*;

import javax.persistence.*;

import lombok.Getter;

import java.io.IOException;

@Entity
@Getter
public class Localizacion{
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Provincia provincia;
    @ManyToOne
    private Municipio municipio;
    @ManyToOne
    private Departamento departamento;
    @Transient
    private ServicioGeoref servicioGeoref;
    @Transient
    private static Integer maxMunicipios = 200;
    @Transient
    private static Integer maxDepartamentos = 135;

    public Localizacion() throws IOException {
        this.servicioGeoref = ServicioGeoref.getInstance();

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
