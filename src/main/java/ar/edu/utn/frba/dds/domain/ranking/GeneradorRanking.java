package ar.edu.utn.frba.dds.domain.ranking;

import ar.edu.utn.frba.dds.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneradorRanking {
    private List<Ranking> rankings;
    private Map<Ranking, List<Entidad>> resultadosPorRanking;

    public GeneradorRanking() {
        this.rankings = new ArrayList<>();
        this.resultadosPorRanking = new HashMap<>();
    }
    public void agregarRanking(Ranking ranking){
        this.rankings.add(ranking);
    }

    public Map<Ranking, List<Entidad>> generarRankings(List<Comunidad> comunidades, List<Entidad> entidades){
        for(Ranking ranking : this.rankings){
            List<Entidad> resultados = ranking.generarRanking(comunidades, entidades);
            resultadosPorRanking.put(ranking, resultados);
        }

        return resultadosPorRanking;
    }

}
