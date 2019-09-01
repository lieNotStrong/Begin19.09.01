package com.neuedu.guo_factory;


/**
 * 简单共产生产pizza
 * */
public class SimpleFactory {

    public Pizza getPizza(String pizzaName){

        if (pizzaName.equalsIgnoreCase("chinesePizza")){
            return new ChinesePizza();
        }else if (pizzaName.equalsIgnoreCase("americaPizza")){
            return new AmericaPizza();
        }else {
            System.out.println("此工厂生产不出您所需的pizza");
            return null;
        }
    }
}
