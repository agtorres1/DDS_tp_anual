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
    private List<Ranking> rankings;
    public RankingController (RepoDeComunidades repoDeComunidades, RepoDeEntidades repoDeEntidades) {
        this.repoDeComunidades = repoDeComunidades;
        this.repoDeEntidades = repoDeEntidades;
        this.generadorRanking = new GeneradorRanking();
        this.rankings.add(new RankingMayorCantidadIncidentes());
        this.rankings.add(new RankingMayorPromedioCierre());
        this.rankings.add(new RankingMayorGradoImpactoProblematicas());
        for (Ranking ranking : rankings) {
            this.generadorRanking.agregarRanking(ranking);
        }
    }
    public void ranking(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put
                ("ranking",
                 this.generadorRanking.
                         generarRankings(this.repoDeComunidades.buscarTodos(), this.repoDeEntidades.buscarTodos()).
                         get(this.rankings.get(0))
                );
        context.render("ranking/ranking.hbs", model);
    }
}
