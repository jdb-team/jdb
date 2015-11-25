package com.ezb.jdb.common;

import lombok.Data;

import java.util.List;

/**
 * author : liufeng
 * create time: 2015/8/3 16:49.
 */
@Data
public class PageResult<T> {

    //总记录数
    private int totalCount;

    //当前页
    private int curPage = 1;

    //页面大小
    private int pageSize = 10;

    //总页数
    private int pageCount;

    //起始记录
    private int firstResult;

    //页数据
    private List<T> resultList;

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
        pageCount = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            pageCount += 1;
        }
    }

    public void calcFirstResult(){
        firstResult = (curPage - 1) * pageSize;
    }
}
