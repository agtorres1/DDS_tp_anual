package ApiRest.src.main.java.org.example.Entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ComunidadSugerencia {
    private Comunidad comunidad1;
    private Comunidad comunidad2;

    public ComunidadSugerencia(Comunidad comunidad1, Comunidad comunidad2){
        this.comunidad1 =comunidad1;
        this.comunidad2 = comunidad2;
    }
}
