package ar.edu.utn.frba.dds.domain.ranking;

import java.util.List;
import java.util.Map;
import ar.edu.utn.frba.dds.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;


public interface Ranking {
    List<Entidad> generarRanking(List<Comunidad> comunidades, List<Entidad> entidades);
}
