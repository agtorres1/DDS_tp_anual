import ar.edu.utn.frba.dds.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


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
