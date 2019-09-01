package com.neuedu.guo_factory;

public class EnglandPizzaFactory implements PizzaFactory {
    @Override
    public Pizza getPizza() {
        return new EnglandPizza();
    }
}
