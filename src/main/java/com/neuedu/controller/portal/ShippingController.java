package com.neuedu.controller.portal;


import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.pojo.Shipping;
import com.neuedu.pojo.User;
import com.neuedu.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/shipping")
public class ShippingController {

    @Autowired
    IShippingService iShippingService;

    /**
     * 添加地址
     * */
    @RequestMapping(value = "/add.do")
    public ServerResponse add(HttpSession session,Shipping shipping){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }

        return iShippingService.add(user.getId(),shipping);

    }

    /**
     * 删除地址
     * */
    @RequestMapping(value = "/delete.do")
    public ServerResponse delete(HttpSession session,Integer shippingId){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }

        return iShippingService.delete(user.getId(),shippingId);

    }


    /**
     * 登录状态更新地址
     * */
    @RequestMapping(value = "/update.do")
    public ServerResponse update(HttpSession session,Shipping shipping){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }

        return iShippingService.update(user.getId(),shipping);

    }

    /**
     * 选中查看具体的地址
     * */
    @RequestMapping(value = "/select.do")
    public ServerResponse select(HttpSession session,Integer shippingId){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }

        return iShippingService.select(user.getId(),shippingId);

    }

    /**
     * 地址列表
     * */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(HttpSession session, @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }

        return iShippingService.list(user.getId(),pageNum,pageSize);

    }

}
