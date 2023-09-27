package Repositorios;

import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoDeEntidades implements WithSimplePersistenceUnit {

    public void agregar(Entidad entidad){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(entidad);
        tx.commit();
    }
    public void eliminar(Entidad entidad){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(entidad);
        tx.commit();
    }

    public void modificar(Entidad entidad){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(entidad);
        tx.commit();
    }
    public Entidad buscarPorId(Integer id){
        return entityManager().find(Entidad.class,id);
    }
    public List<Entidad> buscarTodos(){
        return entityManager().createQuery("from " + Entidad.class.getName()).getResultList();

    }
}