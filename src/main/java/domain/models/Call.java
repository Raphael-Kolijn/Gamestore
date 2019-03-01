package domain.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Call.getById", query = "select c from Call c where c.id = :id"),
        @NamedQuery(name = "Call.getAll", query = "select c from Call c")
})
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue
    private Long id;
    private int duration;
    private Date date;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Employee employee;

    @OneToMany(targetEntity = Subscription.class)
    private List subscriptions;

    public Call() {    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                ", duration=" + duration +
                ", date=" + date +
                ", customer=" + customer +
                ", employee=" + employee +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
