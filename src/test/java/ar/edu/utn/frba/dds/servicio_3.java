package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.comunidades.PropuestaFusion;
import ar.edu.utn.frba.dds.models.domain.comunidades.gradosDeConfianza.Puntaje;
import ar.edu.utn.frba.dds.models.domain.incidentes.AperturaIncidente;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.ServicioFusionador;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.*;
import ar.edu.utn.frba.dds.models.domain.servicios.Banio;
import ar.edu.utn.frba.dds.models.domain.servicios.Genero;
import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.models.domain.servicios.Servicio;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.repositories.RepoDeComunidades;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class servicio_3 {
    private Comunidad comunidad1;
    private Comunidad comunidad2;
    private Miembro miembro1;
    private Incidente incidente;
    private Servicio servicio;
    private PrestacionDeServicio prestacionDeServicio;
    private PropuestaFusion propuestaFusion;
    private Establecimiento establecimiento;

    private RequestComunidadesAnalizables requestComunidadesAnalizables;
    private RequestComunidadesFusionables requestComunidadesFusionables;
    @BeforeEach
    public void init() throws IOException {
        this.servicio = new Banio();
        ((Banio)this.servicio).setGenero(Genero.HOMBRE);
        ((Banio)this.servicio).setDiscapacitado(true);
        this.prestacionDeServicio = new PrestacionDeServicio();
        this.prestacionDeServicio.setId(1L);
        this.prestacionDeServicio.setServicio(this.servicio);
        this.prestacionDeServicio.setCantidad(3);
        this.prestacionDeServicio.setFunciona(false);

        this.establecimiento = new Establecimiento();
        this.establecimiento.setId(1L);
        this.establecimiento.getPrestacionesDeServicios().add(this.prestacionDeServicio);

        this.miembro1 = new Miembro();
        this.miembro1.setId(1L);

        this.incidente = new Incidente();
        this.incidente.setId(1L);
        this.incidente.setFachaYHoraApertura(LocalDateTime.now());
        this.incidente.setPrestacionDeServicio(this.prestacionDeServicio);
        this.incidente.setEstablecimiento(this.establecimiento);
        this.incidente.setAbridor(this.miembro1);

        this.comunidad1 = new Comunidad();
        this.comunidad1.setId(1L);
        this.comunidad1.agregarUsuarios(miembro1);
        this.comunidad1.setPuntaje(new Puntaje(3.00));
        this.comunidad1.getIncidentes().add(incidente);

        this.comunidad2 = new Comunidad();
        this.comunidad2.setId(2L);
        this.comunidad2.agregarUsuarios(miembro1);
        this.comunidad2.setPuntaje(new Puntaje(3.00));
        this.comunidad2.getIncidentes().add(incidente);

        this.propuestaFusion = new PropuestaFusion();
        this.propuestaFusion.setComunidad(this.comunidad2);
        this.propuestaFusion.setFechaSolicitada(LocalDate.of(2022,10,15));
        this.comunidad1.getPropuestasFusion().add(propuestaFusion);


        List<ComunidadFusionable> comunidadesFusionables = new ArrayList<>();
        comunidadesFusionables.add(comunidad1.comunidadFusionable());
        comunidadesFusionables.add(comunidad2.comunidadFusionable());

        this.requestComunidadesAnalizables = new RequestComunidadesAnalizables();
        this.requestComunidadesAnalizables.setComunidades(comunidadesFusionables);

        this.requestComunidadesFusionables = new RequestComunidadesFusionables();
        this.requestComunidadesFusionables.setComunidad1(this.comunidad1.comunidadFusionable());
        this.requestComunidadesFusionables.setComunidad2(this.comunidad2.comunidadFusionable());

/*        this.requestComunidadesFusionables.setComunidad1(comunidad1.comunidadFusionable());
        this.requestComunidadesFusionables.setComunidad2(comunidad2.comunidadFusionable());*/
    }
    @Test
    @DisplayName("Generar response del analizador")
    public void generarResponseAnalizador() throws IOException {
        RepoDeComunidades repoDeComunidades = new RepoDeComunidades();
        Comunidad comunidad1 = repoDeComunidades.buscarPorId(1L);
        ResponseComunidadesAnalizables comunidadesAnalizablesResponse = ServicioFusionador.getInstance().responseComunidadesAnalizables(this.requestComunidadesAnalizables);
        System.out.println(comunidadesAnalizablesResponse.resultado.get(0).comunidad1.gradoConfianza);
    }

    @Test
    @DisplayName("Actualizar comunidades")
    public void generarResponse() throws IOException {
        ResponseComunidadFusionada comunidadesAnalizablesResponse = ServicioFusionador.getInstance().responseComunidadesFusionadas(this.requestComunidadesFusionables);

        System.out.println(comunidadesAnalizablesResponse.resultado.propuestasAnteriores);
    }
}