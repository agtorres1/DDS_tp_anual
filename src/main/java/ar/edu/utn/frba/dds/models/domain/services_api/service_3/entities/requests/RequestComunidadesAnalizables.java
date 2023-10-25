package ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.requests;

import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.ComunidadFusionable;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RequestComunidadesAnalizables {

    @SerializedName("comunidades")
    public List<ComunidadFusionable> comunidades;

}
