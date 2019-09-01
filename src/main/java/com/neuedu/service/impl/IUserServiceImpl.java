package com.neuedu.service.impl;

import com.neuedu.common.EnumClass;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.dao.UserMapper;
import com.neuedu.pojo.User;
import com.neuedu.service.IUserService;
import com.neuedu.utils.MD5Utils;
import com.neuedu.utils.TokenCache;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IUserServiceImpl implements IUserService{


    @Autowired
    UserMapper userMapper;

    @Override
    public ServerResponse register(User user) {

        //step1:参数的非空校验
        if (user == null) {
            return ServerResponse.serverResponseByError(StatusCode.ERROR, "参数不能为空");
        }
        //step2：判断用户名在数据库中是否存在

        int i = userMapper.usernameIsexist(user.getUsername());
        if (i > 0) {
            return ServerResponse.serverResponseByError(StatusCode.ERROR, "用户名已存在");
        }
        //step4：判断邮箱在数据库中是否存在
        int i1 = userMapper.emailIsexist(user.getEmail());
        if (i1 > 0) {
            return ServerResponse.serverResponseByError(StatusCode.ERROR, "邮箱已存在");
        }
        //step5：条件判断完毕，给新注册的用户密码进行MD5加密
        user.setPassword(MD5Utils.getMD5Code(user.getPassword()));
        //step6：给新用户添加默认角色role_user=1
        user.setRole(EnumClass.Role.ROLE_USER.getRoleCode());
        //step6：返回结果
        int insert = userMapper.insert(user);
        if (insert <=0) {
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"注册失败，菜鸡！");
        }


        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS, "注册成功，你真牛逼！");

    }

    @Override
    public ServerResponse login(String username, String password) {

        //step1:参数的非空校验
        if (username==null&&username.equals("")){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户名不能为空");
        }
        if (password==null&&password.equals("")){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"密码不能为空");
        }
        //step2：判断用户名在数据库中是否存在
        int i = userMapper.usernameIsexist(username);
        if (i<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户名不存在");
        }
        //step3:给密码加密
        password = MD5Utils.getMD5Code(password);
        User user = userMapper.selectUserByUsernameAndPassowrd(username, password);
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"密码错误");
        }

        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,user,"登录成功");
    }

    @Override
    public ServerResponse forget_get_question(String username) {


        //step1：参数的非空判断
        if (username==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        int i = userMapper.usernameIsexist(username);
        if (i<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户名不存在");
        }
        //step2:根据用户名查询密保问题
        String question = userMapper.selectQuestionByUsername(username);
        if (question==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"查询密保问题为空");
        }

        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,question,"问题查询成功");
    }

    @Override
    public ServerResponse forget_check_answer(String username, String question, String answer) {

        //step1：参数的非空判断
        if (username==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        if (question==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        if (answer==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        int i1 = userMapper.usernameIsexist(username);
        if (i1<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户名不存在");
        }
        //step2：检查答案是否存在
        int i = userMapper.checkAnswerByUsernameAndQuestionAndAnswer(username, question, answer);
        if (i<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"回答错误");
        }
        String forgetTocken = UUID.randomUUID().toString();
        TokenCache.set("username:"+username,forgetTocken);
        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,forgetTocken,"密保问题回答正确");
    }

    @Override
    public ServerResponse forget_reset_password(String username, String password, String tocken) {

        //step1:参数的非空校验
        if (username==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        if (password==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        if (tocken==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        int i1 = userMapper.usernameIsexist(username);
        //step2:判断用户名是否存在
        if (i1<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户名不存在");
        }
        //step3:判断tocken
        String forgetTocken = TokenCache.get("username:" + username);

        if (forgetTocken==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"请修改自己的密码");
        }
        if (!forgetTocken.equals(tocken)){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"请输入正确的tocken");
        }
        //step3：修改密码
        int i = userMapper.updataPasswordByUsername(username,MD5Utils.getMD5Code(password));
        if (i<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"密码修改失败");
        }

        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,"密码修改成功");
    }

    @Override
    public ServerResponse reset_password(String username,String password, String newpassword) {


        //step1:参数的非空校验
        if (username==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        if (password==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        if (newpassword==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        //step2：查询用户名是否存在
        int i = userMapper.usernameIsexist(username);
        if (i<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"用户名不存在");
        }
        //step3:根据用户名和密码查询用户信息
        User user = userMapper.selectUserByUsernameAndPassowrd(username, MD5Utils.getMD5Code(password));
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"旧密码错误");
        }
        //step4:修改密码
        user.setPassword(MD5Utils.getMD5Code(newpassword));
        int result = userMapper.updateByPrimaryKey(user);
        if (result<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"密码修改失败");
        }

        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,"密码修改成功");
    }

    @Override
    public ServerResponse updata_information(User user) {

        //step1:参数的非空校验
        if (user==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        //step2：修改信息
        int result = userMapper.updataUserByActive(user);

        if (result<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"信息更新失败");
        }
        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,"更新成功");
    }
    
    

    
    
    

}
