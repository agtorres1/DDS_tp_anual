package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.domain.excepciones.NoEsAdministradorExcepcion;
import ar.edu.utn.frba.dds.domain.excepciones.TipoEstablecimientoInvalidoExcepcion;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.services_api.georef.ServicioGeoref;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.TipoEstablecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Ubicacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ComunidadTest {
    private Comunidad comunidad;
    private Miembro cualquiera;
    private Miembro administrador;
    private Establecimiento establecimiento;
    private Ubicacion ubicacion;

    private Localizacion localizacion;

    @BeforeEach
    public void init() throws IOException {
        this.ubicacion = new Ubicacion();
        this.ubicacion.setLatitud(1.00);
        this.ubicacion.setLongitud(-1.00);

        this.localizacion = new Localizacion(ServicioGeoref.getInstance(),"Buenos Aires");

        this.establecimiento = new Establecimiento(TipoEstablecimiento.ESTACION);
        this.establecimiento.setNombre("Flores");
        this.establecimiento.setCentroide(ubicacion);

        this.cualquiera = new Miembro("thompson","soyHacker");
        this.administrador = new Miembro("adminResponsable","puedoAgregarServicios");

        this.comunidad = new Comunidad("ComunidadMuySegura");
    }

    @Test
    @DisplayName("Agregar un servicio nuevo en una estación sin ser administrador de la comunidad")
    public void agregarServicioSinSerAdmin(){
        Assertions.assertThrows(NoEsAdministradorExcepcion.class,()->{
            this.comunidad.ingresarServicioNuevo(this.establecimiento, this.cualquiera,"servicio","muyInclusivo",3);
        });
    }

    @Test
    @DisplayName("Agregar un servicio nuevo en una estación siendo administrador de la comunidad")
    public void agregarServicioSiendoAdmin() throws NoEsAdministradorExcepcion {
        Assertions.assertDoesNotThrow(()->{
            this.comunidad.agregarAdministradores(this.administrador);
            this.comunidad.ingresarServicioNuevo(this.establecimiento,this.administrador,"servicioBueno","servicioMuyResponsable",2);
        });
    }
}
