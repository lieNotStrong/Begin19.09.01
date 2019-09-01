package com.neuedu.factory.factory_methed;


/**
 * 宝马工厂-->实现工厂接口
 * */
public class BMWFactory implements Factory {

    @Override
    public Methed_Car getCar() {
        return new BMW();
    }
}
