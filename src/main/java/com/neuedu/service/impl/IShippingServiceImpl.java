package com.neuedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.dao.ShippingMapper;
import com.neuedu.pojo.Shipping;
import com.neuedu.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IShippingServiceImpl implements IShippingService {

    @Autowired
    ShippingMapper shippingMapper;


    @Override
    public ServerResponse add(Integer userId,Shipping shipping) {

        if (shipping==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        shipping.setUserId(userId);
        int result = shippingMapper.insert(shipping);
        if (result<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"添加地址失败");
        }

        Map<String,Integer> returnResult =  new HashMap<>();
        returnResult.put("shippingId",shipping.getId());

        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,returnResult,"添加地址成功");
    }

    @Override
    public ServerResponse delete(Integer userId,Integer shippingId) {

        if (shippingId==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        int result = shippingMapper.deleteByUserIdAndShippingId(userId,shippingId);
        if (result<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"删除失败,请输入正确的shippingId");
        }

        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,"删除成功");
    }

    @Override
    public ServerResponse update(Integer userId, Shipping shipping) {

        //step1:参数的非空校验
        if (shipping.getId()==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        shipping.setUserId(userId);
        int result = shippingMapper.updateByUserIdAndShippingId(shipping);
        if (result<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"修改失败");
        }

        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,"修改成功");
    }

    @Override
    public ServerResponse select(Integer userId, Integer shippingId) {

        if (shippingId==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        Shipping shipping = shippingMapper.selectByUserIdAndShippingId(userId,shippingId);
        if (shipping==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"此用户没有此地址");
        }

        return ServerResponse.serverResponseBySuccess(shipping);
    }

    @Override
    public ServerResponse list(Integer userId,Integer pageNum,Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo<Shipping> pageInfo = new PageInfo<>(shippingList);

        return ServerResponse.serverResponseBySuccess(pageInfo);
    }
}
