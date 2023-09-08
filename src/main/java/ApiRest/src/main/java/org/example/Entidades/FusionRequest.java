package ApiRest.src.main.java.org.example.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FusionRequest {
    private List<Comunidad> comunidades;

    public FusionRequest(){}

    public FusionRequest(List<Comunidad> comunidades){
        this.comunidades = comunidades;
    }
}
