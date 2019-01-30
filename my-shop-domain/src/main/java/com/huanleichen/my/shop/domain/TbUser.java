package com.huanleichen.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huanleichen.my.shop.commons.persistence.BaseEntity;
import com.huanleichen.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class TbUser extends BaseEntity {
    @Length(min = 6, max = 20, message = "用户名的长度必须介于 6 - 20 之间")
    private String username;
    @JsonIgnore
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE, message = "手机格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;
    private boolean remember;

}
