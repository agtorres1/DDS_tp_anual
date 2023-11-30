package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.server.utils.cronTasks.ConfigurationTask;

public class App {

    public static void main(String[] args) {
        Server.init();

        ConfigurationTask.actualizarPuntajes();

    }
}

