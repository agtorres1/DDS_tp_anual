package ar.edu.utn.frba.dds.domain.servicios;

import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "prestacion_de_servicios")
@Setter @Getter
public class PrestacionDeServicio {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Servicio servicio;
    @Transient
    private Integer cantidad;
    @Transient
    private Boolean funciona;
    private String nombreServicio;
    private List<Miembro> interesados;


    public PrestacionDeServicio(Servicio servicio, Integer cantidad){
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.interesados = new ArrayList<>();

    }

