package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.controllers.ComunidadesController.ComunidadesController;
import ar.edu.utn.frba.dds.controllers.ComunidadesController.incidentes.IncidentesController;
import ar.edu.utn.frba.dds.repositories.*;

public class FactoryController {
    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Usuarios":
                controller = new UsuariosController(new RepoDeMiembros(), new RepoDeMediosDeNotificacion(), new RepoDeRoles());
                break;
            case "EntidadesControladoras":
                controller = new EntidadControladoraController(new RepoEntidadControladora());
                break;
            case "OrganismosDeControl":
                controller = new OrganismoDeControlController(new RepoOrganismoDeControl());
                break;
            case "Comunidades":
                controller = new ComunidadesController(new RepoDeComunidades(), new RepoDeMiembros());
                break;
            case "Incidentes":
                controller = new IncidentesController(new RepoDeComunidades(), new RepoDeIncidentes(), new RepoDePrestacionDeServicio(), new RepoDeEstablecimientos(), new RepoDeMiembros());
                break;
        }
        return controller;
    }
}
