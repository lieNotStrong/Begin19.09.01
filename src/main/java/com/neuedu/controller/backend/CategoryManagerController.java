package com.neuedu.controller.backend;


import com.neuedu.common.EnumClass;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.User;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

@RestController
@RequestMapping(value = "/manager/categort")
public class CategoryManagerController {


    @Autowired
    ICategoryService iCategoryService;
    /**
     * 查看子品类
     * */

    @RequestMapping(value = "/get_category")
    public ServerResponse get_category(HttpSession session, Integer categoryId){

        User current_user = (User) session.getAttribute(StatusCode.CURRENT_USER);

        if (current_user==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户未登录");
        }
        if (current_user.getRole()==EnumClass.Role.ROLE_USER.getRoleCode()){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"该用户没有权限访问");
        }

        ServerResponse result = iCategoryService.get_category(categoryId);


        return result;

    }



    /**
     * 添加子节点
     * 利用了对象绑定，因为parentId已经在service层进行了默认设置，主键id设置了自动增长，所以没有必填参数
     * */
    @RequestMapping(value = "/add_category")
    public ServerResponse add_category(HttpSession session, Category category){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户未登录");
        }

        if (user.getRole()==EnumClass.Role.ROLE_USER.getRoleCode()){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"该用户没有权限访问");
        }

        ServerResponse serverResponse = iCategoryService.add_category(category);

        return serverResponse;
    }

    /**
     * 修改品类
     * 利用了对象绑定，必填参数是修改的品类id
     * */

    @RequestMapping(value = "/set_category")
    public ServerResponse set_category(HttpSession session,Category category){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户未登录");
        }

        if (user.getRole()==EnumClass.Role.ROLE_USER.getRoleCode()){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"该用户没有权限访问");
        }
        ServerResponse serverResponse = iCategoryService.set_category(category);

        return serverResponse;
    }

    /**
     * 递归查询子类别
     * */

    @RequestMapping(value = "/get_deep_category")
    public ServerResponse get_deep_category(HttpSession session,Integer categoryId){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户未登录");
        }

        if (user.getRole()==EnumClass.Role.ROLE_USER.getRoleCode()){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"该用户没有权限访问");
        }

        ServerResponse deep_category = iCategoryService.get_deep_category(categoryId);


        return deep_category;

    }

}
