package ApiRest.criterios;

import ApiRest.Entidades.Comunidad;

public interface Criterio {
    Boolean validarCriterio(Comunidad comunidad1, Comunidad comunidad2);
}
