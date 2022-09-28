package com.kitchen.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Kitchen {

	public static List<Cook> cooks = new ArrayList<>();

	public static void main(String[] args) {

		SpringApplication.run(Kitchen.class, args);
		initiateCooks(5);

		for(Cook cook: cooks){
			new Thread(cook).start();
		}
	}

	public static void initiateCooks(int numberOfCooks){
		for(int i = 1; i <= numberOfCooks; i++){
			Cook cook = new Cook();
			// set / generate name for cook
			cooks.add(cook);
		}
	}
}
