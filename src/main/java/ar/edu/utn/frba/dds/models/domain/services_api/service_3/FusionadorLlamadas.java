package ar.edu.utn.frba.dds.models.domain.services_api.service_3;

import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.requests.RequestComunidadesAnalizables;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.requests.RequestComunidadesFusionables;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.responses.ResponseComunidadFusionada;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.responses.ResponseComunidadesAnalizables;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FusionadorLlamadas {

    @POST("analizar-fusion")
    Call<ResponseComunidadesAnalizables> comunidadesAnalizables(@Body RequestComunidadesAnalizables requestComunidadesAnalizables);

    @POST("fusionar-comunidades")
    Call<ResponseComunidadFusionada> comunidadesFusionadas(@Body RequestComunidadesFusionables requestComunidadesFusionables);
}
