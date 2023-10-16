package ar.edu.utn.frba.dds.repositories;//package Repositorios;

import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class RepoDeMiembros  implements WithSimplePersistenceUnit {

    public Miembro buscarPor(String atributo, String valor) {
        String query = "FROM " + Miembro.class.getName() + " user WHERE user." + atributo + " = :valor";
        try {
            return (Miembro) entityManager()
                    .createQuery(query)
                    .setParameter("valor", valor)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void agregar(Miembro miembro){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(miembro);
        tx.commit();
    }
    public void eliminar(Miembro miembro){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(miembro);
        tx.commit();
    }

    public void modificar(Miembro miembro){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(miembro);
        tx.commit();
    }
    public Miembro buscarPorId(Long id){
        return entityManager().find(Miembro.class,id);
    }
    public List<Miembro> buscarTodos(){
        return entityManager().createQuery("from " + Miembro.class.getName()).getResultList();

    }

    public boolean existeMiembroConNombreMiembro (String nombreMiembro){

        return true;
    }
}