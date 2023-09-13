package ApiRest.controladores;

import ApiRest.Entidades.ApiResponse;
import ApiRest.Entidades.Comunidad;
import ApiRest.Entidades.FusionRequest;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import io.javalin.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class FusionarComunidadesController implements Handler {
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

            respuesta.setResultado(comunidadFusionada);
            context.status(HttpStatus.OK);
            respuesta.setExito(true);
            respuesta.setCodigoDeEstado(HttpStatus.OK.getCode());
            context.result("Fusion realizada");
        }
        catch(Exception e){
            respuesta.setExito(false);
            respuesta.setError(e.getMessage());
            respuesta.setCodigoDeEstado(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
            context.status(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
        }
        context.json(respuesta);
    }

    private List<Long> fusionarListas(List<Long> lista1, List<Long> lista2){
        List<Long> listaFusionada = new ArrayList<>(lista1);
        for(Long elemento : lista2){
            if (!listaFusionada.contains(elemento)) {
                listaFusionada.add(elemento);
            }
        }
        return listaFusionada;
    }
}
