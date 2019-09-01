package com.neuedu.controller.portal;


import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {


    @Autowired
    IProductService iProductService;

    @RequestMapping(value = "/detail.do")
    public ServerResponse detail(Integer productId){



        return iProductService.detail_portal(productId);
    }
}
