package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Pet;
import com.ruoyi.system.service.IPetService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物管理Controller
 * 
 * @author ruoyi
 * @date 2024-02-29
 */
@Controller
@RequestMapping("/system/pet")
public class PetController extends BaseController
{
    private String prefix = "system/pet";

    @Autowired
    private IPetService petService;

    @RequiresPermissions("system:pet:view")
    @GetMapping()
    public String pet()
    {
        return prefix + "/pet";
    }

    /**
     * 查询宠物管理列表
     */
    @RequiresPermissions("system:pet:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Pet pet)
    {
        startPage();
        List<Pet> list = petService.selectPetList(pet);
        return getDataTable(list);
    }

    /**
     * 导出宠物管理列表
     */
    @RequiresPermissions("system:pet:export")
    @Log(title = "宠物管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Pet pet)
    {
        List<Pet> list = petService.selectPetList(pet);
        ExcelUtil<Pet> util = new ExcelUtil<Pet>(Pet.class);
        return util.exportExcel(list, "宠物管理数据");
    }

    /**
     * 新增宠物管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存宠物管理
     */
    @RequiresPermissions("system:pet:add")
    @Log(title = "宠物管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Pet pet)
    {
        return toAjax(petService.insertPet(pet));
    }

    /**
     * 修改宠物管理
     */
    @RequiresPermissions("system:pet:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Pet pet = petService.selectPetById(id);
        mmap.put("pet", pet);
        return prefix + "/edit";
    }

    /**
     * 修改保存宠物管理
     */
    @RequiresPermissions("system:pet:edit")
    @Log(title = "宠物管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Pet pet)
    {
        return toAjax(petService.updatePet(pet));
    }

    /**
     * 删除宠物管理
     */
    @RequiresPermissions("system:pet:remove")
    @Log(title = "宠物管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(petService.deletePetByIds(ids));
    }
}
