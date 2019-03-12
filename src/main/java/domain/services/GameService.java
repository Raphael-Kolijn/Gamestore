package domain.services;

import domain.models.Game;
import domain.repositories.GameRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class GameService {

    @EJB
    private GameRepository repository;

    public List<Game> getAll() {
        return repository.getAll();
    }

    public Game getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Game game) {
        if (game == null)
            return false;

        repository.create(game);
        return true;
    }

    public boolean update(Game game) {
        if (game == null)
            return false;

        repository.update(game);
        return true;
    }

    public boolean delete(Long id) {
        Game game = repository.getById(id);

        if (game == null)
            return false;

        repository.delete(game);
        return true;
    }
}
