package com.cmzn.authcontrol.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cmzn.authcontrol.dao.RoleDao;
import com.cmzn.authcontrol.entity.domain.UserMtmRoleEntity;
import com.cmzn.authcontrol.service.UserMtmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.Query;

import com.cmzn.authcontrol.entity.domain.RoleEntity;
import com.cmzn.authcontrol.service.RoleService;
import org.springframework.util.CollectionUtils;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Autowired
    private UserMtmRoleService userMtmRoleService;

    @Override
    public PageResult<RoleEntity> queryPage(Map<String, Object> params) {
        IPage<RoleEntity> page = this.page(new Query<RoleEntity>().getPage(params), new QueryWrapper<>());
        return new PageResult<>(page);
    }

    @Override
    public List<RoleEntity> getAvailableRolesByUserId(Long userId) {
        List<UserMtmRoleEntity> urs = userMtmRoleService.list(new LambdaQueryWrapper<UserMtmRoleEntity>()
                .select(UserMtmRoleEntity::getRoleId)
                .eq(UserMtmRoleEntity::getUserId, userId));
        List<Long> rIds = urs.stream().map(UserMtmRoleEntity::getRoleId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(rIds)) {
            return new ArrayList<>(1);
        }
        return list(new LambdaQueryWrapper<RoleEntity>()
                .in(RoleEntity::getId, rIds)
                .eq(RoleEntity::getStatus, 1));
    }

}