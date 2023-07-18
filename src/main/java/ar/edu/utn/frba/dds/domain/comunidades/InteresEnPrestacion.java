package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;

import lombok.Getter;

@Getter
public class InteresEnPrestacion 
{
    private PrestacionDeServicio prestacion;
    private TipoDeInteres tipoDeInteres;

    public InteresEnPrestacion (PrestacionDeServicio prestacionParaAgregar) 
    {
        this.prestacion = prestacionParaAgregar;
        this.tipoDeInteres = TipoDeInteres.SIN_MARCAR;
    }

    public void actualizarTipoDeInteres (TipoDeInteres interesNuevo) {
        this.tipoDeInteres = interesNuevo;
    }
}
