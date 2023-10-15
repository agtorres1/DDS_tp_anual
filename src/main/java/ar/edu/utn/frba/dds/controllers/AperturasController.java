package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.domain.incidentes.AperturaIncidente;
import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.repositories.RepoDeEstablecimientos;
import ar.edu.utn.frba.dds.models.repositories.RepoDePrestacionDeServicio;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
public class AperturasController {


    public AperturasController(RepoDePrestacionDeServicio repoDePrestacionDeServicio, RepoDeEstablecimientos repoDeEstablecimientos){
        this.repoDePrestacionDeServicio = repoDePrestacionDeServicio;
        this.repoDeEstablecimientos = repoDeEstablecimientos;
    }
    public void create(Context context){
        AperturaIncidente aperturaIncidente = null;
        List<PrestacionDeServicio> prestacionesDeServicio = this.repoDePrestacionDeServicio.buscarTodos();
        List<Establecimiento> establecimientos = this.repoDeEstablecimientos.buscarTodos();

        Map<String, Object> model = new HashMap<>();
        model.put("prestaciones", prestacionesDeServicio);
        model.put("establecimientos",establecimientos);
        model.put("apertura", aperturaIncidente);
        context.render("aperturaIncidentes/openIncidentes.hbs", model);
    }

}
*/
