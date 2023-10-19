package ar.edu.utn.frba.dds.models.domain.services_api.service_2;

import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.ComunidadPuntaje;
import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.IncidentePuntaje;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RequestComunidadPuntaje {
    @SerializedName("incidentes")
    List<IncidentePuntaje> incidentesPuntaje;

    @SerializedName("comunidad")
    ComunidadPuntaje comunidadPuntaje;
}
