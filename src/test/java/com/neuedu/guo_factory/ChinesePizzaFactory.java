package com.neuedu.guo_factory;

public class ChinesePizzaFactory implements PizzaFactory{
    @Override
    public Pizza getPizza() {
        return new ChinesePizza();
    }
}
