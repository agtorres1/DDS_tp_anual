package ApiRest.src.main.java.org.example.criterios;

import ApiRest.src.main.java.org.example.Entidades.Comunidad;

public class CriterioGradoDeConfiabilidad implements Criterio{

    @Override
    public Boolean validarCriterio(Comunidad comunidad1, Comunidad comunidad2) {
        return comunidad1.getGradoConfianza() == comunidad2.getGradoConfianza();
    }
}
