package ar.edu.utn.frba.dds.controllers;

public class FactoryController {
    public static Object controller(String nombre){
        Object controller = null;
        switch (nombre){
            case "Usuarios" : controller = new UsuariosController(); break;
        }
        return  controller;
    }
}
