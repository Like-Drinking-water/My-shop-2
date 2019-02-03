package com.huanleichen.my.shop.domain;

import com.huanleichen.my.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class ContentCategory extends BaseTreeEntity<ContentCategory> {

    @Length(min = 2, max = 20, message = "分类名称的长度必须在 2 - 20 位之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

}