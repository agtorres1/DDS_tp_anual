package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Setter @Getter
public class Incidente {
    private Date fecha;
    private Time hora;
    private PrestacionDeServicio prestacionDeServicio;
    private String observaciones;
    private Boolean estado;

}
