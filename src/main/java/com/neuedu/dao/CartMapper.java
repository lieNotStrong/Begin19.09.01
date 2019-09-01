package com.neuedu.dao;

import com.neuedu.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    int insert(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    Cart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    List<Cart> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_cart
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Cart record);

    Cart selectByUserIdAndProductId(@Param("userId") Integer userId,
                                    @Param("productId") Integer productId);

    /**
     * 根据userId查询此用户的List<Cart>
     * */
    List<Cart> selectByUserId(Integer userId);

    int selectIsChecked(Integer userId);

    int selectAllQuantityByUserId(Integer userId);

    int deleteProductByUserIdAndProductIdList(@Param("userId") Integer userId,
                                              @Param("productIdList") List<Integer> productIdList);

    int updateCheckedStatusByUserIdAndProductId(@Param("userId") Integer userId,
                                                @Param("productId") Integer productId,
                                                @Param("checked") Integer checked);


}