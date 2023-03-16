package com.cmzn.authcontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import  com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.entity.domain.RoleMtmAuthEntity;

import java.util.Map;

/**
 * 角色与权限中间表(多对多)
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
public interface RoleMtmAuthService extends IService<RoleMtmAuthEntity> {

    PageResult<RoleMtmAuthEntity> queryPage(Map<String, Object> params);
}

