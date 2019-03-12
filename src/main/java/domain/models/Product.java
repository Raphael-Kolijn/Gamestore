package domain.models;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Product.getById", query = "select p from Product p where p.id = :id"),
        @NamedQuery(name = "Product.getAll", query = "select p from Product p")
})
@Table(name = "calls")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private Gamestore gamestore;

    public Product() {
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

    public Gamestore getGamestore() {
        return gamestore;
    }

    public void setGamestore(Gamestore gamestore) {
        this.gamestore = gamestore;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gamestore=" + gamestore +
                '}';
    }
}
