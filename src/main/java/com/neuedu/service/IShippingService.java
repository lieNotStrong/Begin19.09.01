package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;

public interface IShippingService {

    /**
     * 添加地址
     * */
    public ServerResponse add(Integer userId,Shipping shipping);
    /**
     * 删除地址
     * */
    public ServerResponse delete(Integer userId,Integer shippingId);
    /**
     * 修改地址
     * */
    public ServerResponse update(Integer userId,Shipping shipping);
    /**
     * 查看地址
     * */
    public ServerResponse select(Integer userId,Integer shippingId);
    /**
     * 地址列表
     * */
    public ServerResponse list(Integer userId,Integer pageNum,Integer pageSize);
}
