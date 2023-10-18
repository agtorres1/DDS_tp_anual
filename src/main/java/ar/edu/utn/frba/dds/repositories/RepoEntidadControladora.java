package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.models.domain.entidadesExtra.EntidadControladora;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
<<<<<<< HEAD

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoEntidadControladora implements WithSimplePersistenceUnit {
    public void agregar(EntidadControladora entidadControladora){
=======
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityTransaction;

public class RepoEntidadControladora implements WithSimplePersistenceUnit {
    public void agregar(EntidadControladora entidadControladora) {
>>>>>>> 945385735acc48f8d318ca0953ef15841b9fc91d
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(entidadControladora);
        tx.commit();
    }

<<<<<<< HEAD
    public List<EntidadControladora> buscarTodos(){
=======
    public void eliminar(EntidadControladora entidadControladora) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(entidadControladora);
        tx.commit();
    }

    public void modificar(EntidadControladora entidadControladora) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(entidadControladora);
        tx.commit();
    }
    public EntidadControladora buscarPorId(Long id) {
        return entityManager().find(EntidadControladora.class, id);
    }
    public List<EntidadControladora> buscarTodos() {
>>>>>>> 945385735acc48f8d318ca0953ef15841b9fc91d
        return entityManager().createQuery("from " + EntidadControladora.class.getName()).getResultList();
    }
}
