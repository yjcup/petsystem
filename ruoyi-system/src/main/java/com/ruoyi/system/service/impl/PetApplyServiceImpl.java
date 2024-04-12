package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.PetApply;
import com.ruoyi.system.mapper.PetApplyMapper;
import com.ruoyi.system.service.IPetApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我的领养Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
@Service
public class PetApplyServiceImpl implements IPetApplyService 
{
    @Autowired
    private PetApplyMapper petApplyMapper;

    /**
     * 查询我的领养
     * 
     * @param id 我的领养主键
     * @return 我的领养
     */
    @Override
    public PetApply selectPetApplyById(Long id)
    {
        return petApplyMapper.selectPetApplyById(id);
    }

    /**
     * 查询我的领养列表
     * 
     * @param petApply 我的领养
     * @return 我的领养
     */
    @Override
    public List<PetApply> selectPetApplyList(PetApply petApply)
    {
        return petApplyMapper.selectPetApplyList(petApply);
    }

    /**
     * 新增我的领养
     * 
     * @param petApply 我的领养
     * @return 结果
     */
    @Override
    public int insertPetApply(PetApply petApply)
    {
        petApply.setCreateTime(DateUtils.getNowDate());
        return petApplyMapper.insertPetApply(petApply);
    }

    /**
     * 修改我的领养
     * 
     * @param petApply 我的领养
     * @return 结果
     */
    @Override
    public int updatePetApply(PetApply petApply)
    {
        return petApplyMapper.updatePetApply(petApply);
    }

    /**
     * 批量删除我的领养
     * 
     * @param ids 需要删除的我的领养主键
     * @return 结果
     */
    @Override
    public int deletePetApplyByIds(String ids)
    {
        return petApplyMapper.deletePetApplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除我的领养信息
     * 
     * @param id 我的领养主键
     * @return 结果
     */
    @Override
    public int deletePetApplyById(Long id)
    {
        return petApplyMapper.deletePetApplyById(id);
    }
}
