package ApiRest.src.main.java.org.example.controladores;

import ApiRest.src.main.java.org.example.Entidades.Comunidad;
import ApiRest.src.main.java.org.example.Entidades.ComunidadSugerencia;
import ApiRest.src.main.java.org.example.Entidades.SugerenciaRequest;
import ApiRest.src.main.java.org.example.Entidades.ApiResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class analizarFusionController implements Handler {

    private static final double MIN_COINCIDENCIA_ESTABLECIMIENTO = 0.75;
    private static final double MIN_COINCIDENCIA_SERVICIOS = 0.75;
    private static final double MIN_COINCIDENCIA_USUARIOS = 0.05;

    @Override
    public void handle(@NotNull Context context) throws Exception {
        SugerenciaRequest request = context.bodyAsClass(SugerenciaRequest.class);

        ApiResponse response = new ApiResponse();

        context.status(HttpStatus.OK);
        context.result("test" + request.getComunidades().get(0).getId());
    }
}
