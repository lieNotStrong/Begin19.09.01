package com.neuedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.neuedu.common.EnumClass;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import com.neuedu.utils.DateUtils;
import com.neuedu.vo.ProductDetailVO;
import com.neuedu.vo.ProductListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class IProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse addOrUpdate(Product product) {


        if (product.getCategoryId()==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"类别ID不能为空");
        }
        if (product.getStock()==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"商品库存不能为空");
        }

        /**
         * 设置主图，取子图的第一张
         * */
        String subImages = product.getSubImages();
            if (subImages!=null&&!subImages.equals("")){
            String[] subImagesArr = subImages.split(",");
            if (subImagesArr.length>0){
                product.setMainImage(subImagesArr[0]);
            }
        }

        if (product.getId()==null){
            product.setStatus(EnumClass.ProductEnum.PRODUCT_ONLINE.getProductTypeCode());
            int insert = productMapper.insert(product);
            if (insert>0){

                return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,"添加成功");
            }else {
                return ServerResponse.serverResponseByError(StatusCode.ERROR,"添加失败");
            }
        }else {
            int result = productMapper.updateByPrimaryKey(product);
            if (result>0){
                return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,"修改成功");
            }else {
                return ServerResponse.serverResponseByError(StatusCode.ERROR,"修改失败");
            }
        }
    }

    @Override
    public ServerResponse set_sale_status(Integer productId,Integer status) {

        if (productId==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        if (status==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
//        Product product = new Product();
//        product.setId(productId);
//        product.setStatus(status);
//        int i = productMapper.updateByPrimaryKey(product);
        int i = productMapper.updateStatusById(productId,status);
        if (i<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"修改状态失败");
        }
        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,"修改产品状态成功");
    }

    @Override
    public ServerResponse detail(Integer productId) {

        //step1：参数非空判断
        if (productId==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        //step2：查找商品信息
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"商品不存在");
        }

        //step3：将product转成productDetailVO
        ProductDetailVO productDetailVO = productToDetail(product);
        return ServerResponse.serverResponseBySuccess(productDetailVO);
    }



    @Value("${imageHost}")
    private String imageHost;
    //将product转为productDetailVO
    private ProductDetailVO productToDetail(Product product){

        ProductDetailVO productDetailVO = new ProductDetailVO();
        productDetailVO.setId(product.getId());
        productDetailVO.setCategoryId(product.getCategoryId());
        productDetailVO.setName(product.getName());
        productDetailVO.setSubtitle(product.getSubtitle());
        productDetailVO.setMainImage(product.getMainImage());
        productDetailVO.setSubImages(product.getSubImages());
        productDetailVO.setDetail(product.getDetail());
        productDetailVO.setPrice(product.getPrice());
        productDetailVO.setStock(product.getStock());
        productDetailVO.setStatus(product.getStatus());
        productDetailVO.setCreateTime(DateUtils.dateToStr(product.getCreateTime()));
        productDetailVO.setUpdateTime(DateUtils.dateToStr(product.getUpdateTime()));
        productDetailVO.setImageHost(imageHost);


        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());

        if (category!=null){
            productDetailVO.setParentCategoryId(category.getParentId());
        }else {
            productDetailVO.setParentCategoryId(0);
        }


        return productDetailVO;

    }

    @Override
    public ServerResponse list(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);

        List<Product> productList = productMapper.selectAll();

        List<ProductListVO> productListVOList = new ArrayList<>();
        for(Product product:productList){

            ProductListVO productListVO = productToListVO(product);
            productListVOList.add(productListVO);
        }

        PageInfo pageInfo = new PageInfo<ProductListVO>(productListVOList);



        return ServerResponse.serverResponseBySuccess(pageInfo);
    }




    private ProductListVO productToListVO(Product product){
        ProductListVO productListVO = new ProductListVO();

        productListVO.setSubtitle(product.getSubtitle());
        productListVO.setStatus(product.getStatus());
        productListVO.setPrice(product.getPrice());
        productListVO.setName(product.getName());
        productListVO.setMainImage(product.getMainImage());
        productListVO.setCategoryId(product.getCategoryId());
        productListVO.setId(product.getId());

        return productListVO;

    }
    @Override
    public ServerResponse search(Integer productId, String productName, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        if (productName!=null&&!productName.equals("")){
            productName="%"+productName+"%";
        }
        List<Product> productList = productMapper.selectByProductIdOrProductName(productId, productName);
        List<ProductListVO> productListVOList = Lists.newArrayList();
        for (Product product:productList){
            ProductListVO productListVO = productToListVO(product);
            productListVOList.add(productListVO);
        }
        PageInfo<ProductListVO> pageInfo = new PageInfo<>(productListVOList);

        return ServerResponse.serverResponseBySuccess(pageInfo);
    }




    @Override
    public ServerResponse upload(MultipartFile file, String path) {

//        step1:参数的非空判断
        if (file==null||file.getOriginalFilename().equals("")){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"文件必须上传");
        }
//        step2:给传上来的图片重新命名
//        1,获取文件名
        String originalFilename = file.getOriginalFilename();
//        2，截取文件扩展名
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
//        3，为文件生成新的名
        String newFileNameLack = UUID.randomUUID().toString();
        String newFileName = newFileNameLack+substring;
//        step3:创建存放文件的目录
        File file1 = new File(path);
        if (!file1.exists()){
            file1.setWritable(true);
            file1.mkdirs();
        }
        File file2 = new File(file1, newFileName);


        try {
            file.transferTo(file2);
            Map<String,String> map = new HashMap<String,String>();
            map.put("uri",newFileName);
            map.put("url",imageHost+"/"+newFileName);
            return ServerResponse.serverResponseBySuccess(map);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ServerResponse.serverResponseByError(StatusCode.ERROR,"上传失败");
    }

    @Override
    public ServerResponse detail_portal(Integer productId) {

        if (productId==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"没有此商品");
        }
        if (product.getStatus()==EnumClass.ProductEnum.PRODUCT_ONLINE.getProductTypeCode()){
            ProductDetailVO productDetailVO = productToDetail(product);
            return ServerResponse.serverResponseBySuccess(productDetailVO);
        }

        return ServerResponse.serverResponseByError("此商品已下架或删除");
    }


}
