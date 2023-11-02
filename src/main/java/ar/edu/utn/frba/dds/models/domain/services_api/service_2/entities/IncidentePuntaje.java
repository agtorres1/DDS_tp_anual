package ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities;

import com.google.gson.annotations.SerializedName;

public class IncidentePuntaje {
    @SerializedName("incidenteId")
    public int incidenteId;
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
