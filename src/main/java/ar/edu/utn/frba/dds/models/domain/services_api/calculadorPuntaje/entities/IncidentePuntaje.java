package ar.edu.utn.frba.dds.models.domain.services_api.calculadorPuntaje.entities;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class IncidentePuntaje {
    @SerializedName("incidenteId")
    public UUID incidenteId;
    @SerializedName("codigoServicio")
    public int codigoServicio;
    @SerializedName("abiertoPorId")
    public int abiertoPorId;
    @SerializedName("cerradoPorId")
    public int cerradoPorId;
    @SerializedName("fechaApertura")
    public String fechaApertura;
    @SerializedName("fechaCierre")
    public String fechaCierre;


/*          "codigoServicio": 10,
                  "abiertoPorId": 1,
                  "cerradoPorId": 2,
                  "fechaApertura": "2023-09-12T21:20:56.319149",
                  "fechaCierre": "2023-09-12T21:20:58.319149"*/
}
