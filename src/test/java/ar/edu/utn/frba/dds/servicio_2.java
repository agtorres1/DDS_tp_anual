package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.servicios.*;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class servicio_2 {
    private Miembro miembro1;
    private Miembro miembro2;
    private Servicio servicio;
    private PrestacionDeServicio prestacionDeServicio;
    private Incidente incidente;
    private Comunidad comunidad;
    @BeforeEach
    public void init() throws IOException {
        this.servicio = new Banio();
        ((Banio)this.servicio).setGenero(Genero.HOMBRE);
        ((Banio)this.servicio).setDiscapacitado(true);
        this.prestacionDeServicio = new PrestacionDeServicio();
        prestacionDeServicio.setServicio(this.servicio);
        prestacionDeServicio.setCantidad(3);
        this.prestacionDeServicio.setFunciona(true);

        this.miembro1 = new Miembro();
        this.miembro1.setId(1L);
        this.miembro1.setPuntaje(4.00);


    }
    @Test
    @DisplayName("Agregamos dos prestaciones de servicios y damos de baja uno")
    public void EstacionAgregaDosPrestacionesYEliminaUno(){
        establecimiento.agregarPrestaciones(this.prestacionAscensor,this.prestacionBanio);
        establecimiento.darDeBajaPrestaciones(this.prestacionAscensor);

        Assert.assertFalse(establecimiento.getPrestacionesDeServicios().contains(prestacionAscensor));
    }

}
