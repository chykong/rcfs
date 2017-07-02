package com.balance.api.dto;

/**
 * @autho 孔垂云
 * @date 2017/6/24.
 */
public class HouseholdersDetailDTO {
    private int id;//
    private float cog_land_area;//实际用地面积
    private String host_name;//户名
    private String map_id;//编号
    private String location;//座落
    private int status;//状态 0-未入户  10-已入户未签约  20-已签约 30-已审核 40-已交房  50-已拆除 60-已放款 70-已归档
    private float total_homestead_area;//总建筑面积
    private String remarks;//备注
    private float before_area;//06年前面积
    private float between_area;//06-09年间面积
    private float after_area;//09年后面积
    private String appraise_co;//评估公司
    private String demolish_co;//拆迁公司

    @Override
    public String toString() {
        return "HouseholdersDetailDTO{" +
                "id=" + id +
                ", cog_land_area=" + cog_land_area +
                ", host_name='" + host_name + '\'' +
                ", map_id='" + map_id + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", total_homestead_area=" + total_homestead_area +
                ", remarks='" + remarks + '\'' +
                ", before_area=" + before_area +
                ", between_area=" + between_area +
                ", after_area=" + after_area +
                ", appraise_co='" + appraise_co + '\'' +
                ", demolish_co='" + demolish_co + '\'' +
                ", no_sign_reson='" + no_sign_reson + '\'' +
                '}';
    }

    public String getAppraise_co() {
        return appraise_co;
    }

    public void setAppraise_co(String appraise_co) {
        this.appraise_co = appraise_co;
    }

    public String getDemolish_co() {
        return demolish_co;
    }

    public void setDemolish_co(String demolish_co) {
        this.demolish_co = demolish_co;
    }

    private String no_sign_reson;//滞留原因

    public float getBefore_area() {
        return before_area;
    }

    public void setBefore_area(float before_area) {
        this.before_area = before_area;
    }

    public float getBetween_area() {
        return between_area;
    }

    public void setBetween_area(float between_area) {
        this.between_area = between_area;
    }

    public float getAfter_area() {
        return after_area;
    }

    public void setAfter_area(float after_area) {
        this.after_area = after_area;
    }

    public String getNo_sign_reson() {
        return no_sign_reson;
    }

    public void setNo_sign_reson(String no_sign_reson) {
        this.no_sign_reson = no_sign_reson;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTotal_homestead_area() {
        return total_homestead_area;
    }

    public void setTotal_homestead_area(float total_homestead_area) {
        this.total_homestead_area = total_homestead_area;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
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
