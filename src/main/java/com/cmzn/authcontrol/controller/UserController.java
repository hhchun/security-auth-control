package com.cmzn.authcontrol.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmzn.authcontrol.entity.domain.UserEntity;
import com.cmzn.authcontrol.service.UserService;
import com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.R;


/**
 * 用户
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
@RestController
@RequestMapping("/user" )
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    public R<PageResult<UserEntity>> list(@RequestParam Map<String, Object> params) {
        PageResult<UserEntity> page = userService.queryPage(params);

        return R.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    public R<UserEntity> info(@PathVariable("id" ) Long id) {
            UserEntity user = userService.getById(id);

        return R.success(user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    public R<?> save(@RequestBody UserEntity user) {
            userService.save(user);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    public R<?> update(@RequestBody UserEntity user) {
            userService.updateById(user);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    public R<?> delete(@RequestBody Long[] ids) {
            userService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
