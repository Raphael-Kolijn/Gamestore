package domain.repositories;

import domain.Interceptors.*;
import domain.models.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.apache.openejb.persistence.PersistenceBootstrap.logger;

@Local
@Stateless
public class UserRepository {

    @PersistenceContext(unitName = "gamestorePU")
    public EntityManager em;

    @Interceptors(SimpleInterceptor.class)
    public void create(User user){
        try {
//            em.getTransaction().begin();
            em.persist(user);
//            em.getTransaction().commit();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public User getById(Long id) {
        try {
            return em.find(User.class, id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public User getByEmail(String email) {
        try {
            return em.find(User.class, email);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }


    public List<User> All(){
        List<User> users;
        try {
            users = em.createQuery(
                    "SELECT c FROM User c", User.class).getResultList();
            return users;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void update(User user) {
        try {
            em.merge(user);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    public User verifyCode(User user) {
        try {
            return em.createNamedQuery("User.getByEmailAndCode", User.class)
                    .setParameter("email", user.getEmail())
                    .setParameter("code", user.getVerification_code())
                    .getSingleResult();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    public User getByUsernameAndPassword(String username, String password) {
        try {
            return em.createNamedQuery("User.getByUsernameAndPassword", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
    }
}
