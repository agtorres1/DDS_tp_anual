package ar.edu.utn.frba.dds.models.domain.incidentes;

import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;
import lombok.Getter;

@Getter
public class AperturaIncidente {
    private String observaciones;
    private Establecimiento establecimiento;
    private PrestacionDeServicio prestacionDeServicio;

}
