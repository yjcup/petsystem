package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 我的预约对象 pet_res
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
public class PetRes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 宠物昵称 */
    @Excel(name = "宠物昵称")
    private String petName;

    /** 宠物类别 */
    @Excel(name = "宠物类别")
    private String petType;

    /** 预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date resDate;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 备注消息 */
    @Excel(name = "备注消息")
    private String message;

    /** 预约状态 */
    @Excel(name = "预约状态")
    private String status;

    /** 用户id */
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPetName(String petName) 
    {
        this.petName = petName;
    }

    public String getPetName() 
    {
        return petName;
    }
    public void setPetType(String petType) 
    {
        this.petType = petType;
    }

    public String getPetType() 
    {
        return petType;
    }
    public void setResDate(Date resDate) 
    {
        this.resDate = resDate;
    }

    public Date getResDate() 
    {
        return resDate;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("petName", getPetName())
            .append("petType", getPetType())
            .append("resDate", getResDate())
            .append("name", getName())
            .append("contact", getContact())
            .append("message", getMessage())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
