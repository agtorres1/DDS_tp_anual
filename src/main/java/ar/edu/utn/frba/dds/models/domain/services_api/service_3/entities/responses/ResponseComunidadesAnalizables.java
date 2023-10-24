package ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.responses;

import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.ComunidadFusionable;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.SugerenciaFusion;

import java.util.List;

public class ResponseComunidadesAnalizables {
    public int codigoDeEstado;
    public boolean error;
    public boolean exito;
    public List<SugerenciaFusion> resultado;
}
