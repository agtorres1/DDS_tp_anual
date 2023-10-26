package ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.requests;

import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.ComunidadFusionable;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
public class RequestComunidadesFusionables {

    @SerializedName("comunidad1")
    ComunidadFusionable comunidad1;

    @SerializedName("comunidad2")
    ComunidadFusionable comunidad2;
}
