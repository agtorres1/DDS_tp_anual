package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.excepciones.TipoEstablecimientoInvalidoExcepcion;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EntidadTest {
    private Serviciopublico serviciopublico;
    private Entidad entidad;
    private Establecimiento origen;
    private Establecimiento destino;
    private Ubicacion ubicacion1;
    private Ubicacion ubicacion2;
    private Localizacion localizacion;
    @BeforeEach
    public void init() throws IOException {
        this.ubicacion1 = new Ubicacion();
        this.ubicacion1.setLatitud(1.00);
        this.ubicacion1.setLongitud(-1.00);

        this.origen = new Establecimiento(TipoEstablecimiento.ESTACION);
        this.origen.setNombre("Flores");
        this.origen.setCentroide(ubicacion1);

        this.ubicacion2 = new Ubicacion();
        this.ubicacion2.setLatitud(2.00);
        this.ubicacion2.setLongitud(-8.00);

        this.destino = new Establecimiento(TipoEstablecimiento.ESTACION);
        this.destino.setNombre("Once");
        this.destino.setCentroide(ubicacion2);

        this.localizacion = new Localizacion("Chaco");
    }

    @Test
    @DisplayName("Instanciar una linea sin agregar origen y destino a la lista de estaciones")
    public void instanciarLineaSinAgregarEnLista() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.entidad = new Entidad(TipoEntidad.LINEA_TRANSPORTE,TipoEstablecimiento.ESTACION,this.localizacion);
            this.serviciopublico = new Serviciopublico(entidad, ServicioPublicoPosible.SUBTERRANEO);
        });
    }

    @Test
    @DisplayName("Instanciar una linea agregando origen y destino a la lista de estaciones")
    public void instanciarLineaAgregandoEnLista(){
        Assertions.assertDoesNotThrow(()->{
            this.entidad = new Entidad(TipoEntidad.LINEA_TRANSPORTE,TipoEstablecimiento.ESTACION,this.localizacion);
            this.entidad.agregarEstablecimientos(this.origen,this.destino);
            this.serviciopublico = new Serviciopublico(entidad, ServicioPublicoPosible.FERROCARRIL);
        });
    }

    @Test
    @DisplayName("Instanciar una linea agregando origen y destino a la lista de estaciones, pero de una categoria no disponible para este tipo de entidad")
    public void instanciarCategoriaIncorrecta(){
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            this.entidad = new Entidad(TipoEntidad.LINEA_TRANSPORTE,TipoEstablecimiento.ESTACION,this.localizacion);
            this.entidad.agregarEstablecimientos(this.origen,this.destino);
            this.serviciopublico = new Serviciopublico(entidad, ServicioPublicoPosible.BANCO);
        });
    }

    @Test
    @DisplayName("Instanciar una linea agregando origen y destino a la lista de estaciones, pero estas con un diferente tipo de establecimiento al esperado")
    public void instanciarEstablecimientosIncorrectos(){
        Assertions.assertThrows(TipoEstablecimientoInvalidoExcepcion.class,()->{
            Establecimiento establecimientoIncorrecto = new Establecimiento(TipoEstablecimiento.SUCURSAL);
            this.entidad = new Entidad(TipoEntidad.LINEA_TRANSPORTE,TipoEstablecimiento.ESTACION,this.localizacion);
            this.entidad.agregarEstablecimientos(this.origen,establecimientoIncorrecto);
        });
    }

}
