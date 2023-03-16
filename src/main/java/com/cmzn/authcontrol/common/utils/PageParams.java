package com.cmzn.authcontrol.common.utils;

import lombok.Data;

@Data
public class PageParams {
    /**
     * 当前页面
     */
    private Integer currPage;
    /**
     * 每个纪录数
     */
    private Integer pageSize;
}
