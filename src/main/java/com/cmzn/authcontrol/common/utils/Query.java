package com.cmzn.authcontrol.common.utils;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;


/**
 * 查询参数
 *
 * @author hhc
 */
public class Query<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if (params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constant.LIMIT));
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //分页参数
        params.put(Constant.PAGE, page);

        //排序字段
        String orderField = sqlInject((String) params.get(Constant.ORDER_FIELD));
        String order = (String) params.get(Constant.ORDER);


        //前端字段排序
        if (StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)) {
            if (Constant.ASC.equalsIgnoreCase(order)) {
                return page.addOrder(OrderItem.asc(orderField));
            } else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if (StringUtils.isBlank(defaultOrderField)) {
            return page;
        }

        //默认排序
        if (isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        } else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    protected static String sqlInject(String str) {
        if (org.springframework.util.StringUtils.isEmpty(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = org.springframework.util.StringUtils.replace(str, "'", "");
        str = org.springframework.util.StringUtils.replace(str, "\"", "");
        str = org.springframework.util.StringUtils.replace(str, ";", "");
        str = org.springframework.util.StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.contains(keyword)) {
                throw new RuntimeException("包含非法字符");
            }
        }

        return str;
    }

    public IPage<T> getPage(PageParams pageParams) {
        //分页参数
        long curPage = 1;
        long pageSize = 10;

        if (pageParams.getCurrPage() != null && pageParams.getCurrPage() > 0) {
            curPage = pageParams.getCurrPage();
        }
        if (pageParams.getPageSize() != null && pageParams.getCurrPage() > 0) {
            pageSize = pageParams.getPageSize();
        }

        //分页对象
        return new Page<>(curPage, pageSize);
    }

    public static class Constant {
        /**
         * 当前页码
         */
        public static final String PAGE = "page";
        /**
         * 每页显示记录数
         */
        public static final String LIMIT = "limit";
        /**
         * 排序方式
         */
        public static final String ORDER = "order";
        /**
         * 升序
         */
        public static final String ASC = "asc";

        /**
         * 排序字段
         */
        public static final String ORDER_FIELD = "sidx";
    }
}
