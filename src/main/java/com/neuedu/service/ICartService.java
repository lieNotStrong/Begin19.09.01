package com.neuedu.service;

import com.neuedu.common.ServerResponse;

public interface ICartService {

    public ServerResponse addOrUpdate(Integer userId,Integer productId,Integer quantity);
    public ServerResponse list(Integer userId);
    public ServerResponse update(Integer userId,Integer productId,Integer quantity);
    public ServerResponse delete_product(Integer userId,String productIds);
    public ServerResponse get_cart_product_count(Integer userId);
    public ServerResponse select(Integer userId,Integer productId,Integer checked);
}
