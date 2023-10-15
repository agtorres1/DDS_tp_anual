package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.controllers.ComunidadesController.ComunidadesController;
import ar.edu.utn.frba.dds.controllers.ComunidadesController.incidentes.IncidentesController;
import ar.edu.utn.frba.dds.models.repositories.*;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Comunidades": controller = new ComunidadesController(new RepoDeComunidades()); break;
            case "Incidentes": controller = new IncidentesController(new RepoDeComunidades(),new RepoDeIncidentes(),new RepoDePrestacionDeServicio(), new RepoDeEstablecimientos(),new RepoDeMiembros()); break;

        }
        return controller;
    }

}
