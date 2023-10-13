package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.repositories.RepoDeMiembros;

public class FactoryController {
    public static Object controller(String nombre){
        Object controller = null;
        switch (nombre){
            case "Usuarios" : controller = new UsuariosController(new RepoDeMiembros()); break;
        }
        return  controller;
    }
}
