package Repositorios;

import ar.edu.utn.frba.dds.domain.comunidades.Interes;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoDeIntereses  implements WithSimplePersistenceUnit {

    public void agregar(Interes interes){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(interes);
        tx.commit();
    }
    public void eliminar(Interes interes){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(interes);
        tx.commit();
    }

    public void modificar(Interes interes){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(interes);
        tx.commit();
    }
    public Interes buscarPorId(Integer id){
        return entityManager().find(Interes.class,id);
    }
    public List<Interes> buscarTodos(){
        return entityManager().createQuery("from " + Interes.class.getName()).getResultList();

    }
}