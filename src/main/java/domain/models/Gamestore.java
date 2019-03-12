package domain.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Company.getById", query = "select c from Gamestore c where c.id = :id"),
        @NamedQuery(name = "Company.getAll", query = "select c from Gamestore c")
})
@Table(name = "companies")
public class Gamestore {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(targetEntity = Employee.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List employees;

    @OneToMany(targetEntity = Product.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List products;

    public Gamestore() {
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

    public List getEmployees() {
        return employees;
    }

    public void setEmployees(List employees) {
        this.employees = employees;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Gamestore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", products=" + products +
                '}';
    }
}
