package com.dlf.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageDTO<T> {
    //总数
    private Integer total;
    //列表
    private List<T> list;
    //每页数量
    private Integer pageSize = 10;
    //当前页数
    private Integer pageNum;
}
