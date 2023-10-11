package ar.edu.utn.frba.dds.models.domain.services_api.georef.entities;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Provincia {
    @Id
    public int id;
    @Column(name = "nombre")
    public String nombre;

}
