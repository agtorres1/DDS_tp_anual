package ar.edu.utn.frba.dds.controllers;
import io.javalin.http.Context;
public class UsuariosController {
    public UsuariosController(){

    }

    public void login(Context context){
        context.render("Usuarios/login.hbs");
    }

    public void loginPost(Context context){
        String contrasenia = context.formParam("contrasenia");
        String username = context.formParam("nombreDeUsuario");
        //TODO
        context.render("base.hbs");
    }
}
