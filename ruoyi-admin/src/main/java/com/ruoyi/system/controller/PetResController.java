package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.ShiroUtils;
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
import com.ruoyi.system.domain.PetRes;
import com.ruoyi.system.service.IPetResService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的预约Controller
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
@Controller
@RequestMapping("/system/res")
public class PetResController extends BaseController
{
    private String prefix = "system/res";

    private String manager = "system/res/manager";


    @Autowired
    private IPetResService petResService;

    @RequiresPermissions("system:res:view")
    @GetMapping()
    public String res()
    {
        SysRole sysRole = ShiroUtils.getSysUser().getRoles().get(0);
        if(sysRole.getRoleKey().equals("manager")){
            return manager+"/res";
        }
        return prefix + "/res";
    }

    /**
     * 查询我的预约列表
     */
    @RequiresPermissions("system:res:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PetRes petRes)
    {
        startPage();
        List<PetRes> list = petResService.selectPetResList(petRes);
        return getDataTable(list);
    }

    /**
     * 导出我的预约列表
     */
    @RequiresPermissions("system:res:export")
    @Log(title = "我的预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PetRes petRes)
    {
        List<PetRes> list = petResService.selectPetResList(petRes);
        ExcelUtil<PetRes> util = new ExcelUtil<PetRes>(PetRes.class);
        return util.exportExcel(list, "我的预约数据");
    }

    /**
     * 新增我的预约
     */
    @GetMapping("/add")
    public String add()
    {
        SysRole sysRole = ShiroUtils.getSysUser().getRoles().get(0);
        if(sysRole.getRoleKey().equals("manager")){
            return manager+"/add";
        }
        return prefix + "/add";
    }

    /**
     * 新增保存我的预约
     */
    @RequiresPermissions("system:res:add")
    @Log(title = "我的预约", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PetRes petRes)
    {
        return toAjax(petResService.insertPetRes(petRes));
    }

    /**
     * 修改我的预约
     */
    @RequiresPermissions("system:res:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PetRes petRes = petResService.selectPetResById(id);
        SysRole sysRole = ShiroUtils.getSysUser().getRoles().get(0);
        mmap.put("petRes", petRes);

        if(sysRole.getRoleKey().equals("manager")){
            return manager+"/edit";
        }
        return prefix + "/edit";
    }

    /**
     * 修改保存我的预约
     */
    @RequiresPermissions("system:res:edit")
    @Log(title = "我的预约", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PetRes petRes)
    {
        return toAjax(petResService.updatePetRes(petRes));
    }

    /**
     * 删除我的预约
     */
    @RequiresPermissions("system:res:remove")
    @Log(title = "我的预约", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(petResService.deletePetResByIds(ids));
    }
}
