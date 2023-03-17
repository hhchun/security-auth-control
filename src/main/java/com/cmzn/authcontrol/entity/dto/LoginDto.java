package com.cmzn.authcontrol.entity.dto;

import lombok.Data;

@Data
public class LoginDto {
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
