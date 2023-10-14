package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.repositories.RepoDeMiembros;
import ar.edu.utn.frba.dds.repositories.RepoEntidadControladora;
import ar.edu.utn.frba.dds.repositories.RepoOrganismoDeControl;

public class FactoryController {
    public static Object controller(String nombre){
        Object controller = null;
        switch (nombre){
            case "Usuarios" : controller = new UsuariosController(new RepoDeMiembros()); break;
            case "EntidadesControladoras" : controller = new EntidadControladoraController(new RepoEntidadControladora()); break;
            case "OrganismosDeControl" : controller = new OrganismoDeControlController(new RepoOrganismoDeControl()); break;
        }
        return  controller;
    }
}
