package com.neuedu.factory.abstract_factory;

public abstract class AbstractFactory {


    public abstract Abstract_Car getCar();

    public Abstract_Car getCar(String name){
        if (name.equalsIgnoreCase("audi")){
            return new Audi();
        }else if (name.equalsIgnoreCase("bmw")){
            return new BMW();
        } else if (name.equalsIgnoreCase("benz")) {
            return new Benz();
        }else {
            System.out.println("此工厂生产不了此车型");
            return null;
        }
    }
}
