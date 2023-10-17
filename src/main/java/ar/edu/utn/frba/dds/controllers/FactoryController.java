package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.controllers.ComunidadesController.ComunidadesController;
import ar.edu.utn.frba.dds.controllers.ComunidadesController.incidentes.IncidentesController;
import ar.edu.utn.frba.dds.repositories.RepoDeComunidades;
import ar.edu.utn.frba.dds.repositories.RepoDeEstablecimientos;
import ar.edu.utn.frba.dds.repositories.RepoDeIncidentes;
import ar.edu.utn.frba.dds.repositories.RepoDePrestacionDeServicio;
import ar.edu.utn.frba.dds.repositories.RepoDeMiembros;
import ar.edu.utn.frba.dds.repositories.RepoEntidadControladora;
import ar.edu.utn.frba.dds.repositories.RepoOrganismoDeControl;

public class FactoryController {
    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Usuarios":
                controller = new UsuariosController(new RepoDeMiembros());
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
