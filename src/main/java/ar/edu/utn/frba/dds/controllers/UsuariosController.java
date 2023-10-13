package ar.edu.utn.frba.dds.controllers;
import ar.edu.utn.frba.dds.models.domain.ValidadorContrasenias.ValidadorDeContrasenias;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.repositories.RepoDeMiembros;
import at.favre.lib.crypto.bcrypt.BCrypt;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class UsuariosController {
    private RepoDeMiembros repoMiembros;
    public UsuariosController(RepoDeMiembros repoMiembros){
        this.repoMiembros = repoMiembros;
    }

    public void login(Context context){
        context.render("Usuarios/login.hbs");
    }

    public void loginPost(Context context){
        String contrasenia = context.formParam("contrasenia");
        String username = context.formParam("nombreDeUsuario");
        String contraseniaHASH = BCrypt.withDefaults().hashToString(12, contrasenia.toCharArray());

        context.render("base.hbs");
    }

    public void register(Context context){context.render("Usuarios/register.hbs");}

    public void registerPost(Context context){
        String contrasenia = context.formParam("contrasenia");
        String nombreDeUsuario = context.formParam("nombreDeUsuario");
        String email = context.formParam("email");

        if(!(new ValidadorDeContrasenias().esValida(contrasenia))){
            context.render("Usuarios/register.hbs");
        }

        Miembro miembro = new Miembro();
        miembro.setUsuario(nombreDeUsuario);
        miembro.setMail(email);
        String contraseniaHASH = BCrypt.withDefaults().hashToString(12, contrasenia.toCharArray());
        miembro.setContrasenia(contraseniaHASH);

        repoMiembros.agregar(miembro);
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("exito", "Usuario registrado con exito");
        context.render("Usuarios/login.hbs", modelo);
    }

    public void index(Context context){
        Map<String, Object> modelo = new HashMap<>();
        List<Miembro> usuarios = repoMiembros.buscarTodos();
        modelo.put("usuarios", usuarios);
        context.render("Usuarios/index.hbs",modelo);
    }
}
