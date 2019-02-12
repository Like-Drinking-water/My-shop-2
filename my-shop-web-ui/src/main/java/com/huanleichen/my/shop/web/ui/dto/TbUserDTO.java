package com.huanleichen.my.shop.web.ui.dto;

import lombok.Data;

@Data
public class TbUserDTO {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String verification;
}
