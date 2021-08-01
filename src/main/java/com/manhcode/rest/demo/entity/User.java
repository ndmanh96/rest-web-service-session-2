package com.manhcode.rest.demo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.manhcode.rest.demo.jsonview.Views;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
//@JsonIgnoreProperties(value={"email", "role"}) //  Static ignore 2 feild email and role
//@JsonFilter(value = "UserFilter") // Dynamic Filter
@ApiModel(description = "This model is to create user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.External.class)
    @ApiModelProperty(notes = "Auto generated unique id", required = true, position = 1)
    private Long id;

    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "Username is mandatory field. Please provide username")
    @JsonView(Views.External.class)
    @ApiModelProperty(notes = "Username should be flname", example = "ManhNguyen", required = false, position = 2)
    private String username;

    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    @Size(min = 2, message = "Firstname shout have atleast 2 characters")
    @JsonView(Views.External.class)
    private String firstname;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String lastname;

    @Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String email;

    @Column(name = "ROLE", length = 50, nullable = false)
    @JsonView(Views.Internal.class)
    private String role;

    @Column(name = "SSN", length = 50, nullable = false, unique = true)
//    @JsonIgnore  // ignore ssn
    @JsonView(Views.Internal.class)
    private String ssn;

    @OneToMany(mappedBy = "user")
    @JsonView(Views.Internal.class)
    private List<Order> orders;

    public User() {}

    public User(String username, String firstname, String lastname, String email, String role, String ssn) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        order.setUser(this);
        orders.add(order);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
