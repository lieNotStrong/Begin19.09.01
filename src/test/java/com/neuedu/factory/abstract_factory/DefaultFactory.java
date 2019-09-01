package com.neuedu.factory.abstract_factory;

public class DefaultFactory extends AbstractFactory{

    private AudiFactory defaultFactory = new AudiFactory();
    @Override
    public Abstract_Car getCar() {
        return defaultFactory.getCar();
    }
}
