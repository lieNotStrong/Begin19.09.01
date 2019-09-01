package com.neuedu.factory.abstract_factory;

public class AudiFactory extends AbstractFactory {
    @Override
    public Abstract_Car getCar() {
        return new Audi();
    }
}
