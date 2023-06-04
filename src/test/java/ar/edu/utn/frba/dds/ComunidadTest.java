package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.domain.excepciones.NoEsAdministradorExcepcion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ComunidadTest {
    private Comunidad comunidad;
    private Miembro cualquiera;
    private Miembro administrador;
    private Establecimiento establecimiento;
    private Ubicacion ubicacion;
    @BeforeEach
    public void init() {
        this.ubicacion = new Ubicacion();
        this.ubicacion.setLatitud(1.00);
        this.ubicacion.setLongitud(-1.00);

        this.establecimiento = new Establecimiento();
        this.establecimiento.setNombre("Flores");
        this.establecimiento.setCentroide(ubicacion);

        this.cualquiera = new Miembro("thompson","soyHacker");
        this.administrador = new Miembro("adminResponsable","puedoAgregarServicios");

        this.comunidad = new Comunidad("ComunidadMuySegura");
    }

    @Test
    @DisplayName("Agregar un servicio nuevo en una estación sin ser administrador de la comunidad")
    public void agregarServicioSinSerAdmin(){
        try {
            this.comunidad.ingresarServicioNuevo(this.establecimiento, this.cualquiera,"servicio","muyInclusivo");
        } catch (NoEsAdministradorExcepcion e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Agregar un servicio nuevo en una estación siendo administrador de la comunidad")
    public void agregarServicioSiendoAdmin() throws NoEsAdministradorExcepcion {
        this.comunidad.agregarAdministradores(this.administrador);
        try {
            this.comunidad.ingresarServicioNuevo(this.establecimiento,this.administrador,"servicioBueno","servicioMuyResponsable");
        } catch (NoEsAdministradorExcepcion e) {
            throw new RuntimeException(e);
        }
    }
}
