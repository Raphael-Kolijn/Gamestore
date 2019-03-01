package domain.models;

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

    @OneToMany(targetEntity = Call.class)
    private List calls;

    @ManyToOne
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", calls=" + calls +
                ", company=" + company +
                '}';
    }
}
