package com.huanleichen.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huanleichen.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;

@Data
public class ContentCategory extends BaseEntity {
    @NotBlank(message = "父目录不能为空")
    private Long parentId;
    @Length(min = 2, max = 20, message = "分类名称的长度必须在 2 - 20 位之间")
    private String name;
    private Integer status;
    @Digits(integer = 12, fraction = 0, message = "请输入整数")
    private Integer sortOrder;
    @JsonProperty(value = "isParent")
    private Boolean isParent;
}
