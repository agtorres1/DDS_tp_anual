package ar.edu.utn.frba.dds.models.domain.services_api.service_2;

import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.ComunidadPuntaje;
import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.IncidentePuntaje;
import lombok.Setter;

import java.util.List;

@Setter
public class RequestComunidadPuntaje {
    List<IncidentePuntaje> incidentesPuntaje;
    ComunidadPuntaje comunidadPuntaje;

}
