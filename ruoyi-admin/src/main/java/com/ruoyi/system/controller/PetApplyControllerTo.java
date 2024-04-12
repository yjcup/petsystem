package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.PetApply;
import com.ruoyi.system.service.IPetApplyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我的领养Controller
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
@Controller
@RequestMapping("/system/apply/to")
public class PetApplyControllerTo extends BaseController
{
    private String prefix = "system/apply/to";

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
        petApply.setToId(ShiroUtils.getUserId());
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
    @RequiresPermissions("system:apply:edit")
    @Log(title = "我的领养", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PetApply petApply)
    {
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
