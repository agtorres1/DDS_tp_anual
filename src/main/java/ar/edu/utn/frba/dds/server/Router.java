package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.FactoryController;
import ar.edu.utn.frba.dds.controllers.UsuariosController;

import static io.javalin.apibuilder.ApiBuilder.*;




public class Router {

    public static void init() {

        Server.app().routes(() ->{
           get("/login",((UsuariosController) FactoryController.controller("Usuarios"))::login);
           post("/login", ((UsuariosController) FactoryController.controller("Usuarios"))::loginPost);
           get("/usuarios", ((UsuariosController) FactoryController.controller("Usuarios"))::index);
        });

        Server.app().get("/", ctx -> {
                    ctx.sessionAttribute("item1", "Cosa 1");
                    ctx.result("Hola mundo");
        });
    }
}
