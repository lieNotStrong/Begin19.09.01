package com.neuedu.controller.backend;

import com.neuedu.common.EnumClass;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.User;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manager/product")
public class ProductManagerController {


    @Autowired
    IProductService iProductService;

    @RequestMapping(value = "/save.do")
    public ServerResponse addOrUpdate(HttpSession session, Product product){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }
        if (user.getRole()== EnumClass.Role.ROLE_USER.getRoleCode()){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"该用户没有权限");
        }

        return iProductService.addOrUpdate(product);
    }

    @RequestMapping(value = "/set_sale_status.do")
    public ServerResponse set_sale_status(HttpSession session,
                                          @RequestParam("productid") Integer productid,
                                          @RequestParam("status") Integer status){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }
        if (user.getRole()==EnumClass.Role.ROLE_USER.getRoleCode()){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户没有权限");
        }

        return iProductService.set_sale_status(productid,status);

    }

    @RequestMapping(value = "/detail.do")
    public ServerResponse detail(HttpSession session,Integer productid){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }
        if (user.getRole()==EnumClass.Role.ROLE_USER.getRoleCode()){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户没有权限");
        }

        return iProductService.detail(productid);

    }

    @RequestMapping(value = "/list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }
        if (user.getRole()==EnumClass.Role.ROLE_USER.getRoleCode()){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户没有权限");
        }

        return iProductService.list(pageNum, pageSize);

    }

    @RequestMapping(value = "/search.do")
    public ServerResponse search(HttpSession session,
                               @RequestParam(value = "productId",required = false)Integer productId,
                               @RequestParam(value = "productName",required = false)String productName,
                               @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }
        if (user.getRole()==EnumClass.Role.ROLE_USER.getRoleCode()){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户没有权限");
        }

        return iProductService.search(productId,productName,pageNum, pageSize);

    }

}
