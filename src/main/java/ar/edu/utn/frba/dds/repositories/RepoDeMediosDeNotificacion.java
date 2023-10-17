package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.models.domain.MediosDeComunicacion.MedioDeNotificacion;
import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoDeMediosDeNotificacion implements WithSimplePersistenceUnit {

    public void agregar(MedioDeNotificacion medioDeNotificacion){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(medioDeNotificacion);
        tx.commit();
    }
    public void eliminar(MedioDeNotificacion medioDeNotificacion){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(medioDeNotificacion);
        tx.commit();
    }

    public void modificar(MedioDeNotificacion medioDeNotificacion){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(medioDeNotificacion);
        tx.commit();
    }
    public MedioDeNotificacion buscarPorId(Integer id){
        return entityManager().find(MedioDeNotificacion.class,id);
    }
    public List<MedioDeNotificacion> buscarTodos(){
        return entityManager().createQuery("from " + MedioDeNotificacion.class.getName()).getResultList();

    }
}
