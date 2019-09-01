package com.neuedu.factory.simple_factory;

public class SimpleFactory {



    public Car  getCar(String carName){

        if (carName.equals("Benz")){
            return new Benz();
        }else if (carName.equals("BMW")){
            return new BMW();
        }else {

            System.out.println("此工厂不能生产此车型");
            return null;
        }





    }
}
