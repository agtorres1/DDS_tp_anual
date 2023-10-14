package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.repositories.RepoEntidadControladora;
import io.javalin.http.Context;

public class EntidadControladoraController {

    private RepoEntidadControladora repoEntidadControladora;

    public EntidadControladoraController(RepoEntidadControladora repoEntidadControladora){
        this.repoEntidadControladora = repoEntidadControladora;
    }

    public void cargar(Context context){
        //todo
        context.render("base.bhs");
    }

    public void cargarPost(Context context){
        //todo
        context.render("base.bhs");
    }
}
