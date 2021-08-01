package com.manhcode.rest.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.manhcode.rest.demo.jsonview.Views;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    @JsonView(Views.Internal.class)
    private int id;

    @Column(name = "description")
    @JsonView(Views.Internal.class)
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Order() {}

    public Order(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }
}
