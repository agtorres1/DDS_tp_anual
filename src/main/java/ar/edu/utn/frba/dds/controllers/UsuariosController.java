package ar.edu.utn.frba.dds.controllers;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Email;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.MedioDeNotificacion;
import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.Whatsapp;
import ar.edu.utn.frba.dds.models.domain.ValidadorContrasenias.ValidadorDeContrasenias;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.usuario.TipoRol;
import ar.edu.utn.frba.dds.repositories.RepoDeMediosDeNotificacion;
import ar.edu.utn.frba.dds.repositories.RepoDeMiembros;
import ar.edu.utn.frba.dds.repositories.RepoDeRoles;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import at.favre.lib.crypto.bcrypt.BCrypt;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import javax.persistence.EntityTransaction;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class UsuariosController implements WithSimplePersistenceUnit, ICrudViewsHandler {
    private static final Double puntajeInicial = 5.00;
    private RepoDeMiembros repoMiembros;
    private RepoDeMediosDeNotificacion repoDeMediosDeNotificacion;
    private RepoDeRoles repoDeRoles;
    public UsuariosController(RepoDeMiembros repoMiembros, RepoDeMediosDeNotificacion repoDeMediosDeNotificacion, RepoDeRoles repoDeRoles){
        this.repoMiembros = repoMiembros;
        this.repoDeMediosDeNotificacion = repoDeMediosDeNotificacion;
        this.repoDeRoles = repoDeRoles;
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

        context.redirect("/");
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

            MedioDeNotificacion existe = repoDeMediosDeNotificacion.buscarPor("telefono", telefono);
            if(existe != null){
                modelo.put("error", "El teléfono ya está registrado.");
                context.render("Usuarios/register.hbs", modelo);
                return;
            }


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

        miembro.setRol(repoDeRoles.buscarPorTipoRol(TipoRol.NORMAL));
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

    @Override
    public void show(Context context) {
        Map<String, Object> modelo = new HashMap<>();
        long idUsuario = Integer.parseInt(context.pathParam("id"));
        Miembro usuario = repoMiembros.buscarPorId(idUsuario);

        modelo.put("usuario", usuario);
        context.render("Usuarios/show.hbs",modelo);
}

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {
        Miembro usuario = (Miembro) this.repoMiembros.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        context.render("Usuarios/admin.hbs", model);

    }

    @Override
    public void update(Context context) {
        Miembro usuario = (Miembro) this.repoMiembros.buscarPorId(Long.parseLong(context.pathParam("id")));

        this.asignarParametros(usuario, context);

        this.repoMiembros.modificar(usuario);

        long id = Long.parseLong(context.pathParam("id"));



        String redirectTo = "/usuario/" + id;

        context.redirect(redirectTo);


    }


    public void actualizar(Object o) {
        EntityTransaction tx = entityManager().getTransaction();
        if(!tx.isActive())
            tx.begin();
        entityManager().merge(o);
        tx.commit();
    }

    @Override
    public void delete(Context context) {

    }

    private void asignarParametros(Miembro usuario, Context context) {
        Map<String, Object> modelo = new HashMap<>();

        String nombre = context.formParam("nombre");
        String apellido = context.formParam("apellido");
        String email = context.formParam("email");
        String medioNotificacion = context.formParam("medioSelect");

        Miembro miembroPorEmail = this.repoMiembros.buscarPor("mail", email);
        if (miembroPorEmail != null && usuario.getId() != miembroPorEmail.getId()) {
            modelo.put("error", "El email ya está registrado.");
            context.render("Usuarios/admin.hbs", modelo);
            return;
        }

        MedioDeNotificacion medioDeNotificacion;
        String atributo;
        String valor;

        if("telefono".equals(medioNotificacion)){
            String telefono = context.formParam("telefono");

            MedioDeNotificacion existe = repoDeMediosDeNotificacion.buscarPor("telefono", telefono);
            if(existe != null){
                modelo.put("error", "El teléfono ya está registrado.");
                context.render("Usuarios/admin.hbs", modelo);
                return;
            }

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

        usuario.medioDeNotificacion = medio;
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(email);




    }

    public void mostrarUsuario(Context context){
        Long idUsuario = Long.parseLong(context.pathParam("id"));
        Miembro usuario = repoMiembros.buscarPorId(idUsuario);
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("usuario", usuario);

        //todo
    }

}
