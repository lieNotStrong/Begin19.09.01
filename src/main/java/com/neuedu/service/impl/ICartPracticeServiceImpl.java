package com.neuedu.service.impl;


import com.neuedu.common.EnumClass;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.dao.CartMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Cart;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICartService;
import com.neuedu.utils.BigDecimalUtils;
import com.neuedu.vo.CartProductVO;
import com.neuedu.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@Service
public class ICartPracticeServiceImpl implements ICartService {


    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;


    @Override
    public ServerResponse addOrUpdate(Integer userId, Integer productId, Integer quantity) {

        if (productId==null||quantity==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        //判断是添加操作还是修改操作，根据userId和productId查询购物车，如果查询到购物车中有此商品信息，则就是修改操作，如果没有则是添加操作
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart==null){
            Cart cart1 = new Cart();
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            cart1.setQuantity(quantity);
            cart1.setChecked(EnumClass.CartIsChecked.PRODUCT_CHECKED.getRoleCode());
            cartMapper.insert(cart1);
        }else {
            Cart cart1 = new Cart();
            cart1.setId(cart.getId());
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            cart1.setQuantity(quantity);
            cart1.setChecked(cart.getChecked());
            cartMapper.updateByPrimaryKey(cart1);
        }
        CartVO cartVOList = getCartVOList(userId);
        return ServerResponse.serverResponseBySuccess(cartVOList);
    }

    @Override
    public ServerResponse list(Integer userId) {

        CartVO cartVOList = getCartVOList(userId);

        return ServerResponse.serverResponseBySuccess(cartVOList);

    }

    @Override
    public ServerResponse update(Integer userId, Integer productId, Integer quantity) {
        return null;
    }

    @Override
    public ServerResponse delete_product(Integer userId, String productIds) {
        return null;
    }

    @Override
    public ServerResponse get_cart_product_count(Integer userId) {
        return null;
    }

    @Override
    public ServerResponse select(Integer userId, Integer productId, Integer checked) {
        return null;
    }
//    private Integer id;//购物车id 1 2
//    private Integer userId;//用户Id 1 2
//    private Integer productId;//商品id 1 2

//    private Integer quantity;//商品数量 1 2
//    private String productName;//商品名称 1 2
//    private String productSubtitle;//商品标题 1 2
//    private String productMainImage;//商品主图 1 2
//    private BigDecimal productPrice;//商品价格 1 2
//    private Integer productStatus;//商品状态 1 2
//    private BigDecimal productTotalPrice;//商品总价格
//    private Integer productStock;//商品库存 1 2
//    private Integer productChecked;//是否选中 1 2
//    private String limitQuantity;//商品库存描述信息 1 2

    private CartVO getCartVOList(Integer userId){

        CartVO cartVO = new CartVO();
        BigDecimal cartTotalPrice = new BigDecimal("0");
        List<Cart> cartList = cartMapper.selectByUserId(userId);
        List<CartProductVO> cartProductVOList = new ArrayList<>();

        if (cartList!=null&&cartList.size()>0){

            for (Cart cart:cartList){
                CartProductVO cartProductVO = new CartProductVO();
                cartProductVO.setId(cart.getId());
                cartProductVO.setUserId(userId);
                cartProductVO.setProductId(cart.getProductId());
                cartProductVO.setProductChecked(cart.getChecked());

                Product product = productMapper.selectByPrimaryKey(cart.getProductId());
                if (product!=null){
                    cartProductVO.setProductName(product.getName());
                    cartProductVO.setProductSubtitle(product.getSubtitle());
                    cartProductVO.setProductMainImage(product.getMainImage());
                    cartProductVO.setProductPrice(product.getPrice());
                    cartProductVO.setProductStatus(product.getStatus());
                    cartProductVO.setProductStock(product.getStock());
                    int stock = product.getStock();
                    int limitProductQuantity = 0;
                    if (stock>=cart.getQuantity()){
                        limitProductQuantity=cart.getQuantity();
                        cartProductVO.setLimitQuantity("LIMIT_NUM_SUCCESS");
                    }else {
                        limitProductQuantity=stock;
                        cartProductVO.setLimitQuantity("LIMIT_NUM_FAIL");
                    }
                    cartProductVO.setQuantity(limitProductQuantity);
                    cartProductVO.setProductTotalPrice(BigDecimalUtils.mul(Double.valueOf(limitProductQuantity),product.getPrice().doubleValue()));
                }
                cartProductVOList.add(cartProductVO);

                cartTotalPrice = BigDecimalUtils.add(cartTotalPrice.doubleValue(),cartProductVO.getProductTotalPrice().doubleValue());

            }
        }
        cartVO.setCartProductVOList(cartProductVOList);
        cartVO.setCattotalprice(cartTotalPrice);

        int checked = cartMapper.selectIsChecked(userId);
        if (checked>0){
            cartVO.setIsallChecked(false);
        }else {
            cartVO.setIsallChecked(true);
        }

        return cartVO;
    }

}
