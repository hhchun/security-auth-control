package com.cmzn.authcontrol.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.Query;

import com.cmzn.authcontrol.mapper.UserMtmRoleDao;
import com.cmzn.authcontrol.entity.domain.UserMtmRoleEntity;
import com.cmzn.authcontrol.service.UserMtmRoleService;


@Service("userMtmRoleService")
public class UserMtmRoleServiceImpl extends ServiceImpl<UserMtmRoleDao, UserMtmRoleEntity> implements UserMtmRoleService {

    @Override
    public PageResult<UserMtmRoleEntity> queryPage(Map<String, Object> params) {
        IPage<UserMtmRoleEntity> page = this.page(
                new Query<UserMtmRoleEntity>().getPage(params),
                new QueryWrapper<UserMtmRoleEntity>()
        );

        return new PageResult(page);
    }

}