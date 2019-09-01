package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface IProductService {

    /**
     * 添加或修改商品
     * */
    public ServerResponse addOrUpdate(Product product);
    /**
     * 修改商品状态
     * */
    public ServerResponse set_sale_status(Integer productId,Integer status);
    /**
     * 获取商品详情
     * */
    public ServerResponse detail(Integer productId);
    /**
     * 商品的分页查询
     * */
    public ServerResponse list(Integer pageNum,Integer pageSize);
    /**
     * 搜索商品
     * */
    public ServerResponse search(Integer productId,String productName,Integer pageNum,Integer pageSize);
    /**
     * 图片上传
     * */
    public ServerResponse upload(MultipartFile file,String path);
    /**
     * 前台-商品详情
     * */
    public ServerResponse detail_portal(Integer productId);
}
