package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.EntidadControladoraController;
import ar.edu.utn.frba.dds.controllers.FactoryController;
import ar.edu.utn.frba.dds.controllers.OrganismoDeControlController;
import ar.edu.utn.frba.dds.controllers.UsuariosController;
import ar.edu.utn.frba.dds.models.domain.usuario.TipoRol;

import static io.javalin.apibuilder.ApiBuilder.*;




public class Router {

    public static void init() {

        Server.app().routes(() ->{
           get("/login",((UsuariosController) FactoryController.controller("Usuarios"))::login);
           post("/login", ((UsuariosController) FactoryController.controller("Usuarios"))::loginPost);
           get("/register",((UsuariosController) FactoryController.controller("Usuarios"))::register);
           post("/register", ((UsuariosController) FactoryController.controller("Usuarios"))::registerPost);
           get("/usuarios", ((UsuariosController) FactoryController.controller("Usuarios"))::index, TipoRol.ADMINISTRADOR);
           get("/usuarios/{id}", ((UsuariosController) FactoryController.controller("Usuarios"))::mostrarUsuario);
           get("/cargar/organismosDeControl", ((OrganismoDeControlController) FactoryController.controller("OrganismosDeControl"))::cargar, TipoRol.ADMINISTRADOR);
           post("/cargar/organismosDeControl", ((OrganismoDeControlController) FactoryController.controller("OrganismosDeControl"))::cargarPost, TipoRol.ADMINISTRADOR);
           get("/cargar/entidadesControlaras", ((EntidadControladoraController) FactoryController.controller("EntidadesControladoras"))::cargar, TipoRol.ADMINISTRADOR);
           post("/cargar/entidadesControlaras", ((EntidadControladoraController) FactoryController.controller("EntidadesControladoras"))::cargarPost, TipoRol.ADMINISTRADOR);
        });

        Server.app().get("/", ctx -> {  //pantalla de inicio
                    ctx.sessionAttribute("item1", "Cosa 1");
                    ctx.render("base.hbs");
        });
    }
}
