package com.cmzn.authcontrol.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmzn.authcontrol.entity.domain.AuthEntity;
import com.cmzn.authcontrol.service.AuthService;
import com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.R;


/**
 * 权限
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
@RestController
@RequestMapping("/auth" )
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    public R<PageResult<AuthEntity>> list(@RequestParam Map<String, Object> params) {
        PageResult<AuthEntity> page = authService.queryPage(params);

        return R.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    public R<AuthEntity> info(@PathVariable("id" ) Long id) {
            AuthEntity auth = authService.getById(id);

        return R.success(auth);
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    public R<?> save(@RequestBody AuthEntity auth) {
            authService.save(auth);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    public R<?> update(@RequestBody AuthEntity auth) {
            authService.updateById(auth);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    public R<?> delete(@RequestBody Long[] ids) {
            authService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
