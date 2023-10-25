package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Email;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Notificador;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Whatsapp;
import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Interes;
import ar.edu.utn.frba.dds.models.domain.comunidades.InteresEnPrestacion;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.incidentes.AperturaIncidente;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.incidentes.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.models.domain.services_api.georef.entities.Departamento;
import ar.edu.utn.frba.dds.models.domain.services_api.georef.entities.Municipio;
import ar.edu.utn.frba.dds.models.domain.services_api.georef.entities.Provincia;
import ar.edu.utn.frba.dds.models.domain.servicios.*;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.*;
import ar.edu.utn.frba.dds.models.domain.usuario.Rol;
import ar.edu.utn.frba.dds.models.excepciones.TipoEstablecimientoInvalidoExcepcion;
import ar.edu.utn.frba.dds.repositories.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;



public class MainExample implements WithSimplePersistenceUnit {

    public static void main(String[] args) throws IOException, TipoEstablecimientoInvalidoExcepcion {

        new MainExample().start();
    }

//    public void somosDeChaco(RepoDeLocalizaciones repoDeLocalizaciones, Miembro... miembros) throws IOException {
//        for(Miembro miembro : miembros){
//            Localizacion localizacion = new Localizacion();
//            localizacion.setProvincia("Chaco");
//            repoDeLocalizaciones.agregar(localizacion);
//            miembro.setLocalizacion(localizacion);
//        }
//    }

    private void start() throws TipoEstablecimientoInvalidoExcepcion, IOException {
//        RepoDeIncidentes repoDeIncidentes = new RepoDeIncidentes();
//        List<Incidente> listaDeincidentes = repoDeIncidentes.obtenerIncidentesPorLocalidadYProvincia("Villa General Mitre", "CABA");
//
//        System.out.println(listaDeincidentes);

        // Provincia
        Provincia provincia1 = new Provincia();
        provincia1.setId(1);
        provincia1.setNombre("CABA");

// Municipio
        Municipio municipio1 = new Municipio();
        municipio1.setId(1);
        municipio1.setNombre("Villa General Mitre");

// Departamento
        Departamento departamento1 = new Departamento();
        departamento1.setId(1);
        departamento1.setNombre("Departamento 1");

// Localizacion
        Localizacion localizacion1 = new Localizacion();
        localizacion1.setProvincia(provincia1);
        localizacion1.setMunicipio(municipio1);
        localizacion1.setDepartamento(departamento1);

// Servicio
        Banio banio = new Banio();

        // Configura las propiedades del baño
        banio.setNombre("Baño público");
        banio.setDescripcion("Baño para uso público");

        // Configura las propiedades específicas del baño
        banio.setGenero(Genero.HOMBRE);  // Puedes elegir el género según tus necesidades
        banio.setDiscapacitado(true);

// PrestacionDeServicio
        PrestacionDeServicio prestacionDeServicio1 = new PrestacionDeServicio();
        prestacionDeServicio1.setId(1L);
        prestacionDeServicio1.setNombreServicio("Servicio 1");
        prestacionDeServicio1.setCantidad(10);
        prestacionDeServicio1.setFunciona(true);
        prestacionDeServicio1.setServicio(banio);

// Establecimiento
        Establecimiento establecimiento1 = new Establecimiento();
        establecimiento1.setId(1L);
        establecimiento1.setNombre("Establecimiento 1");
        establecimiento1.setTipoEstablecimiento(TipoEstablecimiento.SUCURSAL);
        establecimiento1.setCentroide(new Ubicacion());
        establecimiento1.setLocalizacion(localizacion1);

// Miembro
        Miembro miembro1 = new Miembro();
        miembro1.setId(1L);
        miembro1.setMail("correo1@ejemplo.com");
        miembro1.setContrasenia("contrasenia123");
        miembro1.setUsuario("usuario1");
        miembro1.setRol(new Rol());
        miembro1.setLocalizacion(localizacion1);

// Comunidad
        Comunidad comunidad1 = new Comunidad();
        comunidad1.setId(1L);
        comunidad1.setNombre("Comunidad 1");
        comunidad1.setDescripcion("Descripción de la Comunidad 1");
        comunidad1.setAdministradores(Arrays.asList(miembro1));
        comunidad1.setMiembros(Arrays.asList(miembro1));
        comunidad1.setIncidentes(new ArrayList<>());  // La lista de incidentes se inicializa vacía

// Incidente
        Incidente incidente1 = new Incidente();
        incidente1.setId(1L);
        incidente1.setFachaYHoraApertura(LocalDateTime.now());
        incidente1.setPrestacionDeServicio(prestacionDeServicio1);
        incidente1.setEstablecimiento(establecimiento1);
        incidente1.setObservaciones("Observaciones del incidente 1");
        incidente1.setAbierto(true);
        incidente1.setAbridor(miembro1);
        incidente1.setCerrador(miembro1);
        incidente1.setComunidad(comunidad1);

// Interes
        Interes interes1 = new Interes();
        interes1.setId(1L);
        interes1.setDescripcion("Interés 1");
        interes1.setEntidad(new Entidad());
        interes1.setPrestacionesDeInteres(new HashSet<>());  // El conjunto de prestaciones se inicializa vacío

// InteresEnPrestacion
        InteresEnPrestacion interesEnPrestacion1 = new InteresEnPrestacion(prestacionDeServicio1);



// Agregar relaciones
        prestacionDeServicio1.setInteresados(Arrays.asList(miembro1));
        interes1.setPrestacionesDeInteres(new HashSet<>(Arrays.asList(interesEnPrestacion1)));
        miembro1.setIntereses(Arrays.asList(interes1));
        miembro1.setComunidades(new HashSet<>(Arrays.asList(comunidad1)));


        // Suponiendo que tengas instancias de los repositorios correspondientes
        RepoDeProvincias repoDeProvincias = new RepoDeProvincias();
        RepoDeMunicipios repoDeMunicipios = new RepoDeMunicipios();
        RepoDeDepartamentos repoDeDepartamentos = new RepoDeDepartamentos();
        RepoDeLocalizaciones repoDeLocalizaciones = new RepoDeLocalizaciones();
        RepoDeServicios repoDeServicios = new RepoDeServicios();

        RepoDeEstablecimientos repoDeEstablecimientos = new RepoDeEstablecimientos();
        RepoDeMiembros repoDeMiembros = new RepoDeMiembros();
        RepoDeComunidades repoDeComunidades = new RepoDeComunidades();
        RepoDeIncidentes repoDeIncidentes = new RepoDeIncidentes();


// Guardar provincias, municipios y departamentos en la base de datos
        repoDeProvincias.agregar(provincia1);
        repoDeMunicipios.agregar(municipio1);
        repoDeDepartamentos.agregar(departamento1);

// Guardar localización en la base de datos
        repoDeLocalizaciones.agregar(localizacion1);

// Guardar servicios y prestaciones de servicio en la base de datos
        repoDeServicios.agregar(banio);


// Guardar establecimiento en la base de datos
        repoDeEstablecimientos.agregar(establecimiento1);

// Guardar miembro en la base de datos
        repoDeMiembros.agregar(miembro1);

// Guardar comunidad en la base de datos
        repoDeComunidades.agregar(comunidad1);

// Guardar incidente en la base de datos
        repoDeIncidentes.agregar(incidente1);



        /*
       RepoDeProvincias repoDeProvincias = new RepoDeProvincias();
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


        RepoDeMunicipios repoDeMunicipios = new RepoDeMunicipios();
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
        Incidente incidente = comunidad.getIncidentes().get(0);

        repoDeIncidentes.agregar(incidente);
        RepoDeComunidades repoDeComunidades = new RepoDeComunidades();
        repoDeComunidades.agregar(comunidad);


*/

        }
}





