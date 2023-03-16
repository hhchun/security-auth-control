package com.cmzn.authcontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import  com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.entity.domain.UserMtmRoleEntity;

import java.util.Map;

/**
 * 用户与角色中间表(多对多)
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
public interface UserMtmRoleService extends IService<UserMtmRoleEntity> {

    PageResult<UserMtmRoleEntity> queryPage(Map<String, Object> params);
}

