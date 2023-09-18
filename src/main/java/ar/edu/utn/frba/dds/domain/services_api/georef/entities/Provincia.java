package ar.edu.utn.frba.dds.domain.services_api.georef.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Provincia {
    @Id
    public int id;
    @Column(name = "nombre")
    public String nombre;

}
