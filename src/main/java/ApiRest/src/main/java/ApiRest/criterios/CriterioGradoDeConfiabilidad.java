package ApiRest.criterios;

import ApiRest.Entidades.Comunidad;

public class CriterioGradoDeConfiabilidad implements Criterio{

    @Override
    public Boolean validarCriterio(Comunidad comunidad1, Comunidad comunidad2) {
        return comunidad1.getGradoConfianza() == comunidad2.getGradoConfianza();
    }
}
