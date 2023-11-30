package ar.edu.utn.frba.dds.models.domain.services_api.calculadorPuntaje.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComunidadPuntaje {
    @SerializedName("id")
    public UUID id;
    @SerializedName("puntaje")
    public double puntaje;
    @SerializedName("miembros")
    public List<MiembroPuntaje> miembros;

}
