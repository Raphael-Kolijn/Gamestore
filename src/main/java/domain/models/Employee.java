package domain.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.getById", query = "select e from Employee e where e.id = :id"),
        @NamedQuery(name = "Employee.getAll", query = "select e from Employee e")
})
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String email;

    @OneToMany(targetEntity = Game.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List calls;

    @ManyToOne
    private Gamestore gamestore;

    public Employee() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List getCalls() {
        return calls;
    }

    public void setCalls(List calls) {
        this.calls = calls;
    }

    public Gamestore getGamestore() {
        return gamestore;
    }

    public void setGamestore(Gamestore gamestore) {
        this.gamestore = gamestore;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", calls=" + calls +
                ", gamestore=" + gamestore +
                '}';
    }
}
