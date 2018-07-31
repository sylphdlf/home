package com.dlf.model.dto;

import java.util.List;

public class PageDTO<T> {
    //总数
    private Integer total;
    //列表
    private List<T> list;
    //每页数量
    private Integer itemsPerPage;
    //当前页数
    private Integer currentPage;
}
