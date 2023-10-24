package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.repositories.convertsRepo.ConvertString;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

public class RepoDeIncidentes  implements WithSimplePersistenceUnit {

    private ConvertString convertString;

    public RepoDeIncidentes(){
        this.convertString = new ConvertString();
    }

    public void agregar(Incidente incidente){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(incidente);
        tx.commit();
    }
    public void eliminar(Incidente incidente){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(incidente);
        tx.commit();
    }

    public void modificar(Incidente incidente){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(incidente);
        tx.commit();
    }
    public Incidente buscarPorId(Long id){
        return entityManager().find(Incidente.class,id);
    }

    public List<Incidente> buscarPorListadoId(List<Long> ids){
        String idsSQL = this.convertString.converterListToString(ids);

        String query = "FROM " + Incidente.class.getName() + " incidente WHERE incidente.id in :idsSQL";

        return (List<Incidente>) entityManager()
                .createQuery(query)
                .setParameter("idsSQL", idsSQL)
                .getResultList();
    }
    public List<Incidente> buscarPorSemana(){
        String consultaSQL = "SELECT i FROM Incidente i WHERE DATEDIFF(CURDATE(), i.fachaYHoraApertura) <= 7";

        return entityManager().createQuery(consultaSQL, Incidente.class)
                .getResultList();
    }

    public List<Incidente> buscarTodos(){
        return entityManager().createQuery("from " + Incidente.class.getName()).getResultList();

    }

}