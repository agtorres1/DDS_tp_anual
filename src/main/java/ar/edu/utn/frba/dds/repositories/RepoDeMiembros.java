//package Repositorios;

import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/*public class RepoDeMiembros  implements WithSimplePersistenceUnit {

    public Miembro buscarPorNombreUsuarioYContrasenia(String nombreUsuario, String constrasenia){
        return EntityManagerHelper.getEntityManager().
            createQuery("from Usuario as u where u.nombreDeUsuario= :usuario and u.contrasenia= :pass", Usuario.class)
            .setParameter("usuario",nombreUsuario)
            .setParameter("pass",constrasenia)
            .getSingleResult();

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
    public Miembro buscarPorId(Integer id){
        return entityManager().find(Miembro.class,id);
    }
    public List<Miembro> buscarTodos(){
        return entityManager().createQuery("from " + Miembro.class.getName()).getResultList();

    }
}*/