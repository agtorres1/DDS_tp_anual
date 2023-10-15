package ar.edu.utn.frba.dds.controllers.ComunidadesController.incidentes;

import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Notificador;
import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.incidentes.AperturaIncidente;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.repositories.*;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IncidentesController {
    private RepoDeIncidentes repoDeIncidentes;
    private RepoDeComunidades repoDeComunidades;
    private RepoDeMiembros repoDeMiembros;
    private RepoDePrestacionDeServicio repoDePrestacionDeServicio;
    private RepoDeEstablecimientos repoDeEstablecimientos;

    public IncidentesController(RepoDeComunidades repoDeComunidades,RepoDeIncidentes repoDeIncidentes,RepoDePrestacionDeServicio repoDePrestacionDeServicio,RepoDeEstablecimientos repoDeEstablecimientos, RepoDeMiembros repoDeMiembros) {
        this.repoDeIncidentes = repoDeIncidentes;
        this.repoDeComunidades = repoDeComunidades;
        this.repoDePrestacionDeServicio = repoDePrestacionDeServicio;
        this.repoDeEstablecimientos = repoDeEstablecimientos;
        this.repoDeMiembros = repoDeMiembros;
    }


    public void index(Context context) {
        Comunidad comunidad = this.repoDeComunidades.buscarPorId(Long.parseLong(context.pathParam("idComunidad")));
        Map<String, Object> model = new HashMap<>();
        model.put("comunidad", comunidad);
        model.put("incidentes", comunidad.getIncidentes());
        context.render("comunidades/incidentes/incidentes.hbs", model);
    }




    public void show(Context context) {
        Comunidad comunidad = this.repoDeComunidades.buscarPorId(Long.parseLong(context.pathParam("idComunidad")));
        Incidente incidente = this.repoDeIncidentes.buscarPorId(Long.parseLong(context.pathParam("idIncidente")));
        Map<String, Object> model = new HashMap<>();
        model.put("comunidad",comunidad);
        model.put("incidente", incidente);
        context.render("comunidades/incidentes/incidente.hbs", model);
    }


    public void create(Context context) {
/*        Usuario usuarioLogueado = super.usuarioLogueado(context);

        if(usuarioLogueado == null || !usuarioLogueado.getRol().tenesPermiso("crear_servicios")) {
            throw new AccessDeniedException();
        }*/
        List<PrestacionDeServicio> prestacionesDeServicio = this.repoDePrestacionDeServicio.buscarPorFuncionamiento(false);
        List<Establecimiento> establecimientos = this.repoDeEstablecimientos.buscarTodos();

        Map<String, Object> model = new HashMap<>();
        model.put("prestaciones", prestacionesDeServicio);
        model.put("establecimientos",establecimientos);
        context.render("aperturaIncidentes/openIncidentes.hbs", model);
    }


    public void save(Context context) {
        AperturaIncidente aperturaIncidente = new AperturaIncidente();
        this.asignarParametros(aperturaIncidente, context);
        System.out.println(aperturaIncidente.getFechaYHoraApertura());

        Comunidad comunidadX = this.repoDeComunidades.buscarPorId(4L);
        comunidadX.getMiembros().get(0).abrirIncidente(aperturaIncidente,new Notificador());
        List<Incidente> incidentes = comunidadX.getMiembros().get(0).buscarIncidentes(aperturaIncidente);
        System.out.println(incidentes);
        for(Incidente incidente : incidentes){

            this.repoDePrestacionDeServicio.modificar(incidente.getPrestacionDeServicio());
            this.repoDeEstablecimientos.modificar(incidente.getEstablecimiento());
            this.repoDeMiembros.modificar(incidente.getAbridor());
            this.repoDeIncidentes.agregar(incidente);
        }

        context.status(HttpStatus.CREATED);
        context.redirect("/comunidades");
    }

    public void close(Context context){
        System.out.println("Estoy en close -----------------------------");
        Incidente incidente = this.repoDeIncidentes.buscarPorId(Long.parseLong(context.pathParam("idIncidente")));
        Comunidad comunidad = this.repoDeComunidades.buscarPorId(Long.parseLong(context.pathParam("idComunidad")));
        comunidad.cerrarIncidente(comunidad.getMiembros().get(0),incidente);
        this.repoDeIncidentes.modificar(incidente);

        System.out.println(incidente.getAbierto());

        context.status(HttpStatus.CREATED);
        context.redirect("/comunidades/"+comunidad.getId()+"/incidentes");
    }


/*    public void edit(Context context) {
        Incidente incidente = (Incidente) this.repoDeIncidentes.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("comunidades/incidentes/incidente.hbs", model);
    }


    public void update(Context context) {
        Incidente incidente = (Incidente) this.repoDeIncidentes.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.asignarParametros(incidente, context);
        this.repoDeIncidentes.modificar(incidente);
        context.redirect("/comunidades/incidentes");
    }*/




    public void delete(Context context) {
        Incidente incidente = (Incidente) this.repoDeIncidentes.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.repoDeIncidentes.eliminar(incidente);
        context.redirect("/comunidades/incidentes");
    }

    private void asignarParametros(AperturaIncidente aperturaIncidente, Context context) {
        if(!Objects.equals(context.formParam("prestacion"), "")) {
            PrestacionDeServicio prestacionDeServicio = this.repoDePrestacionDeServicio.buscarPorId(Long.valueOf(context.formParam("prestacion")));
            aperturaIncidente.setPrestacionDeServicio(prestacionDeServicio);
            System.out.println(prestacionDeServicio);
        }

        if(!Objects.equals(context.formParam("establecimiento"), "")) {
            Establecimiento establecimiento = this.repoDeEstablecimientos.buscarPorId(Long.valueOf(context.formParam("establecimiento")));
            aperturaIncidente.setEstablecimiento(establecimiento);
            System.out.println(establecimiento);
        }

        if(!Objects.equals(context.formParam("observaciones"), "")){
            aperturaIncidente.setObservaciones(context.formParam("observaciones"));
        }


    }
}
