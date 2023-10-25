package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.controllers.Controller;
import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.ranking.*;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.repositories.RepoDeComunidades;
import ar.edu.utn.frba.dds.repositories.RepoDeEntidades;
import ar.edu.utn.frba.dds.repositories.RepoEntidadControladora;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingController extends Controller {
    private RepoDeComunidades repoDeComunidades;
    private RepoDeEntidades repoDeEntidades;
    private GeneradorRanking generadorRanking;
    public RankingController() {
        this.generadorRanking = GeneradorRanking.getInstance();
    }
    public void ranking(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put
                ("ranking",
                 this.generadorRanking.getResultadosRanking().
                         getResultados().get(RankingMayorCantidadIncidentes.getInstance())
                );
        context.render("ranking/ranking.hbs", model);
    }
}
