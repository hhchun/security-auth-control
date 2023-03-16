package com.cmzn.authcontrol.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmzn.authcontrol.entity.domain.UserMtmRoleEntity;
import com.cmzn.authcontrol.service.UserMtmRoleService;
import com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.R;


/**
 * 用户与角色中间表(多对多)
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
@RestController
@RequestMapping("/usermtmrole" )
public class UserMtmRoleController {
    @Autowired
    private UserMtmRoleService userMtmRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    public R<PageResult<UserMtmRoleEntity>> list(@RequestParam Map<String, Object> params) {
        PageResult<UserMtmRoleEntity> page = userMtmRoleService.queryPage(params);

        return R.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    public R<UserMtmRoleEntity> info(@PathVariable("id" ) Long id) {
            UserMtmRoleEntity userMtmRole = userMtmRoleService.getById(id);

        return R.success(userMtmRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    public R<?> save(@RequestBody UserMtmRoleEntity userMtmRole) {
            userMtmRoleService.save(userMtmRole);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    public R<?> update(@RequestBody UserMtmRoleEntity userMtmRole) {
            userMtmRoleService.updateById(userMtmRole);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    public R<?> delete(@RequestBody Long[] ids) {
            userMtmRoleService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
