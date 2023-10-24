package ar.edu.utn.frba.dds.controllers.ComunidadesController;

import ar.edu.utn.frba.dds.controllers.Controller;
import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.comunidades.gradosDeConfianza.Puntaje;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.ServicioFusionador;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.ComunidadFusionable;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.PropuestaAnterior;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.requests.RequestComunidadesFusionables;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.responses.ResponseComunidadFusionada;
import ar.edu.utn.frba.dds.repositories.RepoDeComunidades;
import ar.edu.utn.frba.dds.repositories.RepoDeIncidentes;
import ar.edu.utn.frba.dds.repositories.RepoDeMiembros;
import ar.edu.utn.frba.dds.repositories.RepoDePropuestasFusion;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ComunidadesController extends Controller {
    private RepoDeComunidades repoDeComunidades;
    private RepoDeMiembros repoDeMiembros;
    private RepoDeIncidentes repoDeIncidentes;
    private RepoDePropuestasFusion repoDePropuestasFusion;
    public ComunidadesController(RepoDeComunidades repoDeComunidades, RepoDeMiembros repoDeMiembros,RepoDeIncidentes repoDeIncidentes,RepoDePropuestasFusion repoDePropuestasFusion) {
        this.repoDeMiembros = repoDeMiembros;
        this.repoDeComunidades = repoDeComunidades;
        this.repoDeIncidentes = repoDeIncidentes;
        this.repoDePropuestasFusion = repoDePropuestasFusion;
    }

    public void index(Context context){
        Map<String, Object> model = new HashMap<>();
        Miembro miembroActual = buscarMiembroActual(context);
        List<Comunidad> comunidades = this.repoDeComunidades.buscarRestantesA(miembroActual);
        model.put("comunidadesMiembro", miembroActual.getComunidades());
        model.put("comunidades",comunidades);
        context.render("comunidades/comunidades.hbs", model);
    }

    public void join(Context context){
        if(!Objects.equals(context.formParam("comunidad"), null)) {
            Miembro miembroActual = buscarMiembroActual(context);
            Comunidad comunidad = this.repoDeComunidades.buscarPorId(Long.valueOf(context.formParam("comunidad")));
            comunidad.agregarUsuarios(miembroActual);
            this.repoDeComunidades.modificar(comunidad);
        }
        context.status(HttpStatus.CREATED);
        context.redirect("/comunidades");
    }

/*    public void fusion(Context context) throws IOException {
        if(!Objects.equals(context.formParam("comunidad1"), null) && !Objects.equals(context.formParam("comunidad2"), null)) {
            Comunidad comunidad1 = this.repoDeComunidades.buscarPorId(Long.valueOf(context.formParam("comunidad1")));
            Comunidad comunidad2 = this.repoDeComunidades.buscarPorId(Long.valueOf(context.formParam("comunidad2")));
            RequestComunidadesFusionables requestComunidadesFusionables = new RequestComunidadesFusionables();
            requestComunidadesFusionables.setComunidad1(comunidad1.comunidadFusionable());
            requestComunidadesFusionables.setComunidad2(comunidad2.comunidadFusionable());
            ResponseComunidadFusionada responseComunidadesFusionadas = ServicioFusionador.getInstance().responseComunidadesFusionadas(requestComunidadesFusionables);
            Comunidad comunidadFusionada = asignarAtributos(new Comunidad(),responseComunidadesFusionadas);
            this.repoDeComunidades.agregar(comunidadFusionada);
        }
        context.status(HttpStatus.CREATED);
        context.redirect("/comunidades");
    }*/

/*    private Comunidad asignarAtributos(Comunidad comunidad, ResponseComunidadFusionada responseComunidadesFusionadas) {
        if(responseComunidadesFusionadas.exito){
            comunidad.setMiembros(this.repoDeMiembros.buscarPorListadoId(listIntegerToLong(responseComunidadesFusionadas.resultado.usuarios)));
            comunidad.setIncidentes(this.repoDeIncidentes.buscarPorListadoId(listIntegerToLong(responseComunidadesFusionadas.resultado.incidentes)));
            comunidad.setPropuestasFusion(this.repoDePropuestasFusion.buscarPorComunidades(responseComunidadesFusionadas.resultado);
            comunidad.setPuntaje(new Puntaje(responseComunidadesFusionadas.resultado.gradoConfianza));
        }
        return comunidad;
    }*/

/*    private List<Long> listPropuestaToLong(List<PropuestaAnterior> propuestasAnteriores) {
        return propuestasAnteriores.stream().map(propuestaAnterior -> propuestaAnterior)
    }*/

    private List<Long> listIntegerToLong(List<Integer> ids) {
        return ids.stream().map(Long::valueOf).collect(Collectors.toList());
    }

    private Miembro buscarMiembroActual(Context context) {
        return this.repoDeMiembros.buscarPorId(context.sessionAttribute("usuario_id"));
    }

}
