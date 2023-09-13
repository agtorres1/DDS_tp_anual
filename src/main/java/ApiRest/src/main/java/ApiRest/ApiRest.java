package ApiRest;

import ApiRest.controladores.AnalizarFusionController;
import ApiRest.controladores.FusionarComunidadesController;
//import ApiRest.serializadorMagico.LocalDateSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.javalin.Javalin;

import java.time.LocalDate;

public class ApiRest {
    public static void main( String[] args )
    {
        Integer port = Integer.parseInt(System.getProperty("port", "8082"));
        Javalin app = Javalin.create(
                /*config -> {
                    OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
                    openApiConfiguration.getInfo().setTitle("Javalin OpenAPI example");
                    config.plugins.register(new OpenApiPlugin(openApiConfiguration));
                    config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
                    config.plugins.register(new ReDocPlugin(new ReDocConfiguration()));
                }*/).start(port);

        app.post("/api/analizar-fusion", new AnalizarFusionController());
        app.post("/api/fusionar-comunidades", new FusionarComunidadesController());
    }
}
