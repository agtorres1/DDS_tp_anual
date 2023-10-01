package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.builders.EntidadBuilder;
import ar.edu.utn.frba.dds.models.builders.EstablecimientoBuilder;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.TipoEstablecimiento;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Ubicacion;
import ar.edu.utn.frba.dds.models.excepciones.LocalizacionEstablecimientoInvalidaExcepcion;
import ar.edu.utn.frba.dds.models.excepciones.TipoEstablecimientoInvalidoExcepcion;
import ar.edu.utn.frba.dds.models.domain.incidentes.localizaciones.Localizacion;
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
        this.localizacionOrigen = new Localizacion();
        this.localizacionOrigen.setProvincia("Chaco");
        this.localizacionOrigen.setMunicipio("Isla del Cerrito");
        this.origen = this.establecimientoBuilder.conNombre("Flores").conTipo(TipoEstablecimiento.ESTACION).conLocalizacion(this.localizacionOrigen).construir();
        this.origen.setCentroide(ubicacion1);

        this.ubicacion2 = new Ubicacion();
        this.ubicacion2.setLatitud(2.00);
        this.ubicacion2.setLongitud(-8.00);
        this.localizacionDestino = new Localizacion();
        this.localizacionDestino.setProvincia("Chaco");
        this.localizacionDestino.setMunicipio("Chorotis");
        this.destino = this.establecimientoBuilder.conNombre("Once").conTipo(TipoEstablecimiento.ESTACION).
                conLocalizacion(this.localizacionDestino).construir();
        this.destino.setCentroide(ubicacion2);

        this.localizacionEntidad = new Localizacion();
        this.localizacionEntidad.setProvincia("Chaco");
    }


    @Test
    @DisplayName("Instanciar una linea agregando origen y destino a la lista de estaciones")
    public void instanciarLineaAgregandoEnLista(){
        Assertions.assertThrows(LocalizacionEstablecimientoInvalidaExcepcion.class, ()->{
            this.entidad = this.entidadBuilder.conNombre("Linea Resistencia").conTipo(TipoEstablecimiento.ESTACION).
                    conLocalizacion(this.localizacionEntidad).construir();
            this.entidad.agregarEstablecimientos(this.origen, this.destino);
        });
    }

    @Test
    @DisplayName("Instanciar una linea agregando origen y destino a la lista de estaciones, pero estas con un diferente tipo de establecimiento al esperado")
    public void instanciarEstablecimientosIncorrectos() throws IOException {
        Localizacion localizacion = new Localizacion();
        localizacion.setProvincia("Chaco");

        Assertions.assertThrows(TipoEstablecimientoInvalidoExcepcion.class,()->{
            Establecimiento establecimientoIncorrecto = establecimientoBuilder.conNombre("Soy malo").
                    conTipo(TipoEstablecimiento.SUCURSAL).conLocalizacion(localizacion).construir();
            this.entidad =this.entidadBuilder.conNombre("Linea Resistencia").
                    conTipo(TipoEstablecimiento.ESTACION).conLocalizacion(this.localizacionEntidad).construir();
            this.entidad.agregarEstablecimientos(this.origen,establecimientoIncorrecto);
        });
    }

    @Test
    @DisplayName("Instanciar establecimientos a la lista de una entidad, pero una de estas con un diferente provincia configurada a la de la entidad")
    public void instanciarEstablecimientosIncorrectos2()throws IOException {

        Localizacion localizacion3 = new Localizacion();
        localizacion3.setProvincia("Buenos Aires");
        Localizacion localizacion4 = new Localizacion();
        localizacion4.setProvincia("Chaco");

        Assertions.assertThrows(LocalizacionEstablecimientoInvalidaExcepcion.class,()->{
            Establecimiento establecimientoIncorrecto = establecimientoBuilder.conNombre("Soy malo").
                    conTipo(TipoEstablecimiento.ESTACION).conLocalizacion(localizacion3).construir();
            Establecimiento establecimientoCorrecto = establecimientoBuilder.conNombre("Soy bueno").
                    conTipo(TipoEstablecimiento.ESTACION).conLocalizacion(localizacion4).construir();
            this.entidad =this.entidadBuilder.conNombre("Linea Resistencia").
                    conTipo(TipoEstablecimiento.ESTACION).conLocalizacion(this.localizacionEntidad).construir();
            this.entidad.agregarEstablecimientos(establecimientoCorrecto,establecimientoIncorrecto);
        });
    }

}
