package com.Andrey.CustomPizza.model.Ingredients;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dough")
public class Dough {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private String imgSrc;

}
