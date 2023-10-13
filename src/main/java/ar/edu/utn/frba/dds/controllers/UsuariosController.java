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
        //TODO
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

        context.render("usuarios/login.hbs");

    }

    public void index(Context context){
        //TODO
        Map<String, Object> modelo = new HashMap<>();
        List<Miembro> usuarios = new ArrayList<>();
        Miembro test = new Miembro();
        test.setUsuario("test");
        test.setId(1L);
        Miembro test2 = new Miembro();
        test2.setUsuario("test2");
        test2.setId(2L);
        usuarios.add(test);
        usuarios.add(test2);
        modelo.put("usuarios", usuarios);
        context.render("Usuarios/index.hbs",modelo);
    }
}
