package com.kitchen.app;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cook extends Thread {
    private String name;

    private int cook_id;
    private int rank;
    private int proficiency;
    private String catch_phrase;

    private static final Logger logger = LogManager.getLogger(Cook.class);


    public String getCatch_phrase() {
        return catch_phrase;
    }

    public void setCatch_phrase(String catch_phrase) {
        this.catch_phrase = catch_phrase;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCook_id() {
        return cook_id;
    }
    public void setCook_id(int cook_id) {
        this.cook_id = cook_id;
    }


    @Override
    public void run(){
        while (true){
            try {
                Order order = OrderHandling.getOrder();
                if (order != null) {
                    PreparedOrder preparedOrder = cookOrder(order, cook_id);
                    sendPreparedOrder(preparedOrder);
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private PreparedOrder cookOrder(Order order, int cook_id){
        try {
            PreparedOrder preparedOrder = new PreparedOrder();
          //  BeanUtils.copyProperties(order, preparedOrder);

            preparedOrder.setOrder_id(order.getOrderId());
            preparedOrder.setTable_id(order.getTableId());
            preparedOrder.setWaiter_id(order.getWaiterId());
            preparedOrder.setItems(order.getItems());
            preparedOrder.setMax_wait(order.getMaxWait());
            preparedOrder.setPriority(order.getPriority());
            preparedOrder.setPick_up_time(order.getPickUpTime());


            Long cooking_time = order.getMaxWait(); // cooking time will be max_wait time
            preparedOrder.setCooking_time(cooking_time);

            List<CookingDetails> cookingDetails = new ArrayList<>();
            for (Integer food_id : order.getItems()) {
                CookingDetails details = new CookingDetails();
                details.setCook_id(cook_id);
                details.setFood_id(food_id);
                cookingDetails.add(details);
            }
            preparedOrder.setCooking_details(cookingDetails);
            return preparedOrder;

        } catch (Exception e ){
            throw new RuntimeException(e);
        }

    }

    public void sendPreparedOrder(PreparedOrder preparedOrder) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<PreparedOrder> requestBody = new HttpEntity<>(preparedOrder);
        logger.log(Level.INFO, "Sending prepared order back to dinning hall" + requestBody.getBody().toString());
        restTemplate.postForObject("http://localhost:8081/distribution", requestBody, PreparedOrder.class);

    }
}
