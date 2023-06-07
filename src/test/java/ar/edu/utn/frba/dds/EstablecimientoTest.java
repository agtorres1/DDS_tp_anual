package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.servicios.*;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.TipoEstablecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EstablecimientoTest {
    private Establecimiento establecimiento;
    private Banio banio;
    private PrestacionDeServicios prestacionBanio;
    private Escalador ascensor;
    private PrestacionDeServicios prestacionAscensor;
    private Ubicacion ubicacion;
    @BeforeEach
    public void init(){


        this.ubicacion = new Ubicacion();
        this.ubicacion.setLatitud(1.00);
        this.ubicacion.setLongitud(-1.00);

        this.establecimiento = new Establecimiento(TipoEstablecimiento.ESTACION);
        this.establecimiento.setNombre("Flores");
        this.establecimiento.setCentroide(ubicacion);

        this.banio = new Banio();
        this.banio.setFunciona(true);
        this.banio.setGenero(Genero.HOMBRE);
        this.banio.setDiscapacitado(true);
        this.prestacionBanio = new PrestacionDeServicios(this.banio,3);

        this.ascensor = new Escalador();
        this.ascensor.setFunciona(true);
        this.ascensor.setOrigen(TipoTraslado.CALLE);
        this.ascensor.setDestino(TipoTraslado.BARRERA);
        this.prestacionAscensor = new PrestacionDeServicios(this.ascensor,2);

    }
    @Test
    @DisplayName("Agregamos dos prestaciones de servicios y damos de baja uno")
    public void EstacionAgregaDosPrestacionesYEliminaUno(){
        establecimiento.agregarPrestaciones(this.prestacionAscensor,this.prestacionBanio);
        establecimiento.darDeBajaPrestaciones(this.prestacionAscensor);
    }
}
