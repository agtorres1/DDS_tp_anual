package ar.edu.utn.frba.dds.domain.localizaciones;

import ar.edu.utn.frba.dds.domain.comunidades.Interes;
import ar.edu.utn.frba.dds.domain.services_api.georef.ServicioGeoref;
import ar.edu.utn.frba.dds.domain.services_api.georef.entities.Departamento;
import ar.edu.utn.frba.dds.domain.services_api.georef.entities.ListadoDeProvincias;
import ar.edu.utn.frba.dds.domain.services_api.georef.entities.Municipio;
import ar.edu.utn.frba.dds.domain.services_api.georef.entities.Provincia;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
@Getter
public class Localizacion{
    private Provincia provincia;
    private Municipio municipio;
    private Departamento departamento;
    private ServicioGeoref servicioGeoref;
    private static Integer maxMunicipios = 200;
    private static Integer maxDepartamentos = 135;

    public Localizacion(ServicioGeoref servicioGeoref,String provincia) throws IOException {
        this.servicioGeoref = servicioGeoref;
        if(this.servicioGeoref.buscarProvincia(provincia) == null){
            throw new IllegalArgumentException("No es una provincia del listado requerido");
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


}
