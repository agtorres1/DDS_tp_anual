package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

public class RepoDeIncidentes  implements WithSimplePersistenceUnit {

    public void agregar(Incidente incidente){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(incidente);
        tx.commit();
    }
    public void eliminar(Incidente incidente){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(incidente);
        tx.commit();
    }

    public void modificar(Incidente incidente){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(incidente);
        tx.commit();
    }
    public Incidente buscarPorId(Long id){
        return entityManager().find(Incidente.class,id);
    }

/*    public List<Incidente> buscarPorFecha(LocalDateTime localDateTime){
        String consultaSQL = "SELECT i FROM Incidente i WHERE i.fachaYHoraApertura = :parametro";

        return entityManager().createQuery(consultaSQL, Incidente.class)
                .setParameter("parametro", localDateTime)
                .getResultList();
    }*/

    public List<Incidente> buscarTodos(){
        return entityManager().createQuery("from " + Incidente.class.getName()).getResultList();

    }

}