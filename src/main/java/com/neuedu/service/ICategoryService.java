package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Category;

import javax.servlet.http.HttpSession;

public interface ICategoryService {


    /**
     * 获取品类子节点
     * */
    public ServerResponse get_category(Integer categoryId);


    /**
     * 添加品类子节点
     * */
    public ServerResponse add_category(Category category);

    /**
     * 修改品类
     * */
    public ServerResponse set_category(Category category);


    /**
     * 递归调用子类别
     **/
    public ServerResponse get_deep_category(Integer categoryId);
}
