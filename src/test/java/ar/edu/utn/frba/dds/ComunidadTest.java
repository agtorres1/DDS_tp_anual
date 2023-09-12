package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.builders.EstablecimientoBuilder;
import ar.edu.utn.frba.dds.builders.MiembroBuilder;
import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.Email;
import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.MedioDeNotificacion;
import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.RangoHorario;
import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.Whatsapp;
import ar.edu.utn.frba.dds.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.servicios.Banio;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;
import ar.edu.utn.frba.dds.excepciones.NoEsAdministradorExcepcion;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.TipoEstablecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Ubicacion;
import ar.edu.utn.frba.dds.excepciones.PrestacionFuncionaExcepcion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ComunidadTest {
    private Comunidad comunidad;
    private MiembroBuilder miembroBuilder = new MiembroBuilder();
    private Miembro cualquiera;
    private Miembro administrador;
    private Establecimiento establecimiento;
    private EstablecimientoBuilder establecimientoBuilder;
    private Ubicacion ubicacion;


    @BeforeEach
    public void init() throws IOException {
        this.establecimientoBuilder = new EstablecimientoBuilder();
        this.ubicacion = new Ubicacion();
        this.ubicacion.setLatitud(1.00);
        this.ubicacion.setLongitud(-1.00);

        this.establecimiento = establecimientoBuilder.conNombre("Flores").conTipo(TipoEstablecimiento.ESTACION).
                conLocalizacion(new Localizacion("Buenos Aires")).construir();
        this.establecimiento.setCentroide(ubicacion);

        this.cualquiera = miembroBuilder.conCuenta("Thompson","Nosejaja").conLocalizacion(new Localizacion("Chaco")).construir();
        this.administrador = miembroBuilder.conCuenta("adminResponsable","cuidador123").conLocalizacion(new Localizacion("Chaco")).
            conMedioDeNotidicacion(new Whatsapp("1144134775")).construir();
            /*conMedioDeNotidicacion(new Email("tomas.neznajko@gmail.com")).construir();*/



        this.comunidad = new Comunidad();
    }

    @Test
    @DisplayName("Agregar un servicio nuevo en una estación sin ser administrador de la comunidad")
    public void agregarServicioSinSerAdmin(){
        Assertions.assertThrows(NoEsAdministradorExcepcion.class,()->{
        //    this.comunidad.ingresarServicioNuevo(this.establecimiento, this.cualquiera,"servicio","muyInclusivo",3);
        });
    }

    @Test
    @DisplayName("Agregar un servicio nuevo en una estación siendo administrador de la comunidad")
    public void agregarServicioSiendoAdmin() throws NoEsAdministradorExcepcion {
        Assertions.assertDoesNotThrow(()->{
            this.comunidad.agregarAdministradores(this.administrador);
           // this.comunidad.ingresarServicioNuevo(this.establecimiento,this.administrador,"servicioBueno","servicioMuyResponsable",2);
        });
    }

 /*     @Test
    @DisplayName("Agregar un servicio nuevo en una estación siendo administrador de la comunidad")
    public void AbrirIncidente(){
        LocalTime horaInicio1 = LocalTime.of(3, 20);
        LocalTime horaFin1 = LocalTime.of(12, 30);
        RangoHorario rangoHorario1 = new RangoHorario(horaInicio1, horaFin1);
        LocalTime horaInicio2 = LocalTime.of(18, 20);
        LocalTime horaFin2 = LocalTime.of(21, 0);
        RangoHorario rangoHorario2 = new RangoHorario(horaInicio2, horaFin2);
        List<RangoHorario> rangosHorariosElegidos = new ArrayList<>();
        rangosHorariosElegidos.add(rangoHorario1);
        rangosHorariosElegidos.add(rangoHorario2);
        this.administrador.getMedioDeNotificacion().setRangosHorariosElegidos(rangosHorariosElegidos);

        PrestacionDeServicio prestacionDeServicio = new PrestacionDeServicio(new Banio(), 3);
        prestacionDeServicio.setNombreServicio("Baño 1");
        this.establecimiento.agregarPrestaciones(prestacionDeServicio);
        this.comunidad.agregarAdministradores(this.administrador);
        this.comunidad.abrirIncidente(administrador,"Hola",this.establecimiento,prestacionDeServicio);
    } */

    // Ejemplo 1: Crear un rango horario de 8:00 a 12:30


    // Ejemplo 2: Crear otro rango horario de 13:30 a 18:00




}
