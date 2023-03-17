package com.cmzn.authcontrol.entity.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthBo {
    /**
     * id
     */
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限描述
     */
    private String des;
    /**
     * 权限标识
     */
    private String mark;
    /**
     * 权限类型;api接口、menu菜单、button按钮
     */
    private String type;
    /**
     * 权限保护的目标对象
     */
    private String subject;
    /**
     * 权限状态,1正常、-1停用
     */
    private Integer status;

    private List<RoleBo> roles = new ArrayList<>();
}
