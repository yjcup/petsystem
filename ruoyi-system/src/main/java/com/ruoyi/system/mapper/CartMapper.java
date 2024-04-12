package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Cart;

import java.util.List;

/**
 * 购物车管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
public interface CartMapper 
{
    /**
     * 查询购物车管理
     * 
     * @param id 购物车管理主键
     * @return 购物车管理
     */
    public Cart selectCartById(Long id);

    /**
     * 查询购物车管理列表
     * 
     * @param cart 购物车管理
     * @return 购物车管理集合
     */
    public List<Cart> selectCartList(Cart cart);

    /**
     * 新增购物车管理
     * 
     * @param cart 购物车管理
     * @return 结果
     */
    public int insertCart(Cart cart);

    /**
     * 修改购物车管理
     * 
     * @param cart 购物车管理
     * @return 结果
     */
    public int updateCart(Cart cart);

    /**
     * 删除购物车管理
     * 
     * @param id 购物车管理主键
     * @return 结果
     */
    public int deleteCartById(Long id);

    /**
     * 批量删除购物车管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCartByIds(String[] ids);
}
