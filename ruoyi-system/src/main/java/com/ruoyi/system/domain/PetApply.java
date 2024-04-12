package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 我的领养对象 pet_apply
 * 
 * @author ruoyi
 * @date 2024-04-07
 */
public class PetApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 宠物id */
    @Excel(name = "宠物id")
    private Long petId;

    /** 宠物原主人id */
    @Excel(name = "宠物原主人id")
    private Long fromId;

    /** 领养人id */
    @Excel(name = "领养人id")
    private Long toId;

    /** 领养人姓名 */
    @Excel(name = "领养人姓名")
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 备注消息 */
    @Excel(name = "备注消息")
    private String message;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPetId(Long petId) 
    {
        this.petId = petId;
    }

    public Long getPetId() 
    {
        return petId;
    }
    public void setFromId(Long fromId) 
    {
        this.fromId = fromId;
    }

    public Long getFromId() 
    {
        return fromId;
    }
    public void setToId(Long toId) 
    {
        this.toId = toId;
    }

    public Long getToId() 
    {
        return toId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("petId", getPetId())
            .append("fromId", getFromId())
            .append("toId", getToId())
            .append("name", getName())
            .append("contact", getContact())
            .append("message", getMessage())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
