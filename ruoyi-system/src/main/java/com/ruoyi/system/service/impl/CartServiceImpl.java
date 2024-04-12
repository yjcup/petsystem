package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Cart;
import com.ruoyi.system.mapper.CartMapper;
import com.ruoyi.system.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
@Service
public class CartServiceImpl implements ICartService 
{
    @Autowired
    private CartMapper cartMapper;

    /**
     * 查询购物车管理
     * 
     * @param id 购物车管理主键
     * @return 购物车管理
     */
    @Override
    public Cart selectCartById(Long id)
    {
        return cartMapper.selectCartById(id);
    }

    /**
     * 查询购物车管理列表
     * 
     * @param cart 购物车管理
     * @return 购物车管理
     */
    @Override
    public List<Cart> selectCartList(Cart cart)
    {
        return cartMapper.selectCartList(cart);
    }

    /**
     * 新增购物车管理
     * 
     * @param cart 购物车管理
     * @return 结果
     */
    @Override
    public int insertCart(Cart cart)
    {
        cart.setCreateTime(DateUtils.getNowDate());
        return cartMapper.insertCart(cart);
    }

    /**
     * 修改购物车管理
     * 
     * @param cart 购物车管理
     * @return 结果
     */
    @Override
    public int updateCart(Cart cart)
    {
        return cartMapper.updateCart(cart);
    }

    /**
     * 批量删除购物车管理
     * 
     * @param ids 需要删除的购物车管理主键
     * @return 结果
     */
    @Override
    public int deleteCartByIds(String ids)
    {
        return cartMapper.deleteCartByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除购物车管理信息
     * 
     * @param id 购物车管理主键
     * @return 结果
     */
    @Override
    public int deleteCartById(Long id)
    {
        return cartMapper.deleteCartById(id);
    }
}
