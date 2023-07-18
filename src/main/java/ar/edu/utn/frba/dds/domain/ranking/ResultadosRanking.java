package ar.edu.utn.frba.dds.domain.ranking;

import ar.edu.utn.frba.dds.domain.entidadesExtra.EntidadControladora;
import ar.edu.utn.frba.dds.domain.entidadesExtra.OrganismoDeControl;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class ResultadosRanking {
    private Map<Ranking, List<Entidad>> resultados;
    private LocalDateTime fechaCreacion;

    public ResultadosRanking(){
        this.resultados = new HashMap<>();
        this.fechaCreacion = LocalDateTime.now();
    }

    public void agregarRanking(Ranking ranking,List<Entidad> resultadoRanking){
        this.resultados.put(ranking, resultadoRanking);
    }
}
