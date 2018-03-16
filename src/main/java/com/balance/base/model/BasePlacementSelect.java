package com.balance.base.model;

import java.util.List;

/**
 * Created by dsy on 2018/3/5.
 * 安置房选房用model
 */
public class BasePlacementSelect {
    private int total;  //可选总数
    private int noSelect;  //可选套数
    private List<String>  buildCodeList;  //楼号集合
    private List<BasePlacementFloor>  floorList;  //楼号集合

    private List<BasePlacementDetail> selectList; //选房集合

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNoSelect() {
        return noSelect;
    }

    public void setNoSelect(int noSelect) {
        this.noSelect = noSelect;
    }

    public List<String> getBuildCodeList() {
        return buildCodeList;
    }

    public void setBuildCodeList(List<String> buildCodeList) {
        this.buildCodeList = buildCodeList;
    }

    public List<BasePlacementDetail> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<BasePlacementDetail> selectList) {
        this.selectList = selectList;
    }

    public List<BasePlacementFloor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<BasePlacementFloor> floorList) {
        this.floorList = floorList;
    }
}
