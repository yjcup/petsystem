package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Pet;
import com.ruoyi.system.mapper.PetMapper;
import com.ruoyi.system.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 宠物管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-29
 */
@Service
public class PetServiceImpl implements IPetService 
{
    @Autowired
    private PetMapper petMapper;

    /**
     * 查询宠物管理
     * 
     * @param id 宠物管理主键
     * @return 宠物管理
     */
    @Override
    public Pet selectPetById(Long id)
    {
        return petMapper.selectPetById(id);
    }

    /**
     * 查询宠物管理列表
     * 
     * @param pet 宠物管理
     * @return 宠物管理
     */
    @Override
    public List<Pet> selectPetList(Pet pet)
    {
        return petMapper.selectPetList(pet);
    }

    /**
     * 新增宠物管理
     * 
     * @param pet 宠物管理
     * @return 结果
     */
    @Override
    public int insertPet(Pet pet)
    {
        pet.setCreateTime(DateUtils.getNowDate());
        return petMapper.insertPet(pet);
    }

    /**
     * 修改宠物管理
     * 
     * @param pet 宠物管理
     * @return 结果
     */
    @Override
    public int updatePet(Pet pet)
    {
        return petMapper.updatePet(pet);
    }

    /**
     * 批量删除宠物管理
     * 
     * @param ids 需要删除的宠物管理主键
     * @return 结果
     */
    @Override
    public int deletePetByIds(String ids)
    {
        return petMapper.deletePetByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除宠物管理信息
     * 
     * @param id 宠物管理主键
     * @return 结果
     */
    @Override
    public int deletePetById(Long id)
    {
        return petMapper.deletePetById(id);
    }
}
