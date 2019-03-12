package domain.repositories;

import domain.models.Review;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class ReviewRepository {

    @PersistenceContext(unitName = "gamestorePU")
    private EntityManager em;

    public List<Review> getAll() {
        return em.createNamedQuery("Subscription.getAll", Review.class).getResultList();
    }

    public Review getById(Long id) {
        return em.createNamedQuery("Subscription.getById", Review.class).setParameter("id", id).getSingleResult();
    }

    public void create(Review review) {
        em.persist(review);
    }

    public void update(Review review) {
        em.merge(review);
    }

    public void delete(Review review) {
        em.remove(review);
    }
}
