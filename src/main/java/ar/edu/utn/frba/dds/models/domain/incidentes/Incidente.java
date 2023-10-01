package ar.edu.utn.frba.dds.models.domain.incidentes;

import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Entity
@Table(name = "incidentes")

@Setter @Getter
public class Incidente {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fechayHoraApertura")
    private LocalDateTime fachaYHoraApertura;

    @Column(name = "fechayHoraCierre")
    private LocalDateTime fechaYHoraCierre;

    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id")
    private PrestacionDeServicio prestacionDeServicio;

    @Transient
    private Establecimiento establecimiento;

    @Column(name = "observaciones",columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "estaAbierto")
    private Boolean abierto;
    @OneToOne
    @JoinColumn(name = "id_abridor")
    private Miembro abridor;
    @OneToOne
    @JoinColumn(name = "id_cerrador")
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
