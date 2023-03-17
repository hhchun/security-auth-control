package com.cmzn.authcontrol.service.impl;

import com.cmzn.authcontrol.dao.AuthDao;
import com.cmzn.authcontrol.entity.bo.AuthBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import  com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.Query;

import com.cmzn.authcontrol.entity.domain.AuthEntity;
import com.cmzn.authcontrol.service.AuthService;


@Service("authService")
public class AuthServiceImpl extends ServiceImpl<AuthDao, AuthEntity> implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Override
    public PageResult<AuthEntity> queryPage(Map<String, Object> params) {
        IPage<AuthEntity> page = this.page(
                new Query<AuthEntity>().getPage(params),
                new QueryWrapper<AuthEntity>()
        );

        return new PageResult(page);
    }

    @Override
    public List<AuthBo> getAuths() {
        return authDao.getAuths();
    }

}