package ApiRest.src.main.java.org.example.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
public class ComunidadSugerencia {
    private Comunidad comunidad1;
    private Comunidad comunidad2;

    public void setParComunidad(Comunidad primerComunidad, Comunidad segundaComunidad){
        primerComunidad.getPropuestasAnteriores().put(segundaComunidad.getId(), LocalDate.now());
        segundaComunidad.getPropuestasAnteriores().put(primerComunidad.getId(), LocalDate.now());
        setComunidad1(primerComunidad);
        setComunidad2(segundaComunidad);
    }
}
