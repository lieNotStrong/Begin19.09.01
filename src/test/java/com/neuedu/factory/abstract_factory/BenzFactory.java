package com.neuedu.factory.abstract_factory;

public class BenzFactory extends AbstractFactory {
    @Override
    public Abstract_Car getCar() {
        return new Benz();
    }
}
