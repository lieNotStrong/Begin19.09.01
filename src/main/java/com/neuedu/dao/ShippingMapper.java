package com.neuedu.dao;

import com.neuedu.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Random;

public interface ShippingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbg.generated
     */
    int insert(Shipping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbg.generated
     */
    Shipping selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbg.generated
     */
    List<Shipping> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table neuedu_shipping
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Shipping record);

    int deleteByUserIdAndShippingId(@Param("userId") Integer userId,
                                    @Param("shippingId") Integer shippingId);

    int updateByUserIdAndShippingId(Shipping shipping);

    Shipping selectByUserIdAndShippingId(@Param("userId") Integer userId,
                                         @Param("shippingId") Integer shippingId);

    List<Shipping> selectByUserId(Integer userId);





}