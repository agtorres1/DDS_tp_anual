package FusionadorDeComunidades;

import FusionadorDeComunidades.controladores.AnalizarFusionController;
import FusionadorDeComunidades.controladores.FusionarComunidadesController;
//import ApiRest.serializadorMagico.LocalDateSerializer;
import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

import static io.javalin.apibuilder.ApiBuilder.*;
public class ApiRest {
    public static void main( String[] args )
    {
        Integer port = Integer.parseInt(System.getProperty("port", "8082"));
        //SwaggerConfiguration swaggerConfig = new SwaggerConfiguration();
        //swaggerConfig.setBasePath("/api");
        Javalin app = Javalin.create(
                config -> {
                    OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
                    openApiConfiguration.getInfo().setTitle("Javalin OpenAPI example");
                    config.plugins.register(new OpenApiPlugin(openApiConfiguration));
                    config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
                    config.plugins.register(new ReDocPlugin(new ReDocConfiguration()));
                }).routes(()->{
                    path("api/analizar-fusion", () ->{
                        post(new AnalizarFusionController()::handle);
                    });
                    path("api/fusionar-comunidades", () ->{
                        post(new FusionarComunidadesController()::handle);
            });
        }).start(port);
    }
}
