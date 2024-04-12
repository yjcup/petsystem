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
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的订单Controller
 * 
 * @author ruoyi
 * @date 2024-04-09
 */
@Controller
@RequestMapping("/system/order")
public class SysOrderController extends BaseController
{
    private String prefix = "system/order";
    private String manager = "system/order/manager";


    @Autowired
    private ISysOrderService sysOrderService;

    @RequiresPermissions("system:order:view")
    @GetMapping()
    public String order()
    {

        SysRole sysRole = ShiroUtils.getSysUser().getRoles().get(0);
        if(sysRole.getRoleKey().equals("manager")){
            return manager+"/order";
        }
        return prefix + "/order";
    }

    /**
     * 查询我的订单列表
     */
    @RequiresPermissions("system:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysOrder sysOrder)
    {
        startPage();
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出我的订单列表
     */
    @RequiresPermissions("system:order:export")
    @Log(title = "我的订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysOrder sysOrder)
    {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        return util.exportExcel(list, "我的订单数据");
    }

    /**
     * 新增我的订单
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
     * 新增保存我的订单
     */
    @RequiresPermissions("system:order:add")
    @Log(title = "我的订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysOrder sysOrder)
    {
        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改我的订单
     */
    @RequiresPermissions("system:order:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysOrder sysOrder = sysOrderService.selectSysOrderById(id);
        mmap.put("sysOrder", sysOrder);
        SysRole sysRole = ShiroUtils.getSysUser().getRoles().get(0);

        if(sysRole.getRoleKey().equals("manager")){
            return manager+"/edit";
        }
        return prefix + "/edit";
    }

    /**
     * 修改保存我的订单
     */
    @RequiresPermissions("system:order:edit")
    @Log(title = "我的订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysOrder sysOrder)
    {
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 删除我的订单
     */
    @RequiresPermissions("system:order:remove")
    @Log(title = "我的订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysOrderService.deleteSysOrderByIds(ids));
    }
}
