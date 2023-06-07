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
    private PrestacionDeServicio prestacionBanio;
    private Escalador ascensor;
    private PrestacionDeServicio prestacionAscensor;
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
        this.banio.setGenero(Genero.HOMBRE);
        this.banio.setDiscapacitado(true);
        this.prestacionBanio = new PrestacionDeServicio(this.banio,3);
        this.prestacionBanio.setFunciona(true);

        this.ascensor = new Escalador();
        this.ascensor.setOrigen(TipoTraslado.CALLE);
        this.ascensor.setDestino(TipoTraslado.BARRERA);
        this.prestacionAscensor = new PrestacionDeServicio(this.ascensor,2);
        this.prestacionAscensor.setFunciona(true);

    }
    @Test
    @DisplayName("Agregamos dos prestaciones de servicios y damos de baja uno")
    public void EstacionAgregaDosPrestacionesYEliminaUno(){
        establecimiento.agregarPrestaciones(this.prestacionAscensor,this.prestacionBanio);
        establecimiento.darDeBajaPrestaciones(this.prestacionAscensor);
    }
}
