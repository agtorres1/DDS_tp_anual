package Repositorios;

import ar.edu.utn.frba.dds.domain.comunidades.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoDeComunidades  implements WithSimplePersistenceUnit {

    public void agregar(Comunidad comunidad){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(comunidad);
        tx.commit();
    }
    public void eliminar(Comunidad comunidad){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(comunidad);
        tx.commit();
    }

    public void modificar(Comunidad comunidad){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(comunidad);
        tx.commit();
    }
    public Comunidad buscarPorId(Integer id){
        return entityManager().find(Comunidad.class,id);
    }
    public List<Comunidad> buscarTodos(){
        return entityManager().createQuery("from " + Comunidad.class.getName()).getResultList();

    }
}