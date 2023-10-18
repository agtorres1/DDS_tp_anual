package ar.edu.utn.frba.dds.controllers;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Email;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.MedioDeNotificacion;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Whatsapp;
import ar.edu.utn.frba.dds.models.domain.ValidadorContrasenias.ValidadorDeContrasenias;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.repositories.RepoDeMediosDeNotificacion;
import ar.edu.utn.frba.dds.repositories.RepoDeMiembros;
import at.favre.lib.crypto.bcrypt.BCrypt;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class UsuariosController {
    private RepoDeMiembros repoMiembros;
    private RepoDeMediosDeNotificacion repoDeMediosDeNotificacion;
    public UsuariosController(RepoDeMiembros repoMiembros, RepoDeMediosDeNotificacion repoDeMediosDeNotificacion){
        this.repoMiembros = repoMiembros;
        this.repoDeMediosDeNotificacion = repoDeMediosDeNotificacion;
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
        String medioNotificacion = context.formParam("medioNotificacion");

            //Validaciones
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

        //Carga a db
        MedioDeNotificacion medioDeNotificacion;
        String atributo;
        String valor;

        if("telefono".equals(medioNotificacion)){
            String telefono = context.formParam("telefono");
            atributo = "telefono";
            valor = telefono;
            medioDeNotificacion = new Whatsapp(telefono);
        }
        else{
            medioDeNotificacion = new Email(email);
            atributo = "email";
            valor = email;
        }

        repoDeMediosDeNotificacion.agregar(medioDeNotificacion);
        MedioDeNotificacion medio = repoDeMediosDeNotificacion.buscarPor(atributo, valor);

        Miembro miembro = new Miembro();
        miembro.medioDeNotificacion = medio;
        miembro.setUsuario(nombreDeUsuario);
        miembro.setMail(email);
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
