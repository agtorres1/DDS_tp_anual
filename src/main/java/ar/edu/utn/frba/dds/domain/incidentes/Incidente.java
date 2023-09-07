package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.Notificacion;
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

    public void meAbro(Miembro abridor,AperturaIncidente aperturaIncidente){
        setAbridor(abridor);
        setObservaciones(aperturaIncidente.getObservaciones());
        setEstablecimiento(aperturaIncidente.getEstablecimiento());
        setPrestacionDeServicio(aperturaIncidente.getPrestacionDeServicio());
        setFachaYHoraApertura(LocalDateTime.now());
        setAbierto(true);


    }
    public void meCierro(Miembro cerrador){
        this.setFechaYHoraCierre(LocalDateTime.now());
        this.setCerrador(cerrador);
    }

     @Override
    public String toString() {
        return "Incidente{" +
            "fachaYHoraApertura=" + fachaYHoraApertura +
            ", fechaYHoraCierre=" + fechaYHoraCierre +
            ", prestacionDeServicio=" + prestacionDeServicio +
            ", establecimiento=" + establecimiento +
            ", observaciones='" + observaciones + '\'' +
            ", abierto=" + abierto +
            ", abridor=" + abridor +
           ", cerrador=" + cerrador +
            '}';
    }

}
