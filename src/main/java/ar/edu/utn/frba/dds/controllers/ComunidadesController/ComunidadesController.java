package ar.edu.utn.frba.dds.controllers.ComunidadesController;

import ar.edu.utn.frba.dds.controllers.Controller;
import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.models.repositories.RepoDeComunidades;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.*;

public class ComunidadesController extends Controller {
    private RepoDeComunidades repoDeComunidades;
    public ComunidadesController(RepoDeComunidades repoDeComunidades) {
        this.repoDeComunidades = repoDeComunidades;
    }

    public void index(Context context){
        Map<String, Object> model = new HashMap<>();
        List<Comunidad> comunidades = this.repoDeComunidades.buscarTodos();
        model.put("comunidades", comunidades);
        context.render("comunidades/comunidades.hbs", model);
    }
    public void join(Context context){
        if(!Objects.equals(context.formParam("comunidad"), "")) {
            Comunidad comunidad = this.repoDeComunidades.buscarPorId(Long.valueOf(context.formParam("comunidad")));
            comunidad.agregarUsuarios(); //Usuario actual
        }
        context.status(HttpStatus.CREATED);
        context.redirect("/comunidades");
    }

    private void asignarParametros(Context context, Comunidad comunidad) {

    }
}
