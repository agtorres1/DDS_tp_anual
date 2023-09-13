package ApiRest.criterios;

import ApiRest.Entidades.Comunidad;
import ApiRest.Entidades.PropuestaAnterior;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class CriterioPropuestaAnterior implements Criterio{
    private static final Integer MIN_DIFERENCIA_PROPUESTA_MESES = 6;

    @Override
    public Boolean validarCriterio(Comunidad comunidad1, Comunidad comunidad2) {

        Optional<PropuestaAnterior> propuestaAnterior = comunidad1.getPropuestasAnteriores()
                .stream()
                .filter(prop -> prop.getIdComunidad() == comunidad2.getId())
                .findFirst();

        if(propuestaAnterior.isPresent()){
            LocalDate fechaPropuesta = propuestaAnterior.get().getFechaComoLocalDate();
            long diferenciaMeses = ChronoUnit.MONTHS.between(fechaPropuesta, LocalDate.now());

            return diferenciaMeses >= MIN_DIFERENCIA_PROPUESTA_MESES;
        }

        return true;
    }
}
