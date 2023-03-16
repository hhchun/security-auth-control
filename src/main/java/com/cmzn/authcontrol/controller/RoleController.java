package com.cmzn.authcontrol.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmzn.authcontrol.entity.domain.RoleEntity;
import com.cmzn.authcontrol.service.RoleService;
import com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.R;


/**
 * 角色
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
@RestController
@RequestMapping("/role" )
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    public R<PageResult<RoleEntity>> list(@RequestParam Map<String, Object> params) {
        PageResult<RoleEntity> page = roleService.queryPage(params);

        return R.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    public R<RoleEntity> info(@PathVariable("id" ) Long id) {
            RoleEntity role = roleService.getById(id);

        return R.success(role);
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    public R<?> save(@RequestBody RoleEntity role) {
            roleService.save(role);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    public R<?> update(@RequestBody RoleEntity role) {
            roleService.updateById(role);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    public R<?> delete(@RequestBody Long[] ids) {
            roleService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
