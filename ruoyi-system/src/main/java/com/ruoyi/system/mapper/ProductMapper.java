package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Product;

import java.util.List;

/**
 * 宠物商品管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-29
 */
public interface ProductMapper 
{
    /**
     * 查询宠物商品管理
     * 
     * @param id 宠物商品管理主键
     * @return 宠物商品管理
     */
    public Product selectProductById(Long id);

    /**
     * 查询宠物商品管理列表
     * 
     * @param product 宠物商品管理
     * @return 宠物商品管理集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增宠物商品管理
     * 
     * @param product 宠物商品管理
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改宠物商品管理
     * 
     * @param product 宠物商品管理
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 删除宠物商品管理
     * 
     * @param id 宠物商品管理主键
     * @return 结果
     */
    public int deleteProductById(Long id);

    /**
     * 批量删除宠物商品管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByIds(String[] ids);
}
