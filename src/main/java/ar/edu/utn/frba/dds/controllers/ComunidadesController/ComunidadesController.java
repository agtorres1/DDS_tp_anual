package ar.edu.utn.frba.dds.controllers.ComunidadesController;

import ar.edu.utn.frba.dds.controllers.Controller;
import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.comunidades.gradosDeConfianza.Puntaje;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.ServicioFusionador;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.ComunidadFusionable;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.PropuestaAnterior;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.SugerenciaFusion;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.requests.RequestComunidadesAnalizables;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.requests.RequestComunidadesFusionables;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.responses.ResponseComunidadFusionada;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.responses.ResponseComunidadesAnalizables;
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
        if (miembroEnSesion(context) == null) {
            context.redirect("/login");
        }
        Map<String, Object> model = new HashMap<>();
        Miembro miembroActual = miembroEnSesion(context);
        List<Comunidad> comunidades = this.repoDeComunidades.buscarRestantesA(miembroActual);
        model.put("comunidadesMiembro", miembroActual.getComunidades());
        model.put("comunidades",comunidades);
        context.render("comunidades/comunidades.hbs", model);
    }

    public void join(Context context){
        if(!Objects.equals(context.formParam("comunidad"), null)) {
            Miembro miembroActual = miembroEnSesion(context);
            Comunidad comunidad = this.repoDeComunidades.buscarPorId(Long.valueOf(context.formParam("comunidad")));
            comunidad.agregarUsuarios(miembroActual);
            this.repoDeComunidades.modificar(comunidad);
        }
        context.status(HttpStatus.CREATED);
        context.redirect("/comunidades");
    }

    public void analysis(Context context) throws IOException {
        Map<String, Object> model = new HashMap<>();
        List<ComunidadFusionable> comunidadesFusion = this.repoDeComunidades.buscarTodos().stream().map(Comunidad::comunidadFusionable).collect(Collectors.toList());
        RequestComunidadesAnalizables requestComunidadesAnalizables = new RequestComunidadesAnalizables();
        requestComunidadesAnalizables.setComunidades(comunidadesFusion);

        ResponseComunidadesAnalizables responseComunidadesAnalizables = ServicioFusionador.getInstance().responseComunidadesAnalizables(requestComunidadesAnalizables);
        List<List<Comunidad>> comunidades = responseComunidadesAnalizables.resultado.stream().map(this::comunidadesSugeridas).collect(Collectors.toList());
        model.put("comunidades",comunidades);
        context.render("comunidades/comunidadesFusionables.hbs", model);
    }

    private List<Comunidad> comunidadesSugeridas(SugerenciaFusion sugerenciaFusion) {
        List<Comunidad> comunidades = new ArrayList<>();
        comunidades.add(this.repoDeComunidades.buscarPorId((long) sugerenciaFusion.comunidad1.id));
        comunidades.add(this.repoDeComunidades.buscarPorId((long) sugerenciaFusion.comunidad2.id));
        return comunidades;
    }

    public void fusion(Context context) throws IOException {
        if(!Objects.equals(context.formParam("comunidad1"), null) && !Objects.equals(context.formParam("comunidad2"), null)) {
            Comunidad comunidad1 = this.repoDeComunidades.buscarPorId(Long.valueOf(context.formParam("comunidad1")));
            Comunidad comunidad2 = this.repoDeComunidades.buscarPorId(Long.valueOf(context.formParam("comunidad2")));
            RequestComunidadesFusionables requestComunidadesFusionables = new RequestComunidadesFusionables();
            requestComunidadesFusionables.setComunidad1(comunidad1.comunidadFusionable());
            requestComunidadesFusionables.setComunidad2(comunidad2.comunidadFusionable());
            ResponseComunidadFusionada responseComunidadesFusionadas = ServicioFusionador.getInstance().responseComunidadesFusionadas(requestComunidadesFusionables);
            Comunidad comunidadFusionada = asignarAtributos(comunidad1,comunidad2,responseComunidadesFusionadas);
            this.repoDeComunidades.eliminar(comunidad1);
            this.repoDeComunidades.eliminar(comunidad2);
            this.repoDeComunidades.agregar(comunidadFusionada);
        }
        context.status(HttpStatus.CREATED);
        context.redirect("/comunidades");
    }

    private Comunidad asignarAtributos(Comunidad comunidad1,Comunidad comunidad2, ResponseComunidadFusionada responseComunidadesFusionadas) {
        if(responseComunidadesFusionadas.exito){
            Comunidad comunidadNueva = new Comunidad();
            comunidadNueva.setMiembros(buscarMiembros(listIntegerToLong(responseComunidadesFusionadas.resultado.usuarios)));
            comunidadNueva.setIncidentes(buscarIncidentes(listIntegerToLong(responseComunidadesFusionadas.resultado.incidentes)));
            comunidadNueva.setNombre("Fusión de "+comunidad1.getNombre()+" y "+comunidad2.getNombre());
            comunidadNueva.setDescripcion("Comunidad 1: "+comunidad1.getDescripcion() + "\n"
            + "Comunidad 2: "+comunidad2.getDescripcion());
            Puntaje puntaje = new Puntaje();
            puntaje.setValor(responseComunidadesFusionadas.resultado.gradoConfianza);
            comunidadNueva.setPuntaje(puntaje);
            return comunidadNueva;
        }
        return null;
    }

    private List<Incidente> buscarIncidentes(List<Long> ids) {
        return ids.stream().map(id->this.repoDeIncidentes.buscarPorId(id)).collect(Collectors.toList());
    }

    private List<Miembro> buscarMiembros(List<Long> ids) {
        return ids.stream().map(id->this.repoDeMiembros.buscarPorId(id)).collect(Collectors.toList());
    }

    private List<Long> listIntegerToLong(List<Integer> ids) {
        return ids.stream().map(Long::valueOf).collect(Collectors.toList());
    }


}
