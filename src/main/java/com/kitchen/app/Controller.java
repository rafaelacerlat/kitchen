package com.kitchen.app;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);


	@PostMapping("/order")
    public void post(@RequestBody Order order) {
        OrderHandling.collectOrder(order);
        logger.log(Level.INFO, "Received the order:\n" + order.toString());
    }

}