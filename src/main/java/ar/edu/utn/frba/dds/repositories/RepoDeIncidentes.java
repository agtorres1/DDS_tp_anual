package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.incidentes.IncidenteResumido;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.*;

public class RepoDeIncidentes  implements WithSimplePersistenceUnit {

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
    public List<Incidente> obtenerIncidentesPorLocalidadYProvincia(String localidad, String provincia) {
        TypedQuery<Incidente> query = entityManager().createQuery(
                "SELECT i FROM Incidente i " +
                        "JOIN i.establecimiento e " +
                        "JOIN e.localizacion l " +
                        "JOIN l.municipio m " +
                        "JOIN l.provincia p " +
                        "JOIN i.abridor a " +
                        "WHERE m.nombre = :localidad " +
                        "AND p.nombre = :provincia " +
                        " AND i.abierto" +
                        " ORDER BY a.id , i.fachaYHoraApertura", Incidente.class);

        query.setParameter("localidad", localidad);
        query.setParameter("provincia", provincia);

        return query.getResultList();
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

/*    public List<Incidente> buscarPorFecha(LocalDateTime localDateTime){
        String consultaSQL = "SELECT i FROM Incidente i WHERE i.fachaYHoraApertura = :parametro";

        return entityManager().createQuery(consultaSQL, Incidente.class)
                .setParameter("parametro", localDateTime)
                .getResultList();
    }*/
public List<IncidenteResumido> agruparIncidentes(List<Incidente> incidentes) {
    Map<String, IncidenteResumido> incidentesAgrupados = new HashMap<>();

    for (Incidente incidente : incidentes) {
        String claveAgrupacion = incidente.getAbridor() + "-" + incidente.getFachaYHoraApertura();

        if (incidentesAgrupados.containsKey(claveAgrupacion)) {
            IncidenteResumido incidenteResumido = incidentesAgrupados.get(claveAgrupacion);
            incidenteResumido.getComunidades().add(incidente.getComunidad());
        } else {
            IncidenteResumido incidenteResumido = new IncidenteResumido();
            incidenteResumido.setObservaciones(incidente.getObservaciones());
            incidenteResumido.setEstablecimiento(incidente.getEstablecimiento());
            incidenteResumido.setComunidades(new ArrayList<>(Collections.singletonList(incidente.getComunidad())));
            incidentesAgrupados.put(claveAgrupacion, incidenteResumido);
        }
    }

    return new ArrayList<>(incidentesAgrupados.values());
}
    public List<Incidente> buscarTodos(){
        return entityManager().createQuery("from " + Incidente.class.getName()).getResultList();

    }

}