package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.models.domain.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.stream.Collectors;

public class RepoDeComunidades  implements WithSimplePersistenceUnit {
    public void agregar(Comunidad comunidad) {
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
    public Comunidad buscarPorId(Long id){
        return entityManager().find(Comunidad.class,id);
    }

    public List<Comunidad> buscarRestantes(Miembro miembro){
        List<Comunidad> comunidades = buscarTodos();
        return comunidades.stream().filter(comunidad -> !comunidad.getMiembros().contains(miembro)).collect(Collectors.toList());
    }
    public List<Comunidad> buscarTodos(){
        return entityManager().createQuery("from " + Comunidad.class.getName()).getResultList();
    }
}