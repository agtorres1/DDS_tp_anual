package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.servicios.*;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EstablecimientoTest {
    private Establecimiento establecimiento;
    private Banio banio;
    private Escalador ascensor;
    private Ubicacion ubicacion;
    @BeforeEach
    public void init(){


        this.ubicacion = new Ubicacion();
        this.ubicacion.setLatitud(1.00);
        this.ubicacion.setLongitud(-1.00);

        this.establecimiento = new Establecimiento();
        this.establecimiento.setNombre("Flores");
        this.establecimiento.setCentroide(ubicacion);

        this.banio = new Banio();
        this.banio.setFunciona(true);
        this.banio.setGenero(Genero.HOMBRE);
        this.banio.setDiscapacitado(true);

        this.ascensor = new Escalador();
        this.ascensor.setFunciona(true);
        this.ascensor.setOrigen(TipoTraslado.CALLE);
        this.ascensor.setDestino(TipoTraslado.BARRERA);

    }
    @Test
    @DisplayName("Agregamos dos servicios y damos de baja uno")
    public void EstacionAgregaDosServiciosYEliminaUno(){
        establecimiento.agregarServicios(this.ascensor,this.banio);
        establecimiento.darDeBajaServicios(this.ascensor);
    }
}
