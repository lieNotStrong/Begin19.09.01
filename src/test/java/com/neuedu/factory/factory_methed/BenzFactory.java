package com.neuedu.factory.factory_methed;

public class BenzFactory implements Factory {
    @Override
    public Methed_Car getCar() {
        return new Benz();
    }
}
