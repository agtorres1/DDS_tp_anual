package Repositorios;

import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
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
    public Incidente buscarPorId(Integer id){
        return entityManager().find(Incidente.class,id);
    }
    public List<Incidente> buscarTodos(){
        return entityManager().createQuery("from " + Establecimiento.class.getName()).getResultList();

    }
}