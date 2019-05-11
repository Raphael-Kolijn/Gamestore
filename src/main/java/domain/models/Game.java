package domain.models;

import domain.Interceptors.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.interceptor.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Game.getById", query = "select c from Game c where c.id = :id"),
        @NamedQuery(name = "Game.getAll", query = "select c from Game c")
})
@Interceptors({GameInterceptor.class})
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    private Long id;
    @DecimalMax("70.00")
    private int price;
    @Size(min=2, max=240)
    private String name;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Employee employee;

    @OneToMany(targetEntity = Review.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List subscriptions;

    public Game() {    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int duration) {
        this.price = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String date) {
        this.name = date;
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
        return "Game{" +
                "id=" + id +
                ", duration=" + price +
                ", date=" + name +

                '}';
    }
}
