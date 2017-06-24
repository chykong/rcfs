package com.balance.api.dto;

/**
 * @autho 孔垂云
 * @date 2017/6/24.
 */
public class HouseholdersDTO {
    private int id;//
    private float cog_land_area;//实际用地面积
    private String host_name;//户名
    private String map_id;//编号

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    @Override
    public String toString() {
        return "HouseholdersDTO{" +
                "id=" + id +
                ", cog_land_area=" + cog_land_area +
                ", host_name='" + host_name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCog_land_area() {
        return cog_land_area;
    }

    public void setCog_land_area(float cog_land_area) {
        this.cog_land_area = cog_land_area;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }
}
