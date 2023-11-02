package ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import java.util.List;

public class ComunidadFusionable {
    @SerializedName("id")
    public int id;
    @SerializedName("establecimientos")
    public List<Integer> establecimientos;

    @SerializedName("servicios")
    public List<Integer> servicios;

    @SerializedName("usuarios")
    public List<Integer> usuarios;

    @SerializedName("incidentes")
    public List<Integer> incidentes;

    @SerializedName("propuestasAnteriores")
    public List<PropuestaAnterior> propuestasAnteriores;

    @Column(name = "Nivel_del_grado")
    public double gradoConfianza;
}
