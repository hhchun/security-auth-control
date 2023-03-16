package com.cmzn.authcontrol.dao;

import com.cmzn.authcontrol.entity.domain.UserMtmRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色中间表(多对多)
 * 
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
@Mapper
public interface UserMtmRoleDao extends BaseMapper<UserMtmRoleEntity> {
	
}
