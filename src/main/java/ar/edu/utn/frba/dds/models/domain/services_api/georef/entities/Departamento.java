package ar.edu.utn.frba.dds.models.domain.services_api.georef.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Departamento {
    @Id
    public int id;
    public String nombre;
}
