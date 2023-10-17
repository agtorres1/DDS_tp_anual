package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.models.domain.entidadesExtra.EntidadControladora;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoEntidadControladora implements WithSimplePersistenceUnit {
    public void agregar(EntidadControladora entidadControladora){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(entidadControladora);
        tx.commit();
    }

    public List<EntidadControladora> buscarTodos(){
        return entityManager().createQuery("from " + EntidadControladora.class.getName()).getResultList();
    }
}
