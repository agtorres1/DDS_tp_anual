package ar.edu.utn.frba.dds.controllers.usuario;

package controllers.userlog;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginController {

    RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
    ContraseniaHashHelper contraseniaHasher = new ContraseniaHashHelper();

    public ModelAndView pantallaDeLogin(Request request, Response response) {
        return new ModelAndView(null, "login.hbs");
    }


}
