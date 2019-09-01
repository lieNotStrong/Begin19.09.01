package com.neuedu.factory.abstract_factory;


/**
 * 宝马工厂-->实现工厂接口
 * */
public class BMWFactory extends AbstractFactory {

    @Override
    public Abstract_Car getCar() {
        return new BMW();
    }
}
