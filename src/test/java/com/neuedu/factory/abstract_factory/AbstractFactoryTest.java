package com.neuedu.factory.abstract_factory;

public class AbstractFactoryTest {

    public static void main(String[] args) {

        DefaultFactory defaultFactory = new DefaultFactory();
        Abstract_Car car = defaultFactory.getCar("asda");

        System.out.println(car.getName());
    }
}
