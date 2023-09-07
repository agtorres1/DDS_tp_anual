package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import lombok.Getter;

@Getter
public class AperturaIncidente {
    private String observaciones;
    private Establecimiento establecimiento;
    private PrestacionDeServicio prestacionDeServicio;

}
