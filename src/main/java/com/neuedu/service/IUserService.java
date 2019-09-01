package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

public interface IUserService {

    /**
     * 用户注册
     */
    public ServerResponse register(User user);

    /**
     * 用户登录
     */
    public ServerResponse login(String username, String password);

    /**
     * 根据用户名查询密保问题
     */
    public ServerResponse forget_get_question(String username);

    /**
     * 根据用户名、密保问题来核对密保答案
     */
    public ServerResponse forget_check_answer(String username, String question, String answer);

    /**
     * 根据用户名、forgetTocken来修改密码
     */
    public ServerResponse forget_reset_password(String username, String password, String tocken);

    /**
     * 登录状态下修改密码
     */
    public ServerResponse reset_password(String username,String password, String newpassword);

    /**
     * 登录状态下修改用户信息
     * */
    public ServerResponse updata_information(User user);




}