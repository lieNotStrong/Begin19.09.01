package com.neuedu.controller.portal;


import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.pojo.User;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.IUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    IUserService iUserService;

    @Autowired
    IUserServiceImpl iUserServiceTest;



    /**
     * 注册
     * */
    @RequestMapping(value = "/register")
    public ServerResponse register(User user){

        ServerResponse result = iUserService.register(user);
        return result;
    }

    /**
     * 登录
     * */
    @RequestMapping(value = "/login/{username}/{password}")
    public ServerResponse login(HttpSession session,
                                @PathVariable("username") String username,
                                @PathVariable("password") String password){

        ServerResponse serverResponse = iUserService.login(username, password);
        if (serverResponse.isSuccess()){
            User user = (User) serverResponse.getData();
            session.setAttribute(StatusCode.CURRENT_USER,user);
        }

        return serverResponse;
    }

    /**
     * 根据用户名查询密保问题
     * */
    @RequestMapping(value = "/forget_get_question")
    public ServerResponse forget_get_question(@RequestParam("username") String username){

        return iUserService.forget_get_question(username);
    }

    /**
     * 根据用户名、密保问题来核对密保答案
     * */
    @RequestMapping(value = "/forget_check_answer")
    public ServerResponse forget_check_answer(@RequestParam("username") String username,
                                              @RequestParam("question") String question,
                                              @RequestParam("answer") String answer){

        return iUserService.forget_check_answer(username, question, answer);
    }

    /**
     * 根据用户名、forgetTocken来修改密码
     * */
    @RequestMapping(value = "/forget_reset_password")
    public ServerResponse forget_reset_password(@RequestParam("username") String username,
                                              @RequestParam("password") String password,
                                              @RequestParam("tocken") String tocken){

        return iUserService.forget_reset_password(username,password,tocken);
    }

    /**
     * 登录状态下修改密码
     * */
    @RequestMapping(value = "/reset_password")
    public ServerResponse reset_password(HttpSession session,@RequestParam("password") String password,@RequestParam("newpassword") String newpassword){

        User user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录或登录已过期");
        }
        return iUserService.reset_password(user.getUsername(),password,newpassword);
    }

    /**
     * 更新用户信息
     * */
    @RequestMapping(value = "/updata_information")
    public ServerResponse updata_information(User user,HttpSession session){

        User current_user = (User) session.getAttribute(StatusCode.CURRENT_USER);
        if (current_user==null){
            return ServerResponse.serverResponseByError(StatusCode.UN_LOGIN,"用户未登录或登录已过期");
        }
       user.setId(current_user.getId());

        return iUserService.updata_information(user);

    }

    /**
     * 退出登录
     * */
    @RequestMapping(value = "/login_out")
    public ServerResponse login_out(HttpSession session){


        session.removeAttribute(StatusCode.CURRENT_USER);
        return ServerResponse.serverResponseBySuccess();


    }



}
