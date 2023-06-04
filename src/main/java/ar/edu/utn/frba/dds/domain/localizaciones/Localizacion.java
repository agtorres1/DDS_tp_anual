package ar.edu.utn.frba.dds.domain.localizaciones;

import ar.edu.utn.frba.dds.domain.services_api.georef.entities.Departamento;
import ar.edu.utn.frba.dds.domain.services_api.georef.entities.ListadoDeProvincias;
import ar.edu.utn.frba.dds.domain.services_api.georef.entities.Municipio;
import ar.edu.utn.frba.dds.domain.services_api.georef.entities.Provincia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter @Getter
public class Localizacion{
    private Provincia provincia;
    private Municipio municipio;
    private Departamento departamento;


}
