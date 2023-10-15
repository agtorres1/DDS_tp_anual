package ar.edu.utn.frba.dds.server;
import ar.edu.utn.frba.dds.controllers.ComunidadesController.ComunidadesController;
import ar.edu.utn.frba.dds.controllers.FactoryController;
import ar.edu.utn.frba.dds.controllers.ComunidadesController.incidentes.IncidentesController;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Router {

    public static void init() {
        Server.app().routes(() -> {
            get("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::index);
            post("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::join);
            get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::create);
            post("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::save);


            path("comunidades/{idComunidad}/incidentes", () -> {
                get(((IncidentesController) FactoryController.controller("Incidentes"))::index);
                get("{idIncidente}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
                post("{idIncidente}", ((IncidentesController) FactoryController.controller("Incidentes"))::close);
            });
        });
    }


}
