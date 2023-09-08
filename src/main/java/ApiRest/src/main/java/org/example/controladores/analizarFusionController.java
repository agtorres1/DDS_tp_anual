package ApiRest.src.main.java.org.example.controladores;

import ApiRest.src.main.java.org.example.Entidades.FusionRequest;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

public class analizarFusionController implements Handler {
    @Override
    public void handle(@NotNull Context context) throws Exception {
        FusionRequest request = context.bodyAsClass(FusionRequest.class);

        //todo

        context.status(HttpStatus.OK);
        context.result("test" + request.getComunidades().get(0).getId());
    }
}
