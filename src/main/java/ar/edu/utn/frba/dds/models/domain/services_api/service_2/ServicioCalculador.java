package ar.edu.utn.frba.dds.models.domain.services_api.service_2;

import ar.edu.utn.frba.dds.models.domain.services_api.georef.ServicioGeoref;
import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.ComunidadPuntaje;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioCalculador {
    private static ServicioCalculador instancia = null;
    private static final String urlAPI = "http://localhost:8088/api/";
    /* Mejor usar archivo de config para esta URL absoluta*/
    private Retrofit retrofit;

    private ServicioCalculador(){
        this.retrofit = new Retrofit.Builder().baseUrl(urlAPI).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ServicioCalculador getInstance(){
        if(instancia==null){
            instancia = new ServicioCalculador();
        }
        return instancia;
    }

/*    public ComunidadPuntaje comunidadPuntaje(RequestComunidadPuntaje requestComunidadPuntaje){
        CalculadorLlamadas calculadorLlamadas = this.retrofit.create(CalculadorLlamadas.class);
        Call<ComunidadPuntaje> llamadaComunidadPuntaje = calculadorLlamadas.comunidadPuntaje(requestComunidadPuntaje);

    }*/
}