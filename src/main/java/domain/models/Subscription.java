package domain.models;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Subscription.getById", query = "select s from Subscription s where s.id = :id"),
        @NamedQuery(name = "Subscription.getAll", query = "select s from Subscription s")
})
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float price;
    private String period;

    @ManyToOne
    private Call customer;

    @ManyToOne
    private Product product;

    public Subscription() {
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

    public Call getCustomer() {
        return customer;
    }

    public void setCustomer(Call customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", period='" + period + '\'' +
                ", customer=" + customer +
                ", product=" + product +
                '}';
    }
}
