package com.cmzn.authcontrol.service.impl;

import com.cmzn.authcontrol.dao.RoleDao;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.Query;

import com.cmzn.authcontrol.entity.domain.RoleEntity;
import com.cmzn.authcontrol.service.RoleService;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Override
    public PageResult<RoleEntity> queryPage(Map<String, Object> params) {
        IPage<RoleEntity> page = this.page(
                new Query<RoleEntity>().getPage(params),
                new QueryWrapper<RoleEntity>()
        );

        return new PageResult(page);
    }

}