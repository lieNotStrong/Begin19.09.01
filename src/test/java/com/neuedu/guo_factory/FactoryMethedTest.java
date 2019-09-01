package com.neuedu.guo_factory;

public class FactoryMethedTest {

    public static void main(String[] args) {
        PizzaFactory pizzaFactory = new ChinesePizzaFactory();
        String pizzaName = pizzaFactory.getPizza().getPizzaName();
        System.out.println(pizzaName);
    }
}
