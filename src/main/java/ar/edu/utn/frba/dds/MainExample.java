package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDateTime;


public class MainExample implements WithSimplePersistenceUnit {

    public static void main(String[] args) {

        new MainExample().start();
    }

    private void start() {

        Comunidad comunidad = new Comunidad();
        Miembro miembro= new Miembro();
        miembro.setUsuario("Pepe");

        Incidente incidente = new Incidente();
        LocalDateTime fechayhora = LocalDateTime.now();
        incidente.setFachaYHoraApertura(fechayhora);

        entityManager().getTransaction().begin();
       // entityManager().persist(miembro);
      //  entityManager().persist(comunidad);
       // entityManager().persist(incidente);
        entityManager().getTransaction().commit();
    }


}
