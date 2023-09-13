package ApiRest.criterios;

import ApiRest.Entidades.Comunidad;

import java.time.LocalDate;
import java.time.Period;

public class CriterioPropuestaAnterior implements Criterio{

    private static final Integer MIN_DIFERENCIA_PROPUESTA_MESES = 6;
    private Integer fechasDistanciaMeses(LocalDate fecha1, LocalDate fecha2) {
        return Period.between(fecha1,fecha2).getYears()==0?Period.between(fecha1,fecha2).getMonths():
                Period.between(fecha1,fecha2).getYears()*12+Period.between(fecha1,fecha2).getMonths();
    }

    @Override
    public Boolean validarCriterio(Comunidad comunidad1, Comunidad comunidad2) {
        return comunidad1.getPropuestasAnteriores().containsKey(comunidad2.getId())?
                fechasDistanciaMeses(comunidad1.getPropuestasAnteriores().get(comunidad2),LocalDate.now())>=MIN_DIFERENCIA_PROPUESTA_MESES
                :false;
    }
}
