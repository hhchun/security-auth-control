package com.cmzn.authcontrol.auth;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserContext {
    /**
     * id
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户状态,1正常、-1禁用
     */
    private Integer status;
    /**
     *
     */
    private String token;

    private List<Role> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "UserContext{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", nickname='" + nickname + '\'' +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }

    @Data
    public static class Role {
        private Long id;
        /**
         * 角色名称
         */
        private String name;
        /**
         * 角色标识
         */
        private String mark;
        /**
         * 角色状态,1正常、-1停用
         */
        private Integer status;

        private List<Auth> auths = new ArrayList<>();

        @Override
        public String toString() {
            return "Role{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", mark='" + mark + '\'' +
                    ", status=" + status +
                    ", auths=" + auths +
                    '}';
        }
    }

    @Data
    public static class Auth {
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

        @Override
        public String toString() {
            return "Auth{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", des='" + des + '\'' +
                    ", mark='" + mark + '\'' +
                    ", type='" + type + '\'' +
                    ", subject='" + subject + '\'' +
                    ", status=" + status +
                    '}';
        }
    }
}
