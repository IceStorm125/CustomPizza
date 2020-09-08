package com.Andrey.CustomPizza.api.model;

import com.Andrey.CustomPizza.model.Ingredients.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PizzaDTO {

    private List<Cheese> cheeses;
    private List<Dough> doughs;
    private List<Meet> meets;
    private List<Other> others;
    private List<Sauce> sauces;
    private List<Sausage> sausages;
    private List<Vegetable> vegetables;
}
