package com.cmzn.authcontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import  com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.entity.domain.RoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
public interface RoleService extends IService<RoleEntity> {

    PageResult<RoleEntity> queryPage(Map<String, Object> params);

    List<RoleEntity> getAvailableRolesByUserId(Long userId);
}

