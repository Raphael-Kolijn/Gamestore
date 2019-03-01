package domain.repositories;

import domain.models.Call;
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
public class CallRepository {

    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public CallRepository() {
    }

    public List<Call> getAll() {
        return em.createNamedQuery("Call.getAll", Call.class).getResultList();
    }

    public Call getById(Long id) {
        return em.createNamedQuery("Call.getById", Call.class).setParameter("id", id).getSingleResult();
    }

    public void create(Call call) {
        em.persist(call);
    }

    public void update(Call call) {
        em.merge(call);
    }

    public void delete(Call call) {
        em.remove(call);
    }
}