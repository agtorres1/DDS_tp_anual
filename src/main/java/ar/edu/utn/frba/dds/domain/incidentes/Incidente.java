package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Setter @Getter
public class Incidente {
    private LocalDateTime fachaYHoraApertura;
    private LocalDateTime fechaYHoraCierre;
    private PrestacionDeServicio prestacionDeServicio;
    private Establecimiento establecimiento;
    private String observaciones;
    private Boolean abierto;
    private Miembro abridor;
    private Miembro cerrador;


    public void meAbro(Miembro abridor,String observaciones,Establecimiento establecimiento,PrestacionDeServicio prestacionDeServicio){
        setAbridor(abridor);
        setObservaciones(observaciones);
        setEstablecimiento(establecimiento);
        setPrestacionDeServicio(prestacionDeServicio);
        setFachaYHoraApertura(LocalDateTime.now());
        setAbierto(true);
    }
    public void meCierro(Miembro cerrador){
        this.setFechaYHoraCierre(LocalDateTime.now());
        this.setCerrador(cerrador);
    }

}
