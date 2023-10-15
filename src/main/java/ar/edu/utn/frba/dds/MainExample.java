package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Email;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Notificador;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Whatsapp;
import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.incidentes.AperturaIncidente;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.incidentes.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.models.domain.servicios.*;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.*;
import ar.edu.utn.frba.dds.models.excepciones.TipoEstablecimientoInvalidoExcepcion;
import ar.edu.utn.frba.dds.models.repositories.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class MainExample implements WithSimplePersistenceUnit {

    public static void main(String[] args) throws IOException, TipoEstablecimientoInvalidoExcepcion {

        new MainExample().start();
    }

    public void somosDeChaco(RepoDeLocalizaciones repoDeLocalizaciones,Miembro... miembros) throws IOException {
        for(Miembro miembro : miembros){
            Localizacion localizacion = new Localizacion();
            localizacion.setProvincia("Chaco");
            repoDeLocalizaciones.agregar(localizacion);
            miembro.setLocalizacion(localizacion);
        }
    }

    private void start() throws TipoEstablecimientoInvalidoExcepcion, IOException {

/*        RepoDeProvincias repoDeProvincias = new RepoDeProvincias();
        RepoDeLocalizaciones repoDeLocalizaciones = new RepoDeLocalizaciones();
        RepoDeMediosDeNotificacion repoDeMediosDeNotificacion = new RepoDeMediosDeNotificacion();

        Comunidad comunidad = new Comunidad();
        Miembro miembro1= new Miembro();
        miembro1.setUsuario("Pepe");
        Whatsapp wpp1 = new Whatsapp();
        wpp1.setTelefono("1149499974");
        miembro1.setMedioDeNotificacion(wpp1);
        repoDeMediosDeNotificacion.agregar(wpp1);

        Miembro miembro2= new Miembro();
        miembro2.setUsuario("Fede");
        Whatsapp wpp2 = new Whatsapp();
        wpp2.setTelefono("1149499974");
        miembro2.setMedioDeNotificacion(wpp2);
        repoDeMediosDeNotificacion.agregar(wpp2);

        Miembro miembro3= new Miembro();
        miembro3.setUsuario("Agus");
        Whatsapp wpp3 = new Whatsapp();
        wpp3.setTelefono("1149499974");
        miembro3.setMedioDeNotificacion(wpp3);
        repoDeMediosDeNotificacion.agregar(miembro3.getMedioDeNotificacion());

        Miembro miembro4= new Miembro();
        miembro4.setUsuario("Facu");
        Whatsapp wpp4 = new Whatsapp();
        wpp4.setTelefono("1149499974");
        miembro4.setMedioDeNotificacion(wpp4);
        repoDeMediosDeNotificacion.agregar(miembro4.getMedioDeNotificacion());

        Miembro miembro5= new Miembro();
        miembro5.setUsuario("Facu");
        Whatsapp wpp5 = new Whatsapp();
        wpp5.setTelefono("1149499974");
        miembro5.setMedioDeNotificacion(wpp5);
        repoDeMediosDeNotificacion.agregar(miembro5.getMedioDeNotificacion());

        comunidad.agregarUsuarios(miembro1,miembro2,miembro3,miembro4);
        Localizacion localizacion = new Localizacion();
        localizacion.setProvincia("Chaco");
        miembro1.setLocalizacion(localizacion);
        repoDeProvincias.agregar(localizacion.getProvincia());
        repoDeLocalizaciones.agregar(localizacion);
        somosDeChaco(repoDeLocalizaciones,miembro2,miembro3,miembro4,miembro5);

        RepoDeMiembros miembros = new RepoDeMiembros();
        miembros.agregar(miembro1);
        miembros.agregar(miembro2);
        miembros.agregar(miembro3);
        miembros.agregar(miembro4);
        miembros.agregar(miembro5);



        RepoDeServicios repoDeServicios = new RepoDeServicios();

        Servicio banio = new Banio();
        ((Banio) banio).setDiscapacitado(true);
        ((Banio) banio).setGenero(Genero.UNISEX);
        repoDeServicios.agregar(banio);

        Servicio escalador = new Escalador();
        ((Escalador) escalador).setOrigen(TipoTraslado.CALLE);
        ((Escalador) escalador).setOrigen(TipoTraslado.BARRERA);
        repoDeServicios.agregar(escalador);

        RepoDePrestacionDeServicio repoDePrestacionDeServicio = new RepoDePrestacionDeServicio();
        PrestacionDeServicio prestacionBanio = new PrestacionDeServicio();
        prestacionBanio.setNombreServicio("Baño unisex");
        prestacionBanio.setCantidad(2);
        prestacionBanio.setFunciona(false);
        prestacionBanio.setServicio(banio);
        repoDePrestacionDeServicio.agregar(prestacionBanio);

        PrestacionDeServicio prestacionEscalador = new PrestacionDeServicio();
        prestacionEscalador.setNombreServicio("Escalera mecánica");
        prestacionEscalador.setCantidad(2);
        prestacionEscalador.setFunciona(true);
        prestacionEscalador.setServicio(escalador);
        repoDePrestacionDeServicio.agregar(prestacionEscalador);

        Localizacion localizacionEntidad = new Localizacion();
        localizacionEntidad.setProvincia("Chaco");
        repoDeLocalizaciones.agregar(localizacionEntidad);

        Localizacion localizacionEstablecimiento = new Localizacion();
        localizacionEstablecimiento.setProvincia("Chaco");


         localizacionEstablecimiento.setMunicipio("Isla del Cerrito");
       repoDeMunicipios.agregar(localizacionEntidad.getMunicipio());


        repoDeLocalizaciones.agregar(localizacionEstablecimiento);

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(1.00);
        ubicacion.setLongitud(-1.00);

        RepoDeEstablecimientos repoDeEstablecimientos = new RepoDeEstablecimientos();
        Establecimiento establecimiento = new Establecimiento();
        establecimiento.setNombre("Estación de la Isla de Cerrito");
        establecimiento.setLocalizacion(localizacionEstablecimiento);
        establecimiento.agregarPrestaciones(prestacionEscalador,prestacionBanio);
        establecimiento.setCentroide(ubicacion);
        establecimiento.setTipoEstablecimiento(TipoEstablecimiento.ESTACION);
        repoDeEstablecimientos.agregar(establecimiento);

        RepoDeEntidades repoDeEntidades = new RepoDeEntidades();
        Entidad entidad = new Entidad();
        entidad.setNombre("Tren Sarmiento");
        entidad.setLocalizacion(localizacionEntidad);
        entidad.setTipoEntidad(TipoEntidad.LINEA_TRANSPORTE);
        entidad.setTipoEstablecimientos(TipoEstablecimiento.ESTACION);
        entidad.agregarEstablecimientos(establecimiento);
        repoDeEntidades.agregar(entidad);

        AperturaIncidente aperturaIncidente = new AperturaIncidente();
        aperturaIncidente.setObservaciones("El baño está tapadisimo :c");
        aperturaIncidente.setPrestacionDeServicio(prestacionBanio);
        aperturaIncidente.setEstablecimiento(establecimiento);

        miembro1.abrirIncidente(aperturaIncidente,new Notificador());
        RepoDeIncidentes repoDeIncidentes = new RepoDeIncidentes();
        repoDeIncidentes.agregar(comunidad.getIncidentes().get(0));


        repoDeComunidades.agregar(comunidad);*/



        }




    }




