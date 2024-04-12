package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.system.domain.Pet;
import com.ruoyi.system.service.impl.PetServiceImpl;
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
import com.ruoyi.system.domain.PetApply;
import com.ruoyi.system.service.IPetApplyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的领养Controller
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
@Controller
@RequestMapping("/system/apply/from")
public class PetApplyControllerFrom extends BaseController
{
    private String prefix = "system/apply/from";

    @Autowired
    private IPetApplyService petApplyService;

    @RequiresPermissions("system:apply:view")
    @GetMapping()
    public String apply()
    {
        return prefix + "/apply";
    }

    /**
     * 查询我的领养列表
     */
    @RequiresPermissions("system:apply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PetApply petApply)
    {
        startPage();
        petApply.setFromId(ShiroUtils.getUserId());
        List<PetApply> list = petApplyService.selectPetApplyList(petApply);
        return getDataTable(list);
    }

    /**
     * 导出我的领养列表
     */
    @RequiresPermissions("system:apply:export")
    @Log(title = "我的领养", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PetApply petApply)
    {
        List<PetApply> list = petApplyService.selectPetApplyList(petApply);
        ExcelUtil<PetApply> util = new ExcelUtil<PetApply>(PetApply.class);
        return util.exportExcel(list, "我的领养数据");
    }

    /**
     * 新增我的领养
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存我的领养
     */
    @RequiresPermissions("system:apply:add")
    @Log(title = "我的领养", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PetApply petApply)
    {
        return toAjax(petApplyService.insertPetApply(petApply));
    }

    /**
     * 修改我的领养
     */
    @RequiresPermissions("system:apply:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PetApply petApply = petApplyService.selectPetApplyById(id);
        mmap.put("petApply", petApply);
        return prefix + "/edit";
    }

    /**
     * 修改保存我的领养
     */

    @Autowired
    private PetServiceImpl petService;

    @RequiresPermissions("system:apply:edit")
    @Log(title = "我的领养", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PetApply petApply)
    {


        // 如何发现领养成功 则修改状态
        if(petApply.getStatus().equals("1")){
            Pet pet = petService.selectPetById(petApply.getPetId());
            pet.setStatus("1");
            petService.insertPet(pet);
        }
        return toAjax(petApplyService.updatePetApply(petApply));
    }

    /**
     * 删除我的领养
     */
    @RequiresPermissions("system:apply:remove")
    @Log(title = "我的领养", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(petApplyService.deletePetApplyByIds(ids));
    }
}
