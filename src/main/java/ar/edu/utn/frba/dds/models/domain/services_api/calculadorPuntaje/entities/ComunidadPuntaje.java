package ar.edu.utn.frba.dds.models.domain.services_api.calculadorPuntaje.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ComunidadPuntaje {
    @SerializedName("id")
    public int id;
    @SerializedName("puntaje")
    public double puntaje;
    @SerializedName("miembros")
    public List<MiembroPuntaje> miembros;

}
