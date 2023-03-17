package com.cmzn.authcontrol.entity.bo;

import lombok.Data;

import java.util.List;

@Data
public class RoleBo {
    /**
     * id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String des;
    /**
     * 角色标识
     */
    private String mark;
    /**
     * 角色状态,1正常、-1停用
     */
    private Integer status;

    private List<AuthBo> auths;
}
