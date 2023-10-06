package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.controllers.incidentes.IncidentesController;
import ar.edu.utn.frba.dds.models.repositories.RepoDeIncidentes;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Incidentes": controller = new IncidentesController(new RepoDeIncidentes()); break;

        }
        return controller;
    }

}
