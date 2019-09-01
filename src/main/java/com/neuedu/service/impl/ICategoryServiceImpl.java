package com.neuedu.service.impl;

import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class ICategoryServiceImpl implements ICategoryService{

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse get_category(Integer categoryId) {

        //step1：参数的非空校验
        if (categoryId==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        List<Category> categoryList = categoryMapper.selectChildByCategoryid(categoryId);
        if (categoryList==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"此商品没有子节点");
        }


        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,categoryList,"success");
    }

    @Override
    public ServerResponse add_category(Category category) {

        //step1:参数的非空校验
        if (category==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        if (category.getParentId()==null){
            category.setParentId(0);
        }

        int result = categoryMapper.insert(category);
        if (result<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"添加子节点失败");
        }

        return ServerResponse.serverResponseBySuccess();
    }

    @Override
    public ServerResponse set_category(Category category) {

        //step1：参数的非空判断
        if (category==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        Integer id = category.getId();
        //step2：判断参数id是否存在
        if (id==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"id参数不能为空");
        }
//        //step3：判断数据库中id是否存在
//        Category category1  = categoryMapper.selectByPrimaryKey(id);
//        if (category1==null){
//            return ServerResponse.serverResponseByError(StatusCode.ERROR,"id在数据库中不存在");
//        }
        //step4：修改类别
        int result = categoryMapper.updateByPrimaryKey(category);
        if (result<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"类别修改失败");
        }


        return ServerResponse.serverResponseBySuccess();
    }

    @Override
    public ServerResponse get_deep_category(Integer categoryId) {

        if (categoryId==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }

        Set<Category> categories = new HashSet<>();
        Set<Category> categorySet = selectDeepCategort(categoryId, categories);
        Set<Integer> categorysId = new HashSet<>();
        Iterator<Category> iterator = categorySet.iterator();

        while (iterator.hasNext()){
            Category next = iterator.next();
            categorysId.add(next.getId());
        }



        return ServerResponse.serverResponseBySuccess(StatusCode.SUCCESS,categorysId,"返回成功");
    }


    //递归调用
    private Set<Category> selectDeepCategort(Integer categoryId,Set<Category> categorySet){

        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category!=null){
           categorySet.add(category);
        }
        List<Category> categoryList = categoryMapper.selectChildByCategoryid(categoryId);
        if (categoryList!=null&&categoryList.size()>0){
            for (Category category1:categoryList){
                selectDeepCategort(category1.getId(),categorySet);
            }
        }
        return categorySet;
    }

}
