package com.neuedu.guo_factory;

public class AmericaPizzaFactory implements PizzaFactory {
    @Override
    public Pizza getPizza() {
        return new AmericaPizza();
    }
}
