package com.ruoyi.system.service;

import com.ruoyi.system.domain.PetRes;

import java.util.List;

/**
 * 我的预约Service接口
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
public interface IPetResService 
{
    /**
     * 查询我的预约
     * 
     * @param id 我的预约主键
     * @return 我的预约
     */
    public PetRes selectPetResById(Long id);

    /**
     * 查询我的预约列表
     * 
     * @param petRes 我的预约
     * @return 我的预约集合
     */
    public List<PetRes> selectPetResList(PetRes petRes);

    /**
     * 新增我的预约
     * 
     * @param petRes 我的预约
     * @return 结果
     */
    public int insertPetRes(PetRes petRes);

    /**
     * 修改我的预约
     * 
     * @param petRes 我的预约
     * @return 结果
     */
    public int updatePetRes(PetRes petRes);

    /**
     * 批量删除我的预约
     * 
     * @param ids 需要删除的我的预约主键集合
     * @return 结果
     */
    public int deletePetResByIds(String ids);

    /**
     * 删除我的预约信息
     * 
     * @param id 我的预约主键
     * @return 结果
     */
    public int deletePetResById(Long id);
}
