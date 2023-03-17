package com.cmzn.authcontrol.entity.bo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserBo {
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户状态,1正常、-1禁用
     */
    private Integer status;

    private List<RoleBo> roles;
}
