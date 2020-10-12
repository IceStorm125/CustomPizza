package com.Andrey.CustomPizza.api.service;

import com.Andrey.CustomPizza.api.JSONmodel.OrderJSON;
import com.Andrey.CustomPizza.api.model.OrderDTO;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderResponseService implements OrderResponse{


    @Override
    public List<OrderDTO> getOrderResponse(List<Order> orders) {
        List<OrderDTO> orderResponse = new ArrayList<>();
        for(Order order:orders){
            orderResponse.add(new OrderDTO(
                    order.getId(),
                    order.getTime(),
                    order.getCondition().getName(),
                    order.getPizza(),
                    order.getUser().getPhoneNumber(),
                    order.getUser().getName(),
                    order.getPriceWithDiscount(),
                    order.getDeliveryAddress()
            ));
        }
        return orderResponse;
    }

    @Override
    public Pizza parsePizzaFromJSON(String JSONToParse){
        Gson gson = new Gson();
        OrderJSON order = gson.fromJson(JSONToParse,OrderJSON.class);

        Pizza pizza = new Pizza();
        pizza.setPrice(order.getCurrentPrice());
        pizza.setName(order.getPizzaName());
        pizza.setDough(order.getDoughs());
        pizza.setCheese(getStringFromJsonArray(order.getCheeses()));
        pizza.setMeet(getStringFromJsonArray(order.getMeets()));
        pizza.setSauce(getStringFromJsonArray(order.getSauces()));
        pizza.setSausage(getStringFromJsonArray(order.getSausages()));
        pizza.setVegetables(getStringFromJsonArray(order.getVegetables()));
        pizza.setOthers(getStringFromJsonArray(order.getOthers()));

        return pizza;
    }

    private String getStringFromJsonArray(List<String> list){
        if (list.isEmpty()){
            return null;
        }
        StringBuilder stringFromArray = new StringBuilder();
        for (String str:list) {
            stringFromArray.append(str).append(",");
        }
        stringFromArray.replace(stringFromArray.length()-1,stringFromArray.length(),"");
        return String.valueOf(stringFromArray);
    }
}
