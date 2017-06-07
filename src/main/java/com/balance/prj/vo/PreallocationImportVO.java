package com.balance.prj.vo;

/**
 * Created by dsy on 2017/6/6.
 * 导入VO
 */
public class PreallocationImportVO {
    private int rowIndex;//错误行号
    private String reason; //错误原因

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
