package com.Andrey.CustomPizza.api.model;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Date time;
    private String conditionName;
    private Pizza pizza;
    private String phoneNumber;
    private String clientName;
    private double priceWithDiscount;
    private String deliveryAddress;
}
