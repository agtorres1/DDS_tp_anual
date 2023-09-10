package ApiRest.src.main.java.org.example.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SugerenciaRequest {
    private List<Comunidad> comunidades;
}
