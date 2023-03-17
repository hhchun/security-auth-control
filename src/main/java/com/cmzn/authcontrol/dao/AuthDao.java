package com.cmzn.authcontrol.dao;

import com.cmzn.authcontrol.entity.bo.AuthBo;
import com.cmzn.authcontrol.entity.domain.AuthEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 权限
 * 
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
@Mapper
public interface AuthDao extends BaseMapper<AuthEntity> {

    List<AuthBo> getAuths();
}
