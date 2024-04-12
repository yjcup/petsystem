package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.PetApply;

import java.util.List;

/**
 * 我的领养Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
public interface PetApplyMapper 
{
    /**
     * 查询我的领养
     * 
     * @param id 我的领养主键
     * @return 我的领养
     */
    public PetApply selectPetApplyById(Long id);

    /**
     * 查询我的领养列表
     * 
     * @param petApply 我的领养
     * @return 我的领养集合
     */
    public List<PetApply> selectPetApplyList(PetApply petApply);

    /**
     * 新增我的领养
     * 
     * @param petApply 我的领养
     * @return 结果
     */
    public int insertPetApply(PetApply petApply);

    /**
     * 修改我的领养
     * 
     * @param petApply 我的领养
     * @return 结果
     */
    public int updatePetApply(PetApply petApply);

    /**
     * 删除我的领养
     * 
     * @param id 我的领养主键
     * @return 结果
     */
    public int deletePetApplyById(Long id);

    /**
     * 批量删除我的领养
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetApplyByIds(String[] ids);
}
