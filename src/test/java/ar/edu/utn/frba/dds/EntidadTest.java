package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.builders.EntidadBuilder;
import ar.edu.utn.frba.dds.builders.EstablecimientoBuilder;
import ar.edu.utn.frba.dds.excepciones.TipoEstablecimientoInvalidoExcepcion;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EntidadTest {
    private EntidadBuilder entidadBuilder;
    private Entidad entidad;
    private EstablecimientoBuilder establecimientoBuilder;
    private Establecimiento origen;
    private Establecimiento destino;
    private Ubicacion ubicacion1;
    private Ubicacion ubicacion2;
    private Localizacion localizacionOrigen;
    private Localizacion localizacionDestino;
    private Localizacion localizacionEntidad;
    @BeforeEach
    public void init() throws IOException {
        this.establecimientoBuilder = new EstablecimientoBuilder();
        this.entidadBuilder = new EntidadBuilder();

        this.ubicacion1 = new Ubicacion();
        this.ubicacion1.setLatitud(1.00);
        this.ubicacion1.setLongitud(-1.00);
        this.localizacionOrigen = new Localizacion("Chaco");
        this.localizacionOrigen.setMunicipio("Isla del Cerrito");
        this.origen = this.establecimientoBuilder.conNombre("Flores").conTipo(TipoEstablecimiento.ESTACION).conLocalizacion(localizacionOrigen).construir();
        this.origen.setCentroide(ubicacion1);

        this.ubicacion2 = new Ubicacion();
        this.ubicacion2.setLatitud(2.00);
        this.ubicacion2.setLongitud(-8.00);
        this.localizacionDestino = new Localizacion("Chaco");
        this.localizacionDestino.setMunicipio("Chorotis");
        this.destino = this.establecimientoBuilder.conNombre("Once").conTipo(TipoEstablecimiento.ESTACION).
                conLocalizacion(localizacionDestino).construir();
        this.destino.setCentroide(ubicacion2);

        this.localizacionEntidad = new Localizacion("Chaco");
    }


    @Test
    @DisplayName("Instanciar una linea agregando origen y destino a la lista de estaciones")
    public void instanciarLineaAgregandoEnLista(){
        Assertions.assertDoesNotThrow(()->{
            this.entidad = this.entidadBuilder.conNombre("Linea Resistencia").conTipo(TipoEstablecimiento.ESTACION).
                    conLocalizacion(this.localizacionEntidad).construir();
            this.entidad.agregarEstablecimientos(this.origen,this.destino);
        });
    }

    @Test
    @DisplayName("Instanciar una linea agregando origen y destino a la lista de estaciones, pero estas con un diferente tipo de establecimiento al esperado")
    public void instanciarEstablecimientosIncorrectos(){
        Assertions.assertThrows(TipoEstablecimientoInvalidoExcepcion.class,()->{
            Establecimiento establecimientoIncorrecto = establecimientoBuilder.conNombre("Soy malo").
                    conTipo(TipoEstablecimiento.SUCURSAL).conLocalizacion(new Localizacion("Chaco")).construir();
            this.entidad =this.entidadBuilder.conNombre("Linea Resistencia").
                    conTipo(TipoEstablecimiento.ESTACION).conLocalizacion(this.localizacionEntidad).construir();
            this.entidad.agregarEstablecimientos(this.origen,establecimientoIncorrecto);
        });
    }

}
