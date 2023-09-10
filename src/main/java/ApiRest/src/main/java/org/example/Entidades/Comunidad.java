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
}
