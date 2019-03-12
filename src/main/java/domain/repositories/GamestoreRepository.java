package domain.repositories;

import domain.models.Gamestore;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class GamestoreRepository {

    @PersistenceContext(unitName = "gamestorePU")
    private EntityManager em;

    public List<Gamestore> getAll() {
        return em.createNamedQuery("Company.getAll", Gamestore.class).getResultList();
    }

    public Gamestore getById(Long id) {
        return em.createNamedQuery("Company.getById", Gamestore.class).setParameter("id", id).getSingleResult();
    }

    public void create(Gamestore gamestore) {
        em.persist(gamestore);
    }

    public void update(Gamestore gamestore) {
        em.merge(gamestore);
    }

    public void delete(Gamestore gamestore) {
        em.remove(gamestore);
    }
}
