package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.PetRes;
import com.ruoyi.system.mapper.PetResMapper;
import com.ruoyi.system.service.IPetResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我的预约Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
@Service
public class PetResServiceImpl implements IPetResService 
{
    @Autowired
    private PetResMapper petResMapper;

    /**
     * 查询我的预约
     * 
     * @param id 我的预约主键
     * @return 我的预约
     */
    @Override
    public PetRes selectPetResById(Long id)
    {
        return petResMapper.selectPetResById(id);
    }

    /**
     * 查询我的预约列表
     * 
     * @param petRes 我的预约
     * @return 我的预约
     */
    @Override
    public List<PetRes> selectPetResList(PetRes petRes)
    {
        return petResMapper.selectPetResList(petRes);
    }

    /**
     * 新增我的预约
     * 
     * @param petRes 我的预约
     * @return 结果
     */
    @Override
    public int insertPetRes(PetRes petRes)
    {
        petRes.setCreateTime(DateUtils.getNowDate());
        return petResMapper.insertPetRes(petRes);
    }

    /**
     * 修改我的预约
     * 
     * @param petRes 我的预约
     * @return 结果
     */
    @Override
    public int updatePetRes(PetRes petRes)
    {
        return petResMapper.updatePetRes(petRes);
    }

    /**
     * 批量删除我的预约
     * 
     * @param ids 需要删除的我的预约主键
     * @return 结果
     */
    @Override
    public int deletePetResByIds(String ids)
    {
        return petResMapper.deletePetResByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除我的预约信息
     * 
     * @param id 我的预约主键
     * @return 结果
     */
    @Override
    public int deletePetResById(Long id)
    {
        return petResMapper.deletePetResById(id);
    }
}
