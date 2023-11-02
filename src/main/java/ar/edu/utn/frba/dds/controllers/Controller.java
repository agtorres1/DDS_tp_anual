package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;

public class Controller implements WithSimplePersistenceUnit {
    protected Miembro miembroEnSesion(Context ctx) {
        if(ctx.sessionAttribute("usuario_id") == null)
            return null;
        return entityManager()
                .find(Miembro.class, ctx.sessionAttribute("usuario_id"));
    }
}
