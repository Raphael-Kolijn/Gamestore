package domain.services;
import domain.models.User;
import domain.repositories.UserRepository;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService {

    @EJB
    UserRepository userRepo;



    public void create(User user) {
        userRepo.create(user);
    }

    public User getById(Long id) {
        return userRepo.getById(id);
    }

    public void update(User user) {
        userRepo.update(user);
    }

    public void delete(Long id) {
        userRepo.delete(id);
    }

    public List<User> All() {
        return userRepo.All();
    }

    public User getByEmail(String email) { return userRepo.getByEmail(email);}

//    public User findByEmail(String user) {
//        return userRepo.findByEmail(user);
//    }
}
