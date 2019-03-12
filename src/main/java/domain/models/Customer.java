package domain.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.getById", query = "select c from Customer c where c.id = :id"),
        @NamedQuery(name = "Customer.getAll", query = "select c from Customer c")
})
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;

    @OneToMany(targetEntity = Game.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List calls;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List getCalls() {
        return calls;
    }

    public void setCalls(List calls) {
        this.calls = calls;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", calls=" + calls +
                '}';
    }
}
