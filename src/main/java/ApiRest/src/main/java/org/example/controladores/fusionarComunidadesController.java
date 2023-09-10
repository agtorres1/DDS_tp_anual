package ApiRest.src.main.java.org.example.controladores;

import ApiRest.src.main.java.org.example.Entidades.ApiResponse;
import ApiRest.src.main.java.org.example.Entidades.Comunidad;
import ApiRest.src.main.java.org.example.Entidades.FusionRequest;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import io.javalin.http.HttpStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class fusionarComunidadesController implements Handler {
    @Override
    public void handle(@NotNull Context context) throws Exception {
        ApiResponse<Comunidad> respuesta = new ApiResponse<>();
        try {
            FusionRequest request = context.bodyAsClass(FusionRequest.class);
            Comunidad comunidadFusionada = new Comunidad();

            Comunidad comunidad1 = request.getComunidad1();
            Comunidad comunidad2 = request.getComunidad2();

            comunidadFusionada.setEstablecimientos(fusionarListas(comunidad1.getEstablecimientos(), comunidad2.getEstablecimientos()));
            comunidadFusionada.setServicios(fusionarListas(comunidad1.getServicios(), comunidad2.getServicios()));
            comunidadFusionada.setGradoConfianza(comunidad1.getGradoConfianza());
            comunidadFusionada.setUsuarios(fusionarListas(comunidad1.getUsuarios(), comunidad2.getUsuarios()));
            comunidadFusionada.setIncidentes(fusionarListas(comunidad1.getIncidentes(), comunidad2.getIncidentes()));


            respuesta.setExito(true);
            respuesta.setCodigoDeEstado(HttpStatus.OK.getCode());
            respuesta.setResultado(comunidadFusionada);

            context.status(HttpStatus.OK);
        }
        catch(Exception e){
            respuesta.setExito(false);
            respuesta.setCodigoDeEstado(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
        }
    context.json(respuesta);
    }

    private List<String> fusionarListas(List<String> lista1, List<String> lista2){
        List<String> listaFusionada = new ArrayList<String>(lista1);
        for(String elemento : lista2){
            if (!listaFusionada.contains(elemento)) {
                listaFusionada.add(elemento);
            }
        }
        return listaFusionada;
    }
}
