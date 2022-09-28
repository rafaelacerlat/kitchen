package com.kitchen.app;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.Queue;

public class OrderHandling {

    private static Queue<Order> orders = new LinkedList<>();

    public static void collectOrder(Order order){
        orders.add(order);
    }
    public static Order getOrder(){
        return orders.poll();
    }


}
