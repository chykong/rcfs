package com.balance.util.page;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
public class ListDTO<T> {
    @JsonProperty("totalNumber")
    private int total;//总条目数
    private List<T> list;//结果列表

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list == null ? new ArrayList<>() : list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
