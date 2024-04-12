package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysOrder;

import java.util.List;

/**
 * 我的订单Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-09
 */
public interface SysOrderMapper 
{
    /**
     * 查询我的订单
     * 
     * @param id 我的订单主键
     * @return 我的订单
     */
    public SysOrder selectSysOrderById(Long id);

    /**
     * 查询我的订单列表
     * 
     * @param sysOrder 我的订单
     * @return 我的订单集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 新增我的订单
     * 
     * @param sysOrder 我的订单
     * @return 结果
     */
    public int insertSysOrder(SysOrder sysOrder);

    /**
     * 修改我的订单
     * 
     * @param sysOrder 我的订单
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

    /**
     * 删除我的订单
     * 
     * @param id 我的订单主键
     * @return 结果
     */
    public int deleteSysOrderById(Long id);

    /**
     * 批量删除我的订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysOrderByIds(String[] ids);
}
