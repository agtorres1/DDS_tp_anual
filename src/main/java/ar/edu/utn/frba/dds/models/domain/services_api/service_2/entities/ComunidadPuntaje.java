package ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities;

import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;

import java.util.ArrayList;
import java.util.List;

public class ComunidadPuntaje {
    public int id;
    public double puntaje;
    public List<MiembroPuntaje> miembros;

    public ComunidadPuntaje(){
        this.miembros = new ArrayList<>();
    }
}
