package Auth;


import domain.models.*;

import javax.ejb.*;
import javax.faces.context.*;
import javax.persistence.*;
import java.util.*;
import java.util.logging.*;

@Stateless
public class UserEJB {

    @PersistenceContext(unitName="gamestorePU")
    private EntityManager em;

    public User createUser(User user) {
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        Group group = new Group();
        group.setEmail(user.getEmail());
        group.setGroupname(Group.USERS_GROUP);

        em.persist(user);
        em.persist(group);

        return user;
    }

    public boolean login(User user) {
        if (user.getEmail() == "testAuthUser") {
            return false;
        }

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("User", user);

        return true;
    }

    public User findUserById(Long id) {
        TypedQuery<User> query = em.createNamedQuery("findUserById", User.class);
        query.setParameter("username", id);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }
        return user;
    }
}
