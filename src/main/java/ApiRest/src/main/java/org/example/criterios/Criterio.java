package ApiRest.src.main.java.org.example.criterios;

import ApiRest.src.main.java.org.example.Entidades.Comunidad;

public interface Criterio {
    Boolean validarCriterio(Comunidad comunidad1, Comunidad comunidad2);
}
