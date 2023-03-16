package com.cmzn.authcontrol.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmzn.authcontrol.entity.domain.RoleMtmAuthEntity;
import com.cmzn.authcontrol.service.RoleMtmAuthService;
import com.cmzn.authcontrol.common.utils.PageResult;
import com.cmzn.authcontrol.common.utils.R;


/**
 * 角色与权限中间表(多对多)
 *
 * @author hhc
 * @email 12487489@qq.com
 * @date 2023-03-16 16:28:54
 */
@RestController
@RequestMapping("/rolemtmauth" )
public class RoleMtmAuthController {
    @Autowired
    private RoleMtmAuthService roleMtmAuthService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    public R<PageResult<RoleMtmAuthEntity>> list(@RequestParam Map<String, Object> params) {
        PageResult<RoleMtmAuthEntity> page = roleMtmAuthService.queryPage(params);

        return R.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    public R<RoleMtmAuthEntity> info(@PathVariable("id" ) Long id) {
            RoleMtmAuthEntity roleMtmAuth = roleMtmAuthService.getById(id);

        return R.success(roleMtmAuth);
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    public R<?> save(@RequestBody RoleMtmAuthEntity roleMtmAuth) {
            roleMtmAuthService.save(roleMtmAuth);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    public R<?> update(@RequestBody RoleMtmAuthEntity roleMtmAuth) {
            roleMtmAuthService.updateById(roleMtmAuth);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    public R<?> delete(@RequestBody Long[] ids) {
            roleMtmAuthService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
