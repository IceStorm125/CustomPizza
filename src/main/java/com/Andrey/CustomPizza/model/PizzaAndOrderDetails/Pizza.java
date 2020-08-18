package com.Andrey.CustomPizza.model.PizzaAndOrderDetails;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @NotNull(message = "Pick your dough!")
    private String dough;

    private String cheese;
    private String meet;
    private String sauce;
    private String sausage;
    private String vegetables;
    private String others;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;

}
