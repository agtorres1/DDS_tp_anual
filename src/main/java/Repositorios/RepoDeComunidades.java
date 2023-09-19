package Repositorios;

import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoDeComunidades  implements WithSimplePersistenceUnit {

    public void agregar(Establecimiento establecimiento){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(establecimiento);
        tx.commit();
    }
    public void eliminar(Establecimiento establecimiento){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(establecimiento);
        tx.commit();
    }

    public void modificar(Establecimiento establecimiento){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(establecimiento);
        tx.commit();
    }
    public Establecimiento buscarPorId(Integer id){
        return entityManager().find(Establecimiento.class,id);
    }
    public List<Establecimiento> buscarTodos(){
        return entityManager().createQuery("from " + Establecimiento.class.getName()).getResultList();

    }
}