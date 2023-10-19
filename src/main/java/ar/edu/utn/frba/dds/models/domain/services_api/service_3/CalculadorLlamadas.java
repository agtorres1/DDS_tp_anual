package ar.edu.utn.frba.dds.models.domain.services_api.service_3;

import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.ComunidadPuntaje;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CalculadorLlamadas {

    @POST("calcularPuntaje")
    Call<ComunidadPuntaje> comunidadPuntaje(@Body RequestComunidadPuntaje requestComunidadPuntaje);
}
