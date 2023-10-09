package ar.edu.utn.frba.dds.controllers.incidentes;

import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.repositories.RepoDeIncidentes;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IncidentesController {

    private RepoDeIncidentes repoDeIncidentes;

    public IncidentesController(RepoDeIncidentes repoDeIncidentes) {
        this.repoDeIncidentes = repoDeIncidentes;
    }


    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repoDeIncidentes.buscarTodos();
        model.put("incidentes", incidentes);
        context.render("incidentes/incidentes.hbs", model);
    }


    public void show(Context context) {
        Incidente incidente = this.repoDeIncidentes.buscarPorId((int) Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
    }


    public void create(Context context) {
/*        Usuario usuarioLogueado = super.usuarioLogueado(context);

        if(usuarioLogueado == null || !usuarioLogueado.getRol().tenesPermiso("crear_servicios")) {
            throw new AccessDeniedException();
        }*/


        Incidente incidente = null;
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);

    }


    public void save(Context context) {
        Incidente incidente = new Incidente();
        this.asignarParametros(incidente, context);
        this.repoDeIncidentes.agregar(incidente);
        context.status(HttpStatus.CREATED);
        context.redirect("/incidentes");
    }


    public void edit(Context context) {
        Incidente incidente = (Incidente) this.repoDeIncidentes.buscarPorId((int) Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
    }


    public void update(Context context) {
        Incidente incidente = (Incidente) this.repoDeIncidentes.buscarPorId((int) Long.parseLong(context.pathParam("id")));
        this.asignarParametros(incidente, context);
        this.repoDeIncidentes.modificar(incidente);
        context.redirect("/incidentes");
    }


    public void delete(Context context) {
        Incidente incidente = (Incidente) this.repoDeIncidentes.buscarPorId((int) Long.parseLong(context.pathParam("id")));
        this.repoDeIncidentes.eliminar(incidente);
        context.redirect("/incidentes");
    }

    private void asignarParametros(Incidente incidente, Context context) {
        if(!Objects.equals(context.formParam("id"), null)) {
            incidente.setId(Long.valueOf(context.formParam("id")));
        }
    }
}
