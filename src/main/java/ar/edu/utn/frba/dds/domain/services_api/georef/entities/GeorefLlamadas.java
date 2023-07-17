package ar.edu.utn.frba.dds.domain.services_api.georef.entities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefLlamadas {
    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("campos") String campos);

    @GET("municipios")
    Call<ListadoDeMunicipiosDeProvincia> municipios(@Query("campos") String campos, @Query("provincia") int idProvincia, @Query("max") int max);

    @GET("departamentos")
    Call<ListadoDeDepartamentosDeProvincia> departamentos(@Query("campos") String campos, @Query("provincia") int idProvincia, @Query("max") int max);

}
