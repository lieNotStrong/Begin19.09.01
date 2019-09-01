package com.neuedu.service.impl;

import com.neuedu.common.EnumClass;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StatusCode;
import com.neuedu.dao.CartMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Cart;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICartService;
import com.neuedu.service.ICategoryService;
import com.neuedu.utils.BigDecimalUtils;
import com.neuedu.vo.CartProductVO;
import com.neuedu.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ICartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;

    @Override
    public ServerResponse addOrUpdate(Integer userId, Integer productId,Integer quantity) {

        //step1:参数的非空判断
        if (productId==null||quantity==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        //step2：查看是添加商品还是更新商品
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart==null){
            //cart==null说明没有查询到此用户下的此productId，则说明此购物车中没有此商品，此时为添加商品
            Cart cart1 = new Cart();
            cart1.setProductId(productId);
            cart1.setUserId(userId);
            cart1.setQuantity(quantity);
            cart1.setChecked(EnumClass.CartIsChecked.PRODUCT_CHECKED.getRoleCode());
            cartMapper.insert(cart1);
        }else {
            Cart cart1 = new Cart();
            cart1.setProductId(productId);
            cart1.setUserId(userId);
            cart1.setQuantity(quantity);
            cart1.setChecked(cart.getChecked());
            cartMapper.updateByPrimaryKey(cart1);
        }
        CartVO cartVOList = getCartVOList(userId);


        return ServerResponse.serverResponseBySuccess(cartVOList);
    }



//    private Integer id;//购物车id 1
//    private Integer userId;//用户Id 1
//    private Integer productId;//商品id 1
//    private Integer quantity;//商品数量 1
//    private String productName;//商品名称 1
//    private String productSubtitle;//商品标题 1
//    private String productMainImage;//商品主图 1
//    private BigDecimal productPrice;//商品价格 1
//    private Integer productStatus;//商品状态 1
//    private BigDecimal productTotalPrice;//商品总价格
//    private Integer productStock;//商品库存 1
//    private Integer productChecked;//是否选中 1
//    private String limitQuantity;//商品库存描述信息

    private CartVO getCartVOList(Integer userId){

        CartVO cartVO = new CartVO();
        List<Cart> cartList = cartMapper.selectByUserId(userId);
        List<CartProductVO> cartProductVOList = new ArrayList<>();
        BigDecimal cartTotalPrice = new BigDecimal("0");
        if (cartList!=null){
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
                    int limitProductStock = 0;
                    if (stock>=cart.getQuantity()){
                        limitProductStock=cart.getQuantity();
                        cartProductVO.setLimitQuantity("LIMIT_NUM_SUCCESS");
                    }else {
                        limitProductStock=stock;
                        Cart cart1 = new Cart();
                        cart1.setId(cart.getId());
                        cart1.setUserId(userId);
                        cart1.setQuantity(limitProductStock);
                        cart1.setProductId(cart.getProductId());
                        cart1.setChecked(cart.getChecked());
                        cartMapper.updateByPrimaryKey(cart1);
                        cartProductVO.setLimitQuantity("LIMIT_NUM_FAIL");
                    }
                    cartProductVO.setQuantity(limitProductStock);
                    cartProductVO.setProductTotalPrice(BigDecimalUtils.mul(Double.valueOf(cart.getQuantity()),product.getPrice().doubleValue()));

                }

                //计算购物车的总价格
                if (cartProductVO.getProductChecked()== EnumClass.CartIsChecked.PRODUCT_CHECKED.getRoleCode()){
                    cartTotalPrice=BigDecimalUtils.add(cartTotalPrice.doubleValue(),cartProductVO.getProductTotalPrice().doubleValue());
                }
                cartProductVOList.add(cartProductVO);
            }
        }
        //往CartVO中添加cartProductVO
        cartVO.setCartProductVOList(cartProductVOList);
        cartVO.setCattotalprice(cartTotalPrice);
        int checkedAll = cartMapper.selectIsChecked(userId);
        if (checkedAll>0){
            cartVO.setIsallChecked(false);
        }else {
            cartVO.setIsallChecked(true);
        }

        return cartVO;

    }

    @Override
    public ServerResponse list(Integer userId) {

        CartVO cartVOList = getCartVOList(userId);

        return ServerResponse.serverResponseBySuccess(cartVOList);
    }

    @Override
    public ServerResponse update(Integer userId, Integer productId, Integer quantity) {

        if (productId==null||quantity==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart!=null){
            cart.setQuantity(quantity);
            cartMapper.updateByPrimaryKey(cart);
        }
        CartVO cartVOList = getCartVOList(userId);

        return ServerResponse.serverResponseBySuccess(cartVOList);
    }

    @Override
    public ServerResponse delete_product(Integer userId,String productIds) {

        if (productIds==null||productIds.equals("")){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"参数不能为空");
        }
        //step2:把String类型的productIds转换成Integer类型的集合
        List<Integer> integerProductIdList = new ArrayList<>();
        String[] split = productIds.split(",");
        if (split!=null&&split.length>0){
            for (String strProducts:split){
                Integer intProducts = Integer.parseInt(strProducts);
                integerProductIdList.add(intProducts);
            }
        }
        //step3：
        int result = cartMapper.deleteProductByUserIdAndProductIdList(userId, integerProductIdList);
        if (result<=0){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"删除失败");
        }
        CartVO cartVOList = getCartVOList(userId);
//        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
//        if (cart!=null){
//            cartMapper.deleteByPrimaryKey(cart.getId());
//        }
//        CartVO cartVOList = getCartVOList(userId);
        return ServerResponse.serverResponseBySuccess(cartVOList);
    }

    @Override
    public ServerResponse get_cart_product_count(Integer userId) {

        int allQuantity = cartMapper.selectAllQuantityByUserId(userId);


        return ServerResponse.serverResponseBySuccess(allQuantity);
    }

    @Override
    public ServerResponse select(Integer userId, Integer productId, Integer checked) {


        Integer result = cartMapper.updateCheckedStatusByUserIdAndProductId(userId, productId, checked);
        if (result==null){
            return ServerResponse.serverResponseByError(StatusCode.ERROR,"选中商品失败");
        }


        return ServerResponse.serverResponseBySuccess(getCartVOList(userId));
    }
}
