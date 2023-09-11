package ApiRest.src.main.java.org.example.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class Comunidad {
    private String id;
    private List<String> establecimientos;
    private List<String> servicios;
    private List<String> usuarios;
    private List<String> incidentes;

    private Map<String, LocalDate> propuestasAnteriores; // String sea idComunidad

    private double gradoConfianza;
}
