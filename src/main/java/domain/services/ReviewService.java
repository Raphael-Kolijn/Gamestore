package domain.services;

import domain.models.Review;
import domain.repositories.ReviewRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class ReviewService {

    @EJB
    private ReviewRepository repository;

    public List<Review> getAll() {
        return repository.getAll();
    }

    public Review getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Review review) {
        if (review == null)
            return false;

        repository.create(review);
        return true;
    }

    public boolean update(Review review) {
        if (review == null)
            return false;

        repository.update(review);
        return true;
    }

    public boolean delete(Long id) {
        Review review = repository.getById(id);

        if (review == null)
            return false;

        repository.delete(review);
        return true;
    }
}
