package com.balance.api.dto;

/**
 * Created by dsy on 2017/6/26.
 * GIS地图使用的dto
 */
public class GisDTO {
    private int id;
    private String map_id;  //编号
    private String host_name;  //产权人姓名（被搬迁人）
    private String location;  //坐落

    private int status;  //状态 0-未入户  10-已入户未签约  20-已签约   30-已审核 40-已交房  50-已拆除 60-已放款  70-已归档
    private String leader;  //包村干部
    private String management_co;  //管理公司
    private String geo_co;  //测绘公司
    private String appraise_co; //评估公司
    private String demolish_co;  //拆迁公司
    private String pulledown_co;  //拆除公司
    private String audit_co;  //审计公司

    private Integer prj_base_info_id;  //项目id

    private Double before_area;  //06年前面积
    private Double between_area;  //06-09年间面积
    private Double after_area;  //09年后面积
    private Double management_house_area;  //房屋营业面积
    private Double field_house_area;  //场地营业面积
    private String no_sign_reason;//未签约原因
    private String remarks;  //备注

    private int d_id;  //返回地图对应的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getManagement_co() {
        return management_co;
    }

    public void setManagement_co(String management_co) {
        this.management_co = management_co;
    }

    public String getGeo_co() {
        return geo_co;
    }

    public void setGeo_co(String geo_co) {
        this.geo_co = geo_co;
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

    public String getPulledown_co() {
        return pulledown_co;
    }

    public void setPulledown_co(String pulledown_co) {
        this.pulledown_co = pulledown_co;
    }

    public String getAudit_co() {
        return audit_co;
    }

    public void setAudit_co(String audit_co) {
        this.audit_co = audit_co;
    }


    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

    public Double getBefore_area() {
        return before_area;
    }

    public void setBefore_area(Double before_area) {
        this.before_area = before_area;
    }

    public Double getBetween_area() {
        return between_area;
    }

    public void setBetween_area(Double between_area) {
        this.between_area = between_area;
    }

    public Double getAfter_area() {
        return after_area;
    }

    public void setAfter_area(Double after_area) {
        this.after_area = after_area;
    }

    public Double getManagement_house_area() {
        return management_house_area;
    }

    public void setManagement_house_area(Double management_house_area) {
        this.management_house_area = management_house_area;
    }

    public Double getField_house_area() {
        return field_house_area;
    }

    public void setField_house_area(Double field_house_area) {
        this.field_house_area = field_house_area;
    }

    public String getNo_sign_reason() {
        return no_sign_reason;
    }

    public void setNo_sign_reason(String no_sign_reason) {
        this.no_sign_reason = no_sign_reason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }
}
