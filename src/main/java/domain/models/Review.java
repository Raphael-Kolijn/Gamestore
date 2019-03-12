package domain.models;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Subscription.getById", query = "select s from Review s where s.id = :id"),
        @NamedQuery(name = "Subscription.getAll", query = "select s from Review s")
})
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float price;
    private String period;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Customer customer;

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game customer) {
        this.game = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", period='" + period + '\'' +
                ", customer=" + game +
                ", product=" + customer +
                '}';
    }
}
