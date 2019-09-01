package com.neuedu.factory.simple_factory;

public class SimpleFactoryTest {


    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        String carName1 = "Benz";
        String carName2 = "BMW";
        String carName3 = "Audi";
        Car car = simpleFactory.getCar(carName2);
        System.out.println(car.getName());
    }
}
