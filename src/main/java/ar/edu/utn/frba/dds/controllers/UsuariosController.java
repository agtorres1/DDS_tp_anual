package ar.edu.utn.frba.dds.controllers;
import ar.edu.utn.frba.dds.models.domain.ValidadorContrasenias.ValidadorDeContrasenias;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.repositories.RepoDeMiembros;
import at.favre.lib.crypto.bcrypt.BCrypt;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class UsuariosController {
    private static final Double puntajeInicial = 5.00;
    private RepoDeMiembros repoMiembros;
    public UsuariosController(RepoDeMiembros repoMiembros){
        this.repoMiembros = repoMiembros;
    }

    public void login(Context context){context.render("Usuarios/login.hbs");}

    public void loginPost(Context context){
        String contrasenia = context.formParam("contrasenia");
        String nombreDeUsuario = context.formParam("nombreDeUsuario");

        Miembro miembro = this.repoMiembros.buscarPor("usuario", nombreDeUsuario);

        boolean esMismaContrasenia = BCrypt.verifyer().verify(contrasenia.getBytes(), miembro.getContrasenia().getBytes()).verified;

        if(!esMismaContrasenia || miembro == null)
        {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("error", "Nombre de usuario o contraseña incorrecta");
            context.render("Usuarios/login.hbs", modelo);
            return;
        }

        context.sessionAttribute("usuario_id", miembro.getId());
        context.sessionAttribute("tipo_rol", miembro.getRol().getTipo().toString());

        context.render("base.hbs");
    }

    public void register(Context context){context.render("Usuarios/register.hbs");}

    public void registerPost(Context context){
            Map<String, Object> modelo = new HashMap<>();

            String contrasenia = context.formParam("contrasenia");
            String nombreDeUsuario = context.formParam("nombreDeUsuario");
            String email = context.formParam("email");

            if (!(new ValidadorDeContrasenias().esValida(contrasenia))) {
                modelo.put("error", "La contraseña no es valida");
                context.render("Usuarios/register.hbs", modelo);
                return;
            }

            Miembro miembroPorUsername = this.repoMiembros.buscarPor("usuario", nombreDeUsuario);
            if (miembroPorUsername != null) {
                modelo.put("error", "El nombre de usuario ya existe.");
                context.render("Usuarios/register.hbs", modelo);
                return;
            }

            Miembro miembroPorEmail = this.repoMiembros.buscarPor("mail", email);
            if (miembroPorEmail != null) {
                modelo.put("error", "El email ya está registrado.");
                context.render("Usuarios/register.hbs", modelo);
                return;
            }

            Miembro miembro = new Miembro();
            miembro.setUsuario(nombreDeUsuario);
            miembro.setMail(email);
            miembro.setPuntaje(this.puntajeInicial);
            String contraseniaHASH = BCrypt.withDefaults().hashToString(12, contrasenia.toCharArray());
            miembro.setContrasenia(contraseniaHASH);

            repoMiembros.agregar(miembro);
            modelo.put("exito", "Usuario registrado con exito");
            context.render("Usuarios/login.hbs", modelo);
    }

    public void index(Context context){
        Map<String, Object> modelo = new HashMap<>();
        List<Miembro> usuarios = repoMiembros.buscarTodos();
        modelo.put("usuarios", usuarios);
        context.render("Usuarios/index.hbs",modelo);
    }

    public void mostrarUsuario(Context context){
        Long idUsuario = Long.parseLong(context.pathParam("id"));
        Miembro usuario = repoMiembros.buscarPorId(idUsuario);
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("usuario", usuario);

        //todo
    }

}
