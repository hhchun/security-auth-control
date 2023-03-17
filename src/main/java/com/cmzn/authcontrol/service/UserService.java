package com.cmzn.authcontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmzn.authcontrol.auth.UserContext;
import  com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.entity.domain.UserEntity;
import com.cmzn.authcontrol.entity.dto.LoginDto;

import java.util.Map;

/**
 * 用户
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
public interface UserService extends IService<UserEntity> {

    PageResult<UserEntity> queryPage(Map<String, Object> params);

    String login(LoginDto loginDto);

    UserContext getUserContext(Long userId);
}

