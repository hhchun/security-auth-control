package com.cmzn.authcontrol.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cmzn.authcontrol.auth.UserContext;
import com.cmzn.authcontrol.auth.UserContextHolder;
import com.cmzn.authcontrol.common.utils.TokenUtils;
import com.cmzn.authcontrol.dao.UserDao;
import com.cmzn.authcontrol.entity.bo.UserBo;
import com.cmzn.authcontrol.entity.domain.*;
import com.cmzn.authcontrol.entity.dto.LoginDto;
import com.cmzn.authcontrol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.Query;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageResult<UserEntity> queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageResult(page);
    }

    @Override
    public String login(LoginDto loginDto) {
        String account = loginDto.getAccount();
        String password = loginDto.getPassword();
        UserEntity user = getOne(new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getAccount, account));
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (password.equals(user.getPassword())) {
            UserContext context = getUserContext(user.getId());
            if (context == null) {
                throw new RuntimeException("获取用户上下文异常");
            }
            UserContextHolder.setContext(String.valueOf(user.getId()), context);
            return context.getToken();
        } else {
            throw new RuntimeException("密码错误");
        }
    }

    @Override
    public UserContext getUserContext(Long userId) {
        UserBo user = userDao.getUserContext(userId);
        if (user == null) {
            return null;
        }
        UserContext context = new UserContext();
        BeanUtil.copyProperties(user, context);
        context.setToken(TokenUtils.getToken(String.valueOf(userId)));
        return context;
    }
}