package com.neuedu.controller.portal;


import com.neuedu.common.EnumClass;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.pojo.User;
import com.neuedu.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    ICartService iCartService;

   /**
    * 添加购物车
    * */
   @RequestMapping(value = "/add.do")
   public ServerResponse add(HttpSession session, @RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity){

       User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
       if (user==null){
           return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
       }

       return iCartService.addOrUpdate(user.getId(),productId,quantity);
   }

   /**
    * 查看购物车列表
    * */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(HttpSession session){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }

        return iCartService.list(user.getId());
    }

    /**
     * 更新购物车某个商品数量
     * */
    @RequestMapping(value = "/update.do")
    public ServerResponse update(HttpSession session, @RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }

        return iCartService.update(user.getId(),productId,quantity);
    }

    /**
     * 移除购物车某个商品数量
     * */
    @RequestMapping(value = "/delete_product.do")
    public ServerResponse delete_product(HttpSession session,String productIds){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }

        return iCartService.delete_product(user.getId(),productIds);
    }

    /**
     * 移除购物车某个商品数量
     * */
    @RequestMapping(value = "/get_cart_product_count.do")
    public ServerResponse get_cart_product_count(HttpSession session){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }

        return iCartService.get_cart_product_count(user.getId());
    }

    /**
     * 购物车选中某个商品
     * */
    @RequestMapping(value = "/select.do")
    public ServerResponse select(HttpSession session,Integer productId){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }
        return iCartService.select(user.getId(),productId, EnumClass.CartIsChecked.PRODUCT_CHECKED.getRoleCode());
    }

    /**
     * 购物车取消选中某个商品
     * */
    @RequestMapping(value = "/un_select.do")
    public ServerResponse un_select(HttpSession session,Integer productId){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }
        return iCartService.select(user.getId(),productId, EnumClass.CartIsChecked.PRODUCT_UNCHECKED.getRoleCode());
    }

    /**
     * 购物车全选商品
     * */
    @RequestMapping(value = "/all_select.do")
    public ServerResponse all_select(HttpSession session){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }
        return iCartService.select(user.getId(),null, EnumClass.CartIsChecked.PRODUCT_CHECKED.getRoleCode());
    }
    /**
     * 购物车取消全选商品
     * */
    @RequestMapping(value = "/all_unselect.do")
    public ServerResponse all_unselect(HttpSession session){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录");
        }
        return iCartService.select(user.getId(),null, EnumClass.CartIsChecked.PRODUCT_UNCHECKED.getRoleCode());
    }



}
