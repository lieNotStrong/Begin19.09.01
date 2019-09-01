package com.neuedu.guo_factory;

public class SimpleFactoryTest {

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Pizza pizza = simpleFactory.getPizza("chinesepizza");
        System.out.println(pizza.getPizzaName());

    }
}
