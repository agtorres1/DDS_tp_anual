package ar.edu.utn.frba.dds.controllers.ComunidadesController;

import ar.edu.utn.frba.dds.controllers.Controller;
import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.models.repositories.RepoDeComunidades;
import ar.edu.utn.frba.dds.repositories.RepoDeMiembros;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.*;

public class ComunidadesController extends Controller {
    private RepoDeComunidades repoDeComunidades;
    private RepoDeMiembros repoDeMiembros;
    public ComunidadesController(RepoDeComunidades repoDeComunidades, RepoDeMiembros repoDeMiembros) {
        this.repoDeMiembros = repoDeMiembros;
        this.repoDeComunidades = repoDeComunidades;
    }

    public void index(Context context){
        Map<String, Object> model = new HashMap<>();
        System.out.println((Long) context.sessionAttribute("usuario_id"));
        List<Comunidad> comunidades = this.repoDeComunidades.buscarTodos();
        Miembro miembro = this.repoDeMiembros.buscarPorId(context.sessionAttribute("usuario_id"));

        model.put("comunidadesMiembro", miembro.getComunidades());
        model.put("comunidades",comunidades);
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
