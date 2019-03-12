package domain.services;

import domain.models.Gamestore;
import domain.repositories.GamestoreRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class GamestoreService {

    @EJB
    private GamestoreRepository repository;

    public List<Gamestore> getAll() {
        return repository.getAll();
    }

    public Gamestore getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Gamestore gamestore) {
        if (gamestore == null)
            return false;

        repository.create(gamestore);
        return true;
    }

    public boolean update(Gamestore gamestore) {
        if (gamestore == null)
            return false;

        repository.update(gamestore);
        return true;
    }

    public boolean delete(Long id) {
        Gamestore gamestore = repository.getById(id);

        if (gamestore == null)
            return false;

        repository.delete(gamestore);
        return true;
    }
}
