package Repositorios;

import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoDePrestacionDeServicio  implements WithSimplePersistenceUnit {

    public void agregar(PrestacionDeServicio prestacionDeServicio){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(prestacionDeServicio);
        tx.commit();
    }
    public void eliminar(PrestacionDeServicio prestacionDeServicio){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(prestacionDeServicio);
        tx.commit();
    }

    public void modificar(PrestacionDeServicio prestacionDeServicio){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(prestacionDeServicio);
        tx.commit();
    }
    public PrestacionDeServicio buscarPorId(Integer id){
        return entityManager().find(PrestacionDeServicio.class,id);
    }
    public List<PrestacionDeServicio> buscarTodos(){
        return entityManager().createQuery("from " + PrestacionDeServicio.class.getName()).getResultList();

    }
}