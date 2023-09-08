package ApiRest.src.main.java.org.example.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Comunidad {
    private String id;
    private List<String> establecimientos;
    private List<String> servicios;
    private List<String> usuarios;
    private List<String> incidentes;
    private double gradoConfianza;

    public Comunidad(){}
    public Comunidad(List<String> establecimientos,
                     List<String> servicios,
                     List<String> usuarios,
                     List<String> incidentes,
                     double gradoConfianza,
                     String id) {
        this.establecimientos = establecimientos;
        this.servicios = servicios;
        this.gradoConfianza = gradoConfianza;
        this.incidentes = incidentes;
        this.usuarios = usuarios;
        this.id = id;
    }

}
