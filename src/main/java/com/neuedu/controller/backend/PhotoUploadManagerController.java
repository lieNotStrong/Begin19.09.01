package com.neuedu.controller.backend;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/manager/product")
public class PhotoUploadManagerController {

    @Autowired
    IProductService iProductService;

    @GetMapping(value = "/upload" )
    public String upload(){

        return "upload";
    }

    @ResponseBody
    @PostMapping(value = "/upload")
    public ServerResponse upload(@RequestParam("uploadfile") MultipartFile file){


        String path = "E:\\upload";

        return iProductService.upload(file,path);
    }



}
