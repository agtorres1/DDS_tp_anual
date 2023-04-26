package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.domain.comunidades.Usuario;
import ar.edu.utn.frba.dds.domain.excepciones.NoEsAdministradorExcepcion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Estacion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ComunidadTest {
    private Comunidad comunidad;
    private Usuario cualquiera;
    private Usuario administrador;
    private Estacion estacion;
    private Ubicacion ubicacion;
    @BeforeEach
    public void init() {
        this.ubicacion = new Ubicacion();
        this.ubicacion.setLatitud(1.00);
        this.ubicacion.setLongitud(-1.00);

        this.estacion = new Estacion();
        this.estacion.setNombre("Flores");
        this.estacion.setCentroide(ubicacion);

        this.cualquiera = new Usuario("thompson","soyHacker");
        this.administrador = new Usuario("adminResponsable","puedoAgregarServicios");

        this.comunidad = new Comunidad("ComunidadMuySegura");
    }

    @Test
    @DisplayName("Agregar un servicio nuevo en una estación sin ser administrador de la comunidad")
    public void agregarServicioSinSerAdmin(){
        try {
            this.comunidad.ingresarServicioNuevo(this.estacion, this.cualquiera,"servicio","muyInclusivo");
        } catch (NoEsAdministradorExcepcion e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Agregar un servicio nuevo en una estación siendo administrador de la comunidad")
    public void agregarServicioSiendoAdmin() throws NoEsAdministradorExcepcion {
        this.comunidad.agregarAdministradores(this.administrador);
        try {
            this.comunidad.ingresarServicioNuevo(this.estacion,this.administrador,"servicioBueno","servicioMuyResponsable");
        } catch (NoEsAdministradorExcepcion e) {
            throw new RuntimeException(e);
        }
    }
}
