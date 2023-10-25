package ar.edu.utn.frba.dds.models.domain.incidentes;

import ar.edu.utn.frba.dds.models.builders.puntajes.IncidentePuntajeBuilder;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.IncidentePuntaje;
import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "incidentes")

@Setter @Getter
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechayHoraApertura",columnDefinition = "TIMESTAMP")
    private LocalDateTime fachaYHoraApertura;

    @Column(name = "fechayHoraCierre",columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaYHoraCierre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_servicio", referencedColumnName = "id")
    private PrestacionDeServicio prestacionDeServicio;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_establecimiento", referencedColumnName = "id")
    private Establecimiento establecimiento;

    @Column(name = "observaciones",columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "estaAbierto")
    private Boolean abierto;

    @OneToOne(cascade = CascadeType.PERSIST)
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
        setFachaYHoraApertura(aperturaIncidente.getFechaYHoraApertura());
        setAbierto(true);
    }
    public void meCierro(Miembro cerrador){
        this.setFechaYHoraCierre(LocalDateTime.now());
        this.setCerrador(cerrador);
        this.setAbierto(false);
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

    public IncidentePuntaje incidentePuntaje(){
        return new IncidentePuntajeBuilder().conId(this.getId()).conAbridor(this.getAbridor().getId()).
                conCerrador(this.getCerrador().getId()).conServicio(this.getPrestacionDeServicio().getId()).
                conFechaApertura(this.getFachaYHoraApertura()).conFechaCierre(this.fechaYHoraCierre).construir();
    }


}
