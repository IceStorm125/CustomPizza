package com.Andrey.CustomPizza.api.JSONmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderJSON {

    private double currentPrice;
    private String pizzaName;
    private String doughs;
    private List<String> cheeses;
    private List<String> meets;
    private List<String> sauces;
    private List<String> sausages;
    private List<String> vegetables;
    private List<String> others;
}
