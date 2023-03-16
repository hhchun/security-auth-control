package com.cmzn.authcontrol.entity.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 权限
 * 
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
@Data
@TableName("auth")
public class AuthEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 是否删除
	 */
	private Integer del;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 权限描述
	 */
	private String des;
	/**
	 * 权限标识
	 */
	private String mark;
	/**
	 * 权限类型;api接口、menu菜单、button按钮
	 */
	private String type;
	/**
	 * 权限保护的目标对象
	 */
	private String subject;
	/**
	 * 权限状态,1正常、-1停用
	 */
	private Integer status;

}
