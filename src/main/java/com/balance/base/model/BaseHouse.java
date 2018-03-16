package com.balance.base.model;

/**
 * Created by 杨康康 on 2018/3/3.
 */
public class BaseHouse {
    /**
     * 户型id
     */
    private String id;
    /**
     * 户型名称
     */
    private String name;
    /**
     * 户型图
     */
    private String houseImage;
    /**
     * 户型描述
     */
    private String houseDescribe;
    /**
     * 项目id
     */
    private Integer prjBaseInfoId;

    @Override
    public String toString() {
        return "BaseHouse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", houseImage='" + houseImage + '\'' +
                ", houseDescribe='" + houseDescribe + '\'' +
                ", prjBaseInfoId='" + prjBaseInfoId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouseImage() {
        return houseImage;
    }

    public void setHouseImage(String houseImage) {
        this.houseImage = houseImage;
    }

    public String getHouseDescribe() {
        return houseDescribe;
    }

    public void setHouseDescribe(String houseDescribe) {
        this.houseDescribe = houseDescribe;
    }

    public Integer getPrjBaseInfoId() {
        return prjBaseInfoId;
    }

    public void setPrjBaseInfoId(Integer prjBaseInfoId) {
        this.prjBaseInfoId = prjBaseInfoId;
    }
}
