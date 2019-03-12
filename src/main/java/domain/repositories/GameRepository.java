package domain.repositories;

import domain.models.Game;
import domain.models.SimpleInterceptor;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
@Interceptors(SimpleInterceptor.class)
public class GameRepository {

    @PersistenceContext(unitName = "gamestorePU")
    private EntityManager em;

    public GameRepository() {
    }

    public List<Game> getAll() {
        return em.createNamedQuery("Game.getAll", Game.class).getResultList();
    }

    public Game getById(Long id) {
        return em.createNamedQuery("Game.getById", Game.class).setParameter("id", id).getSingleResult();
    }

    public void create(Game game) {
        em.persist(game);
    }

    public void update(Game game) {
        em.merge(game);
    }

    public void delete(Game game) {
        em.remove(game);
    }
}
