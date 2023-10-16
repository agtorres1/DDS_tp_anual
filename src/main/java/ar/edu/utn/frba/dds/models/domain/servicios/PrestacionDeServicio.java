package ar.edu.utn.frba.dds.models.domain.servicios;

import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;

import javax.persistence.*;

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
   @Column(name = "cantidadDisponible")
    private Integer cantidad;
    @Column(name = "funciona")
    private Boolean funciona;
    @Column(name = "nombre")
    private String nombreServicio;
    @OneToMany
    private List<Miembro> interesados;


    public PrestacionDeServicio() {
        this.interesados = new ArrayList<>();
    }

}