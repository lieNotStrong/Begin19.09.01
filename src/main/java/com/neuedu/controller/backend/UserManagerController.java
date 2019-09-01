package com.neuedu.controller.backend;


import com.neuedu.common.EnumClass;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.pojo.User;
import com.neuedu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user/manager")
public class UserManagerController {





    @Autowired
    IUserService iUserService;


    @RequestMapping(value = "/login/{username}/{password}")
    public ServerResponse managerLogin(@PathVariable("username") String username,
                                        @PathVariable("password") String password,
                                        HttpSession session){
        //执行login方法
        ServerResponse serverResponse = iUserService.login(username, password);
        //判断login方法响应是否成功
        if (serverResponse.isSuccess()){
            User user = (User) serverResponse.getData();
            if (user.getRole()== EnumClass.Role.ROLE_USER.getRoleCode()){
                return ServerResponse.serverResponseByError(StatusCode.ERROR,"您没有登录权限");
            }

           session.setAttribute(StatusCode.CURRENT_USER,user);
        }

        return serverResponse;
    }

}
