package com.ruoyi.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.*;
import com.ruoyi.system.domain.Pet;
import com.ruoyi.system.domain.PetRes;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.impl.PetResServiceImpl;
import com.ruoyi.system.service.impl.PetServiceImpl;
import com.ruoyi.system.service.impl.SysOrderServiceImpl;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin")
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private SysPasswordService passwordService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        Boolean footer = Convert.toBool(configService.selectConfigByKey("sys.index.footer"), true);
        Boolean tagsView = Convert.toBool(configService.selectConfigByKey("sys.index.tagsView"), true);
        mmap.put("footer", footer);
        mmap.put("tagsView", tagsView);
        mmap.put("mainClass", contentMainClass(footer, tagsView));
        mmap.put("copyrightYear", RuoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", RuoYiConfig.isDemoEnabled());
        mmap.put("isDefaultModifyPwd", initPasswordIsModify(user.getPwdUpdateDate()));
        mmap.put("isPasswordExpired", passwordIsExpiration(user.getPwdUpdateDate()));
        mmap.put("isMobile", ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")));

        // 菜单导航显示风格
        String menuStyle = configService.selectConfigByKey("sys.index.menuStyle");
        // 移动端，默认使左侧导航菜单，否则取默认配置
        String indexStyle = ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")) ? "index" : menuStyle;

        // 优先Cookie配置导航菜单
        Cookie[] cookies = ServletUtils.getRequest().getCookies();
        for (Cookie cookie : cookies)
        {
            if (StringUtils.isNotEmpty(cookie.getName()) && "nav-style".equalsIgnoreCase(cookie.getName()))
            {
                indexStyle = cookie.getValue();
                break;
            }
        }
        String webIndex = "topnav".equalsIgnoreCase(indexStyle) ? "index-topnav" : "index";
//        return webIndex;
        return "index-topnav";
    }

    // 锁定屏幕
    @GetMapping("/lockscreen")
    public String lockscreen(ModelMap mmap)
    {
        mmap.put("user", getSysUser());
        ServletUtils.getSession().setAttribute(ShiroConstants.LOCK_SCREEN, true);
        return "lock";
    }

    // 解锁屏幕
    @PostMapping("/unlockscreen")
    @ResponseBody
    public AjaxResult unlockscreen(String password)
    {
        SysUser user = getSysUser();
        if (StringUtils.isNull(user))
        {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        if (passwordService.matches(user, password))
        {
            ServletUtils.getSession().removeAttribute(ShiroConstants.LOCK_SCREEN);
            return AjaxResult.success();
        }
        return AjaxResult.error("密码不正确，请重新输入。");
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin()
    {
        return "skin";
    }

    // 切换菜单
    @GetMapping("/system/menuStyle/{style}")
    public void menuStyle(@PathVariable String style, HttpServletResponse response)
    {
        CookieUtils.setCookie(response, "nav-style", style);
    }


    @Autowired
    private SysOrderServiceImpl sysOrderService;
    @Autowired
    private SysUserServiceImpl sysUserService;

    @Autowired
    private PetServiceImpl petService;
    @Autowired
    private PetResServiceImpl resService;

    @Autowired
    private SysUserRoleMapper  sysUserRoleMapper;


    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", RuoYiConfig.getVersion());

        //订单相关的表
        //用户数 订单数 宠物数 预约数  订单变化
        int orderssize = sysOrderService.selectSysOrderList(new SysOrder()).size();
        int petsize = petService.selectPetList(new Pet()).size();
        int ressize = resService.selectPetResList(new PetRes()).size();
        int usersize = sysUserRoleMapper.countUserRoleByRoleId(100L);
        List<SysOrder> sysOrders = sysOrderService.selectSysOrderList(new SysOrder());
        Map<String, Integer> stringIntegerMap = countOrdersByDay(sysOrders);
        System.out.println(stringIntegerMap);
        mmap.put("data",mapToJsonArray(stringIntegerMap));
        mmap.put("ordersize",orderssize);
        mmap.put("petsize",petsize);
        mmap.put("ressize",ressize);
        mmap.put("usersize",usersize);

        SysRole sysRole = ShiroUtils.getSysUser().getRoles().get(0);
        if (sysRole.getRoleKey().equals("manager")){
            return "main_v1";
        }
        return "/system/pet/pet";
    }


    public static  String mapToJsonArray(Map<String, Integer> map) {
        StringBuilder jsonArray = new StringBuilder("[");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            jsonArray.append("[gd(" + entry.getKey() + "), " + entry.getValue()+100 + "]");
            jsonArray.append(",");
        }
        jsonArray.deleteCharAt(jsonArray.length() - 1); // 删除最后一个逗号
        jsonArray.append("]");
        System.out.println(jsonArray.toString());
        return jsonArray.toString();
    }

    public static Map<String, Integer> countOrdersByDay(List<SysOrder> orders) {
        Map<String, Integer> orderCounts = new HashMap<>();

        for (SysOrder order : orders) {
            // 获取订单的创建时间
            Date createTime = order.getCreateTime();
            // 格式化创建时间为日期字符串
            String day = formatDateToDay(createTime);
            // 将订单添加到对应日期的订单量中，如果该日期的订单量已存在，则将其数量加1，否则初始化为1
            orderCounts.put(day, orderCounts.getOrDefault(day, 0) + 1);
        }

        return orderCounts;
    }

    // 将日期格式化为"yyyy-MM-dd"的字符串
    private static String formatDateToDay(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy, MM, dd");
        return formatter.format(date);
    }

    // content-main class
    public String contentMainClass(Boolean footer, Boolean tagsView)
    {
        if (!footer && !tagsView)
        {
            return "tagsview-footer-hide";
        }
        else if (!footer)
        {
            return "footer-hide";
        }
        else if (!tagsView)
        {
            return "tagsview-hide";
        }
        return StringUtils.EMPTY;
    }

    // 检查初始密码是否提醒修改
    public boolean initPasswordIsModify(Date pwdUpdateDate)
    {
        Integer initPasswordModify = Convert.toInt(configService.selectConfigByKey("sys.account.initPasswordModify"));
        return initPasswordModify != null && initPasswordModify == 1 && pwdUpdateDate == null;
    }

    // 检查密码是否过期
    public boolean passwordIsExpiration(Date pwdUpdateDate)
    {
        Integer passwordValidateDays = Convert.toInt(configService.selectConfigByKey("sys.account.passwordValidateDays"));
        if (passwordValidateDays != null && passwordValidateDays > 0)
        {
            if (StringUtils.isNull(pwdUpdateDate))
            {
                // 如果从未修改过初始密码，直接提醒过期
                return true;
            }
            Date nowDate = DateUtils.getNowDate();
            return DateUtils.differentDaysByMillisecond(nowDate, pwdUpdateDate) > passwordValidateDays;
        }
        return false;
    }
}
