package ApiRest.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Getter @Setter
public class Comunidad {
    private Long id;
    private List<Long> establecimientos;
    private List<Long> servicios;
    private List<Long> usuarios;
    private List<Long> incidentes;

    private List<PropuestaAnterior> propuestasAnteriores; // String sea idComunidad

    private double gradoConfianza;

    public Comunidad(){
        this.establecimientos = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.incidentes = new ArrayList<>();
        this.propuestasAnteriores = new ArrayList<>();
    }
}
