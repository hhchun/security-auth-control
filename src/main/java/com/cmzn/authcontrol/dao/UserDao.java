package com.cmzn.authcontrol.dao;

import com.cmzn.authcontrol.entity.bo.UserBo;
import com.cmzn.authcontrol.entity.domain.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户
 * 
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    UserBo getUserContext(@Param("userId") Long userId);
}
