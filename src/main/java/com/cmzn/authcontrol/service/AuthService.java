package com.cmzn.authcontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import  com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.entity.bo.AuthBo;
import com.cmzn.authcontrol.entity.domain.AuthEntity;

import java.util.List;
import java.util.Map;

/**
 * 权限
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
public interface AuthService extends IService<AuthEntity> {

    PageResult<AuthEntity> queryPage(Map<String, Object> params);

    List<AuthBo> getAuths();
}

