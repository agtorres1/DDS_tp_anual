package ar.edu.utn.frba.dds.server;
import ar.edu.utn.frba.dds.controllers.ComunidadesController.ComunidadesController;
import ar.edu.utn.frba.dds.controllers.FactoryController;

import ar.edu.utn.frba.dds.controllers.ComunidadesController.incidentes.IncidentesController;

import ar.edu.utn.frba.dds.controllers.EntidadControladoraController;

import ar.edu.utn.frba.dds.controllers.OrganismoDeControlController;
import ar.edu.utn.frba.dds.controllers.RankingController;
import ar.edu.utn.frba.dds.controllers.UsuariosController;
import ar.edu.utn.frba.dds.models.domain.usuario.TipoRol;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;



public class Router {

    public static void init() {

        Server.app().routes(() -> {
            get("/", (ctx) -> ctx.render("base.hbs"));
            get("/login", ((UsuariosController) FactoryController.controller("Usuarios"))::login);
            post("/login", ((UsuariosController) FactoryController.controller("Usuarios"))::loginPost);
            get("/register", ((UsuariosController) FactoryController.controller("Usuarios"))::register);
            post("/register", ((UsuariosController) FactoryController.controller("Usuarios"))::registerPost);
            get("/usuarios", ((UsuariosController) FactoryController.controller("Usuarios"))::index, TipoRol.ADMINISTRADOR);
            get("/usuarios/{id}", ((UsuariosController) FactoryController.controller("Usuarios"))::mostrarUsuario);
            get("/cargar/organismosDeControl", ((OrganismoDeControlController) FactoryController.controller("OrganismosDeControl"))::cargar, TipoRol.ENTIDAD, TipoRol.ADMINISTRADOR);
            post("/cargar/organismosDeControl", ((OrganismoDeControlController) FactoryController.controller("OrganismosDeControl"))::cargarPost, TipoRol.ENTIDAD, TipoRol.ADMINISTRADOR);
            get("/cargar/entidadesControladoras", ((EntidadControladoraController) FactoryController.controller("EntidadesControladoras"))::cargar, TipoRol.ENTIDAD, TipoRol.ADMINISTRADOR);
            post("/cargar/entidadesControladoras", ((EntidadControladoraController) FactoryController.controller("EntidadesControladoras"))::cargarPost, TipoRol.ENTIDAD, TipoRol.ADMINISTRADOR);
            get("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::index);
            post("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::join);

            get("/ranking", ((RankingController) FactoryController.controller("Rankings"))::ranking);

            get("/usuario/{id}",((UsuariosController) FactoryController.controller("Usuarios")) :: show);
            get("/usuario/{id}/editar",((UsuariosController) FactoryController.controller("Usuarios")) :: edit);
            post("/usuario",((UsuariosController) FactoryController.controller("Usuarios")) :: save);
            post("/usuario/{id}",((UsuariosController) FactoryController.controller("Usuarios")) :: update);
            get("/usuario/{id}/intereses",((UsuariosController) FactoryController.controller("Usuarios"))::show);
            post("/usuario/{id}/intereses",((UsuariosController) FactoryController.controller("Usuarios"))::show);

            path("comunidades/{idComunidad}/incidentes", () -> {
                get(((IncidentesController) FactoryController.controller("Incidentes"))::index);
                get("{idIncidente}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
                post("{idIncidente}", ((IncidentesController) FactoryController.controller("Incidentes"))::close);

            });
            path("incidentes", () -> {
                get(((IncidentesController) FactoryController.controller("Incidentes"))::create);
                get("revisarIncidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::revisarIncidentes);
                post(((IncidentesController) FactoryController.controller("Incidentes"))::save);
            });

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






    }
}
