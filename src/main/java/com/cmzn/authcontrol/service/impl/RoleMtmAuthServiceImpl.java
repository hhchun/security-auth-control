package com.cmzn.authcontrol.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.Query;

import com.cmzn.authcontrol.mapper.RoleMtmAuthDao;
import com.cmzn.authcontrol.entity.domain.RoleMtmAuthEntity;
import com.cmzn.authcontrol.service.RoleMtmAuthService;


@Service("roleMtmAuthService")
public class RoleMtmAuthServiceImpl extends ServiceImpl<RoleMtmAuthDao, RoleMtmAuthEntity> implements RoleMtmAuthService {

    @Override
    public PageResult<RoleMtmAuthEntity> queryPage(Map<String, Object> params) {
        IPage<RoleMtmAuthEntity> page = this.page(
                new Query<RoleMtmAuthEntity>().getPage(params),
                new QueryWrapper<RoleMtmAuthEntity>()
        );

        return new PageResult(page);
    }

}