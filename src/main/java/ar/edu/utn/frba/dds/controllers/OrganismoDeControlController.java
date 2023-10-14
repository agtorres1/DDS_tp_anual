package ar.edu.utn.frba.dds.controllers;
import io.javalin.http.Context;
import ar.edu.utn.frba.dds.repositories.RepoOrganismoDeControl;

public class OrganismoDeControlController {
    private RepoOrganismoDeControl repoOrganismoDeControl;

    public OrganismoDeControlController(RepoOrganismoDeControl repoOrganismoDeControl){
        this.repoOrganismoDeControl = repoOrganismoDeControl;
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
