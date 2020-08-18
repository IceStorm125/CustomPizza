package com.Andrey.CustomPizza.model.PizzaAndOrderDetails;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "cond_ns")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "condition",cascade = CascadeType.ALL)
    private List<Order> orders;
}
