package ar.edu.utn.frba.dds.server;
import ar.edu.utn.frba.dds.controllers.FactoryController;
import ar.edu.utn.frba.dds.controllers.incidentes.IncidentesController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Router {

    public static void init() {
        Server.app().routes(() -> {
            path("incidentes", () -> {
            get(((IncidentesController) FactoryController.controller("Incidentes"))::index);
            post(((IncidentesController) FactoryController.controller("Incidentes"))::save);
            get("revisarIncidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::revisarIncidentes);
            get("crear", ((IncidentesController) FactoryController.controller("Incidentes"))::create);
            get("{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
            get("{id}/editar", ((IncidentesController) FactoryController.controller("Incidentes"))::edit);
            post("{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);
            delete("{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::delete);


            });
//            get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
//          get("incidentes/crear", ((IncidentesController) FactoryController.controller("Incidentes"))::create);
//            get("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
//            get("incidentes/{id}/editar", ((IncidentesController) FactoryController.controller("Incidentes"))::edit);
//            post("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);
//            post("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::save);
//            delete("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::delete);
//            path("login", () -> {
//
//                get();
//
//
//            });





            post("/ubicacion", ctx -> {
                // Obtén el cuerpo de la solicitud como una cadena JSON
                String body = ctx.body();

                // Convierte la cadena JSON en un objeto Java usando una biblioteca como Jackson o Gson
                ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper
                Map<String, String> ubicacionData = objectMapper.readValue(body, new TypeReference<Map<String, String>>() {});

                String latitud = ubicacionData.get("latitud");
                String longitud = ubicacionData.get("longitud");

                // Haz algo con la ubicación (almacenar en una base de datos, procesar, etc.)
                System.out.println("Ubicación recibida - Latitud: " + latitud + ", Longitud: " + longitud);

                // Responde al cliente
                ctx.json(new HashMap<String, String>() {
                    {
                        put("mensaje", "Ubicación recibida correctamente");
                    }});
            });
        });

      //  Server.app().

            /*get("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::index);
            get("servicios/crear", ((ServiciosController) FactoryController.controller("Servicios"))::create);
            get("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::show);
            get("servicios/{id}/editar", ((ServiciosController) FactoryController.controller("Servicios"))::edit);
            post("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::update);
            post("servicios", ((ServiciosController) FactoryController.controller("Servicios"))::save);
            delete("servicios/{id}", ((ServiciosController) FactoryController.controller("Servicios"))::delete);
*/
/*            path("servicios/{id}/tareas", () -> {
                get(((TareasController) FactoryController.controller("Tareas"))::index);
                //TODO
            });
        });*/
    }


}
