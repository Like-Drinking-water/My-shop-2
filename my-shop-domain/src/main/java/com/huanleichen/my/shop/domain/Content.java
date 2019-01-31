package com.huanleichen.my.shop.domain;

import com.huanleichen.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class Content extends BaseEntity {
    @NotNull(message = "父目录不能为空")
    private Long categoryId;
    @Length(min = 2, max = 20, message = "标题的长度必须在 2 - 20 位之间")
    private String title;
    @Length(min = 2, max = 20, message = "子标题的长度必须在 2 - 20 位之间")
    private String subTitle;
    @Length(min = 2, max = 20, message = "子标题的长度必须在 2 - 20 位之间")
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    @NotBlank(message = "内容不能为空")
    private String content;
}
