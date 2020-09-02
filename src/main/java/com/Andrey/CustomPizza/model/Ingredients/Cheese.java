package com.Andrey.CustomPizza.model.Ingredients;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "ingredients",name = "cheese")

public class Cheese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private String imgSrc;
}

