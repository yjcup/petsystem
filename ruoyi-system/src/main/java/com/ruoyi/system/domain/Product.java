package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 宠物商品管理对象 product
 * 
 * @author ruoyi
 * @date 2024-02-29
 */
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String inro;

    /** 详细描述 */
    @Excel(name = "详细描述")
    private String des;

    /** 封面 */
    @Excel(name = "封面")
    private String img;

    /** 商品轮播图 */
    @Excel(name = "商品轮播图")
    private String imglist;

    /** 库存 */
    @Excel(name = "库存")
    private Long inventory;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setInro(String inro) 
    {
        this.inro = inro;
    }

    public String getInro() 
    {
        return inro;
    }
    public void setDes(String des) 
    {
        this.des = des;
    }

    public String getDes() 
    {
        return des;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setImglist(String imglist) 
    {
        this.imglist = imglist;
    }

    public String getImglist() 
    {
        return imglist;
    }
    public void setInventory(Long inventory) 
    {
        this.inventory = inventory;
    }

    public Long getInventory() 
    {
        return inventory;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("inro", getInro())
            .append("des", getDes())
            .append("img", getImg())
            .append("imglist", getImglist())
            .append("inventory", getInventory())
            .append("price", getPrice())
            .append("createTime", getCreateTime())
            .toString();
    }
}
