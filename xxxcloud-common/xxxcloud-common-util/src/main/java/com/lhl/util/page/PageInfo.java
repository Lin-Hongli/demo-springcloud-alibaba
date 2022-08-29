package com.lhl.util.page;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo{
    private long pageNo;

    private long pageSize;

    private long total;

    private List<?> rows;

    public PageInfo() {

    }

    public PageInfo(int pageNo, int pageSize, long total, List<?> rows) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.rows = rows;
    }
}
