package com.neuedu.factory.factory_methed;

public class FactoryMethedTest {

    public static void main(String[] args) {
        BenzFactory benzFactory = new BenzFactory();
        Methed_Car car = benzFactory.getCar();
        BMWFactory bmwFactory = new BMWFactory();
        Methed_Car car1 = bmwFactory.getCar();

        System.out.println(car.getName()+" "+car1.getName());
    }
}
