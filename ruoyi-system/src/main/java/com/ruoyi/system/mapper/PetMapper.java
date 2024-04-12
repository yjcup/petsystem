package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Pet;

import java.util.List;

/**
 * 宠物管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-29
 */
public interface PetMapper 
{
    /**
     * 查询宠物管理
     * 
     * @param id 宠物管理主键
     * @return 宠物管理
     */
    public Pet selectPetById(Long id);

    /**
     * 查询宠物管理列表
     * 
     * @param pet 宠物管理
     * @return 宠物管理集合
     */
    public List<Pet> selectPetList(Pet pet);

    /**
     * 新增宠物管理
     * 
     * @param pet 宠物管理
     * @return 结果
     */
    public int insertPet(Pet pet);

    /**
     * 修改宠物管理
     * 
     * @param pet 宠物管理
     * @return 结果
     */
    public int updatePet(Pet pet);

    /**
     * 删除宠物管理
     * 
     * @param id 宠物管理主键
     * @return 结果
     */
    public int deletePetById(Long id);

    /**
     * 批量删除宠物管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetByIds(String[] ids);
}
