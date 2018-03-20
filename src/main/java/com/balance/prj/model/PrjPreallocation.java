package com.balance.prj.model;


import com.balance.base.model.BasePlacementDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户信息 model
 */
public class PrjPreallocation {
    private int id;
    private String map_id;  //编号
    private String host_name;  //产权人姓名（被搬迁人）
    private String lessee_name;  //承租人
    private String location;  //坐落
    private String legal_name;  //法人
    private String id_card;  //身份证号
    private String house_property;  //房屋属性
    private String land_property;  //土地属性
    private Double total_land_area; //土地总面积
    private Double card_land_area;  //证载土地总面积
    private Double cog_land_area; //实际土地面积
    private Double lessee_land_area; //土地租赁面积
    private Double total_homestead_area; //总建筑面积
    private Double card_homestead_area; //证载建筑面积
    private Double no_card_homestead_area; //无证房屋面积
    private Double management_homestead_area; //经营面积
    private Integer house_register_num;  //户籍人数
    private BigDecimal money_homestead;  //房屋补偿款
    private BigDecimal money_machine;  //机器补偿款
    private BigDecimal money_adjunct;  //附属物补偿款
    private BigDecimal project_cooperate_award;  //工程配合奖
    private BigDecimal incentive_fees;  //补助奖励费
    private BigDecimal allocation_housing_assess_price;  //分配房源(面积、套数)评估价款
    private BigDecimal money_new_house;  //房屋重置成新价
    private BigDecimal money_new_adjunct;  //附属物重置成新价
    private BigDecimal money_relocate;  //搬家补助费
    private BigDecimal money_kd; //宽带移机费
    private BigDecimal money_ssbcf; //停产停业损失补偿费
    private BigDecimal money_dhyjf;  //电话移机费
    private BigDecimal money_yxdsyjf;  //有线电视移机费
    private BigDecimal money_ktyjf;  //空调移机费
    private BigDecimal money_rsqyjf;  //热水器移机费
    private BigDecimal total_yjf;  //总移机费
    private BigDecimal money_qt;  //其他
    private BigDecimal money_ql;  //弃楼补贴
    private BigDecimal money_ljjl;  //资源节约和垃圾减量奖励
    private BigDecimal money_no_house;  //未建房奖励
    private BigDecimal money_cy;  //创业补助
    private BigDecimal money_fwzh;  //房屋周转费
    private BigDecimal subsidy_relocate;  //补助奖励费
    private BigDecimal total_compensation;  //总补偿款
    private String in_host_date;  //入户时间
    private Double az_area; //安置面积
    private Double float_az_area;  //浮动安置面积
    private Integer one_room_num;  //一居室 套数
    private Double one_room_area; //一居室面积
    private Integer two_room_num;  //二居室  套数
    private Double two_room_area;  //二居室  面积
    private Integer three_room_num;  //三居室 套数
    private Double three_room_area;  //三居室  面积
    private Integer az_room_budget_num;  //安置房预估数量
    private Double az_room_budget_area;  //安置房预估面积
    private BigDecimal budget_compensation;  //预算房款
    private BigDecimal budget_sub;  //预算差价
    private String signed_code; //签约序号
    private String signed_date; //签约日期
    private String money_date; //放款日期
    private String handover_house_date; //交房日期
    private String handover_end_date; //交房截止日期
    private String select_house_code;  //选房序号
    private String choose_room_position;//选房位置
    private String az_house_host;  //安置房产权人
    private Integer az_house_room;   //安置房居室
    private Double az_house_area;  //安置房面积
    private String az_house_host2;  //安置房产权人2
    private Integer az_house_room2;    //安置房居室2
    private Double az_house_area2;   //安置房面积2
    private String az_house_host3;  //安置房产权人3
    private Integer az_house_room3;    //安置房居室3
    private Double az_house_area3;   //安置房面积3
    private String az_house_host4;  //安置房产权人4
    private Integer az_house_room4;   //安置房居室4
    private Double az_house_area4;   //安置房面积4
    private Double total_az_house_area;  //安置房总面积合计
    private Double float_az_house_area;  //安置房浮动面积合计
    private BigDecimal money_az_house;  //安置房价款
    private BigDecimal money_float_az_house;  //安置房浮动面积价款
    private BigDecimal total_az_pay_house;  //安置房购房款合计
    private BigDecimal first_calculate_sub;  //首次结算差价
    private String choose_room_date; // 选房时间
    private int status;  //状态 0-未入户  10-已入户未签约  20-已签约   30-已审核 40-已交房  50-已拆除 60-已放款  70-已归档
    private String leader;  //包村干部
    private String management_co;  //管理公司
    private String geo_co;  //测绘公司
    private String appraise_co; //评估公司
    private String demolish_co;  //拆迁公司
    private String pulledown_co;  //拆除公司
    private String audit_co;  //审计公司
    private String demolition_card_code;  //拆迁许可证号
    private String demolition_year_code;  //拆迁许可证年号

    private String village;  //村
    private String town;  //镇
    private String section;  //标段
    private String groups;  //组别
    private Integer prj_base_info_id;  //项目id
    private BigDecimal appraise_compensation;  //评估金额
    private BigDecimal appraise_money;  //评估价格
    private String demolished_date;  //拆除日期
    private String relocate_date;  //搬迁日期
    private Double before_demolished_area;  //待拆除面积
    private Double after_demolished_area;  //已拆除面积
    private Double before_area;  //06年前面积
    private Double between_area;  //06-09年间面积
    private Double after_area;  //09年后面积  或 09-13年面积
    private Double last_area;  //13 年后面积
    private Double management_house_area;  //房屋营业面积
    private Double field_house_area;  //场地营业面积
    private String no_sign_reason;//未签约原因
    private String audit_date;  //审核日期
    private String audit_user;  //审核人
    private String remarks;  //备注
    private String created_by;   //创建人
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created_at;   //创建时间
    private String archives_cabinet_number;//档案柜号
    private String archive_date;//存档时间

    private String appraise_person;  //评估人员
    private String demolish_person;  //服务人员

    private Integer rowIndex;  //导入时  行号


    private String other_file_name;  //基本情况文件名

    private String other_file_path;  //基本情况文件路径

    private String archive_file_name;  //归档文件名
    private String archive_file_path;  //归档路径

    private List<PrjPreallAttach> preallAttaches;  //附件图片List

    private int d_id;  //返回地图对应的id

    private Integer float_people;  //流动人口
    private Integer car_num;  //车辆
    private Integer rmgl_num;   //燃煤锅炉
    private Integer rqgl_num;   //燃气锅炉
    private Integer zqgl_num;   //蒸汽锅炉
    private Integer jzzz_num;  //家具制造
    private Integer scqg_num;  //
    private Integer qx_num;  //汽修
    private Integer wl_num;  //物流
    private Integer fz_num;  //服装
    private Integer qt_num;   //其他
    private Float scattered_coal;  //散煤
    //    private List<PreallocationRelation> relations;  //户籍关系
    private List<PreallocationRela> relas;  //家庭成员集合
    private String content;  //特殊情况


    private String build_code;  //楼号
    private String floor;  //楼层
    private String map_code;  //门牌号
    private int relas_num;
    private int compensation_type; //选房方式  1-面积  2-人口
    private String relas_detail;

    private List<BasePlacementDetail> selectHomes;  //选房信息
    private List<PrjPreallocation>  subHome;  //接收分户情况
    private List<PrjPreallocation>  subBc;  //接收补偿情况
    private int parent_type;  //分院情况 0-否  1-院  2-户
    private String parent_id; //分院id

    public List<PreallocationRela> getRelas() {
        return relas;
    }

    public int getParent_type() {
        return parent_type;
    }

    public List<BasePlacementDetail> getSelectHomes() {
        return selectHomes;
    }

    public void setSelectHomes(List<BasePlacementDetail> selectHomes) {
        this.selectHomes = selectHomes;
    }

    public void setParent_type(int parent_type) {
        this.parent_type = parent_type;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setRelas(List<PreallocationRela> relas) {
        this.relas = relas;
    }

    public String getArchive_file_name() {
        return archive_file_name;
    }

    public void setArchive_file_name(String archive_file_name) {
        this.archive_file_name = archive_file_name;
    }

    public String getArchive_file_path() {
        return archive_file_path;
    }

    public void setArchive_file_path(String archive_file_path) {
        this.archive_file_path = archive_file_path;
    }

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

    public String getLessee_name() {
        return lessee_name;
    }

    public void setLessee_name(String lessee_name) {
        this.lessee_name = lessee_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLegal_name() {
        return legal_name;
    }

    public void setLegal_name(String legal_name) {
        this.legal_name = legal_name;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getHouse_property() {
        return house_property;
    }

    public void setHouse_property(String house_property) {
        this.house_property = house_property;
    }

    public String getLand_property() {
        return land_property;
    }

    public void setLand_property(String land_property) {
        this.land_property = land_property;
    }

    public Double getTotal_land_area() {
        return total_land_area;
    }

    public void setTotal_land_area(Double total_land_area) {
        this.total_land_area = total_land_area;
    }

    public Double getCard_land_area() {
        return card_land_area;
    }

    public void setCard_land_area(Double card_land_area) {
        this.card_land_area = card_land_area;
    }

    public Double getCog_land_area() {
        return cog_land_area;
    }

    public void setCog_land_area(Double cog_land_area) {
        this.cog_land_area = cog_land_area;
    }

    public Double getLessee_land_area() {
        return lessee_land_area;
    }

    public void setLessee_land_area(Double lessee_land_area) {
        this.lessee_land_area = lessee_land_area;
    }

    public Double getTotal_homestead_area() {
        return total_homestead_area;
    }

    public void setTotal_homestead_area(Double total_homestead_area) {
        this.total_homestead_area = total_homestead_area;
    }

    public Double getCard_homestead_area() {
        return card_homestead_area;
    }

    public void setCard_homestead_area(Double card_homestead_area) {
        this.card_homestead_area = card_homestead_area;
    }

    public Double getNo_card_homestead_area() {
        return no_card_homestead_area;
    }

    public void setNo_card_homestead_area(Double no_card_homestead_area) {
        this.no_card_homestead_area = no_card_homestead_area;
    }

    public Double getManagement_homestead_area() {
        return management_homestead_area;
    }

    public void setManagement_homestead_area(Double management_homestead_area) {
        this.management_homestead_area = management_homestead_area;
    }

    public Integer getHouse_register_num() {
        return house_register_num;
    }

    public void setHouse_register_num(Integer house_register_num) {
        this.house_register_num = house_register_num;
    }

    public BigDecimal getMoney_homestead() {
        return money_homestead;
    }

    public void setMoney_homestead(BigDecimal money_homestead) {
        this.money_homestead = money_homestead;
    }

    public BigDecimal getMoney_machine() {
        return money_machine;
    }

    public void setMoney_machine(BigDecimal money_machine) {
        this.money_machine = money_machine;
    }

    public BigDecimal getMoney_adjunct() {
        return money_adjunct;
    }

    public void setMoney_adjunct(BigDecimal money_adjunct) {
        this.money_adjunct = money_adjunct;
    }

    public BigDecimal getProject_cooperate_award() {
        return project_cooperate_award;
    }

    public void setProject_cooperate_award(BigDecimal project_cooperate_award) {
        this.project_cooperate_award = project_cooperate_award;
    }

    public BigDecimal getIncentive_fees() {
        return incentive_fees;
    }

    public void setIncentive_fees(BigDecimal incentive_fees) {
        this.incentive_fees = incentive_fees;
    }

    public BigDecimal getAllocation_housing_assess_price() {
        return allocation_housing_assess_price;
    }

    public void setAllocation_housing_assess_price(BigDecimal allocation_housing_assess_price) {
        this.allocation_housing_assess_price = allocation_housing_assess_price;
    }

    public BigDecimal getMoney_new_house() {
        return money_new_house;
    }

    public void setMoney_new_house(BigDecimal money_new_house) {
        this.money_new_house = money_new_house;
    }

    public BigDecimal getMoney_new_adjunct() {
        return money_new_adjunct;
    }

    public void setMoney_new_adjunct(BigDecimal money_new_adjunct) {
        this.money_new_adjunct = money_new_adjunct;
    }

    public BigDecimal getMoney_relocate() {
        return money_relocate;
    }

    public void setMoney_relocate(BigDecimal money_relocate) {
        this.money_relocate = money_relocate;
    }

    public BigDecimal getMoney_kd() {
        return money_kd;
    }

    public void setMoney_kd(BigDecimal money_kd) {
        this.money_kd = money_kd;
    }

    public BigDecimal getMoney_ssbcf() {
        return money_ssbcf;
    }

    public void setMoney_ssbcf(BigDecimal money_ssbcf) {
        this.money_ssbcf = money_ssbcf;
    }

    public BigDecimal getMoney_dhyjf() {
        return money_dhyjf;
    }

    public void setMoney_dhyjf(BigDecimal money_dhyjf) {
        this.money_dhyjf = money_dhyjf;
    }

    public BigDecimal getMoney_yxdsyjf() {
        return money_yxdsyjf;
    }

    public void setMoney_yxdsyjf(BigDecimal money_yxdsyjf) {
        this.money_yxdsyjf = money_yxdsyjf;
    }

    public BigDecimal getMoney_ktyjf() {
        return money_ktyjf;
    }

    public void setMoney_ktyjf(BigDecimal money_ktyjf) {
        this.money_ktyjf = money_ktyjf;
    }

    public BigDecimal getMoney_rsqyjf() {
        return money_rsqyjf;
    }

    public void setMoney_rsqyjf(BigDecimal money_rsqyjf) {
        this.money_rsqyjf = money_rsqyjf;
    }

    public BigDecimal getTotal_yjf() {
        return total_yjf;
    }

    public void setTotal_yjf(BigDecimal total_yjf) {
        this.total_yjf = total_yjf;
    }

    public BigDecimal getMoney_qt() {
        return money_qt;
    }

    public void setMoney_qt(BigDecimal money_qt) {
        this.money_qt = money_qt;
    }

    public BigDecimal getSubsidy_relocate() {
        return subsidy_relocate;
    }

    public void setSubsidy_relocate(BigDecimal subsidy_relocate) {
        this.subsidy_relocate = subsidy_relocate;
    }

    public BigDecimal getTotal_compensation() {
        return total_compensation;
    }

    public void setTotal_compensation(BigDecimal total_compensation) {
        this.total_compensation = total_compensation;
    }

    public String getIn_host_date() {
        return in_host_date;
    }

    public void setIn_host_date(String in_host_date) {
        this.in_host_date = in_host_date;
    }

    public Double getAz_area() {
        return az_area;
    }

    public void setAz_area(Double az_area) {
        this.az_area = az_area;
    }

    public Double getFloat_az_area() {
        return float_az_area;
    }

    public void setFloat_az_area(Double float_az_area) {
        this.float_az_area = float_az_area;
    }

    public Integer getOne_room_num() {
        return one_room_num;
    }

    public void setOne_room_num(Integer one_room_num) {
        this.one_room_num = one_room_num;
    }

    public Double getOne_room_area() {
        return one_room_area;
    }

    public void setOne_room_area(Double one_room_area) {
        this.one_room_area = one_room_area;
    }

    public Integer getTwo_room_num() {
        return two_room_num;
    }

    public void setTwo_room_num(Integer two_room_num) {
        this.two_room_num = two_room_num;
    }

    public Double getTwo_room_area() {
        return two_room_area;
    }

    public void setTwo_room_area(Double two_room_area) {
        this.two_room_area = two_room_area;
    }

    public Integer getThree_room_num() {
        return three_room_num;
    }

    public void setThree_room_num(Integer three_room_num) {
        this.three_room_num = three_room_num;
    }

    public Double getThree_room_area() {
        return three_room_area;
    }

    public void setThree_room_area(Double three_room_area) {
        this.three_room_area = three_room_area;
    }

    public Integer getAz_room_budget_num() {
        return az_room_budget_num;
    }

    public void setAz_room_budget_num(Integer az_room_budget_num) {
        this.az_room_budget_num = az_room_budget_num;
    }

    public Double getAz_room_budget_area() {
        return az_room_budget_area;
    }

    public void setAz_room_budget_area(Double az_room_budget_area) {
        this.az_room_budget_area = az_room_budget_area;
    }

    public BigDecimal getBudget_compensation() {
        return budget_compensation;
    }

    public void setBudget_compensation(BigDecimal budget_compensation) {
        this.budget_compensation = budget_compensation;
    }

    public BigDecimal getBudget_sub() {
        return budget_sub;
    }

    public void setBudget_sub(BigDecimal budget_sub) {
        this.budget_sub = budget_sub;
    }

    public String getSigned_code() {
        return signed_code;
    }

    public void setSigned_code(String signed_code) {
        this.signed_code = signed_code;
    }

    public String getSigned_date() {
        return signed_date;
    }

    public void setSigned_date(String signed_date) {
        this.signed_date = signed_date;
    }

    public String getMoney_date() {
        return money_date;
    }

    public void setMoney_date(String money_date) {
        this.money_date = money_date;
    }

    public String getHandover_house_date() {
        return handover_house_date;
    }

    public void setHandover_house_date(String handover_house_date) {
        this.handover_house_date = handover_house_date;
    }

    public String getHandover_end_date() {
        return handover_end_date;
    }

    public void setHandover_end_date(String handover_end_date) {
        this.handover_end_date = handover_end_date;
    }

    public String getSelect_house_code() {
        return select_house_code;
    }

    public void setSelect_house_code(String select_house_code) {
        this.select_house_code = select_house_code;
    }

    public String getChoose_room_position() {
        return choose_room_position;
    }

    public void setChoose_room_position(String choose_room_position) {
        this.choose_room_position = choose_room_position;
    }

    public String getAz_house_host() {
        return az_house_host;
    }

    public void setAz_house_host(String az_house_host) {
        this.az_house_host = az_house_host;
    }

    public Integer getAz_house_room() {
        return az_house_room;
    }

    public void setAz_house_room(Integer az_house_room) {
        this.az_house_room = az_house_room;
    }

    public Double getAz_house_area() {
        return az_house_area;
    }

    public void setAz_house_area(Double az_house_area) {
        this.az_house_area = az_house_area;
    }

    public String getAz_house_host2() {
        return az_house_host2;
    }

    public void setAz_house_host2(String az_house_host2) {
        this.az_house_host2 = az_house_host2;
    }

    public Integer getAz_house_room2() {
        return az_house_room2;
    }

    public void setAz_house_room2(Integer az_house_room2) {
        this.az_house_room2 = az_house_room2;
    }

    public Double getAz_house_area2() {
        return az_house_area2;
    }

    public void setAz_house_area2(Double az_house_area2) {
        this.az_house_area2 = az_house_area2;
    }

    public String getAz_house_host3() {
        return az_house_host3;
    }

    public void setAz_house_host3(String az_house_host3) {
        this.az_house_host3 = az_house_host3;
    }

    public Integer getAz_house_room3() {
        return az_house_room3;
    }

    public void setAz_house_room3(Integer az_house_room3) {
        this.az_house_room3 = az_house_room3;
    }

    public Double getAz_house_area3() {
        return az_house_area3;
    }

    public void setAz_house_area3(Double az_house_area3) {
        this.az_house_area3 = az_house_area3;
    }

    public String getAz_house_host4() {
        return az_house_host4;
    }

    public void setAz_house_host4(String az_house_host4) {
        this.az_house_host4 = az_house_host4;
    }

    public Integer getAz_house_room4() {
        return az_house_room4;
    }

    public void setAz_house_room4(Integer az_house_room4) {
        this.az_house_room4 = az_house_room4;
    }

    public Double getAz_house_area4() {
        return az_house_area4;
    }

    public void setAz_house_area4(Double az_house_area4) {
        this.az_house_area4 = az_house_area4;
    }

    public Double getTotal_az_house_area() {
        return total_az_house_area;
    }

    public void setTotal_az_house_area(Double total_az_house_area) {
        this.total_az_house_area = total_az_house_area;
    }

    public Double getFloat_az_house_area() {
        return float_az_house_area;
    }

    public void setFloat_az_house_area(Double float_az_house_area) {
        this.float_az_house_area = float_az_house_area;
    }

    public BigDecimal getMoney_az_house() {
        return money_az_house;
    }

    public void setMoney_az_house(BigDecimal money_az_house) {
        this.money_az_house = money_az_house;
    }

    public BigDecimal getMoney_float_az_house() {
        return money_float_az_house;
    }

    public void setMoney_float_az_house(BigDecimal money_float_az_house) {
        this.money_float_az_house = money_float_az_house;
    }

    public BigDecimal getTotal_az_pay_house() {
        return total_az_pay_house;
    }

    public void setTotal_az_pay_house(BigDecimal total_az_pay_house) {
        this.total_az_pay_house = total_az_pay_house;
    }

    public BigDecimal getFirst_calculate_sub() {
        return first_calculate_sub;
    }

    public void setFirst_calculate_sub(BigDecimal first_calculate_sub) {
        this.first_calculate_sub = first_calculate_sub;
    }

    public String getChoose_room_date() {
        return choose_room_date;
    }

    public void setChoose_room_date(String choose_room_date) {
        this.choose_room_date = choose_room_date;
    }

    public String getStatusString() {
        switch (status) {
            case 0:
                return "未入户";
            case 10:
                return "已入户未签约";
            case 20:
                return "已签约";
            case 30:
                return "已审核";
            case 40:
                return "已交房";
            case 50:
                return "已拆除";
            case 60:
                return "已放款";
            case 70:
                return "已归档";
        }

        return "";
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

    public String getDemolition_card_code() {
        return demolition_card_code;
    }

    public void setDemolition_card_code(String demolition_card_code) {
        this.demolition_card_code = demolition_card_code;
    }

    public String getDemolition_year_code() {
        return demolition_year_code;
    }

    public void setDemolition_year_code(String demolition_year_code) {
        this.demolition_year_code = demolition_year_code;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Integer getPrj_base_info_id() {
        return prj_base_info_id;
    }

    public void setPrj_base_info_id(Integer prj_base_info_id) {
        this.prj_base_info_id = prj_base_info_id;
    }

    public BigDecimal getAppraise_compensation() {
        return appraise_compensation;
    }

    public void setAppraise_compensation(BigDecimal appraise_compensation) {
        this.appraise_compensation = appraise_compensation;
    }

    public BigDecimal getAppraise_money() {
        return appraise_money;
    }

    public void setAppraise_money(BigDecimal appraise_money) {
        this.appraise_money = appraise_money;
    }

    public String getDemolished_date() {
        return demolished_date;
    }

    public void setDemolished_date(String demolished_date) {
        this.demolished_date = demolished_date;
    }

    public String getRelocate_date() {
        return relocate_date;
    }

    public void setRelocate_date(String relocate_date) {
        this.relocate_date = relocate_date;
    }

    public Double getBefore_demolished_area() {
        return before_demolished_area;
    }

    public void setBefore_demolished_area(Double before_demolished_area) {
        this.before_demolished_area = before_demolished_area;
    }

    public Double getAfter_demolished_area() {
        return after_demolished_area;
    }

    public void setAfter_demolished_area(Double after_demolished_area) {
        this.after_demolished_area = after_demolished_area;
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

    public String getAudit_date() {
        return audit_date;
    }

    public void setAudit_date(String audit_date) {
        this.audit_date = audit_date;
    }

    public String getAudit_user() {
        return audit_user;
    }

    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getArchives_cabinet_number() {
        return archives_cabinet_number;
    }

    public void setArchives_cabinet_number(String archives_cabinet_number) {
        this.archives_cabinet_number = archives_cabinet_number;
    }

    public String getArchive_date() {
        return archive_date;
    }

    public void setArchive_date(String archive_date) {
        this.archive_date = archive_date;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getOther_file_name() {
        return other_file_name;
    }

    public void setOther_file_name(String other_file_name) {
        this.other_file_name = other_file_name;
    }

    public String getOther_file_path() {
        return other_file_path;
    }

    public void setOther_file_path(String other_file_path) {
        this.other_file_path = other_file_path;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getAppraise_person() {
        return appraise_person;
    }

    public void setAppraise_person(String appraise_person) {
        this.appraise_person = appraise_person;
    }

    public String getDemolish_person() {
        return demolish_person;
    }

    public void setDemolish_person(String demolish_person) {
        this.demolish_person = demolish_person;
    }

    public List<PrjPreallAttach> getPreallAttaches() {
        return preallAttaches;
    }

    public void setPreallAttaches(List<PrjPreallAttach> preallAttaches) {
        this.preallAttaches = preallAttaches;
    }

    public Integer getFloat_people() {
        return float_people;
    }

    public void setFloat_people(Integer float_people) {
        this.float_people = float_people;
    }

    public Integer getCar_num() {
        return car_num;
    }

    public void setCar_num(Integer car_num) {
        this.car_num = car_num;
    }

    public Integer getRmgl_num() {
        return rmgl_num;
    }

    public void setRmgl_num(Integer rmgl_num) {
        this.rmgl_num = rmgl_num;
    }

    public Integer getRqgl_num() {
        return rqgl_num;
    }

    public void setRqgl_num(Integer rqgl_num) {
        this.rqgl_num = rqgl_num;
    }

    public Integer getZqgl_num() {
        return zqgl_num;
    }

    public void setZqgl_num(Integer zqgl_num) {
        this.zqgl_num = zqgl_num;
    }

    public Integer getJzzz_num() {
        return jzzz_num;
    }

    public void setJzzz_num(Integer jzzz_num) {
        this.jzzz_num = jzzz_num;
    }

    public Integer getScqg_num() {
        return scqg_num;
    }

    public void setScqg_num(Integer scqg_num) {
        this.scqg_num = scqg_num;
    }

    public Integer getQx_num() {
        return qx_num;
    }

    public void setQx_num(Integer qx_num) {
        this.qx_num = qx_num;
    }

    public Integer getWl_num() {
        return wl_num;
    }

    public void setWl_num(Integer wl_num) {
        this.wl_num = wl_num;
    }

    public Integer getFz_num() {
        return fz_num;
    }

    public void setFz_num(Integer fz_num) {
        this.fz_num = fz_num;
    }

    public Integer getQt_num() {
        return qt_num;
    }

    public void setQt_num(Integer qt_num) {
        this.qt_num = qt_num;
    }

    public Float getScattered_coal() {
        return scattered_coal;
    }

    public void setScattered_coal(Float scattered_coal) {
        this.scattered_coal = scattered_coal;
    }

    public Double getLast_area() {
        return last_area;
    }

    public void setLast_area(Double last_area) {
        this.last_area = last_area;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBuild_code() {
        return build_code;
    }

    public void setBuild_code(String build_code) {
        this.build_code = build_code;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getMap_code() {
        return map_code;
    }

    public void setMap_code(String map_code) {
        this.map_code = map_code;
    }

    public int getRelas_num() {
        return relas_num;
    }

    public void setRelas_num(int relas_num) {
        this.relas_num = relas_num;
    }

    public String getRelas_detail() {
        return relas_detail;
    }

    public void setRelas_detail(String relas_detail) {
        this.relas_detail = relas_detail;
    }

    public int getCompensation_type() {
        return compensation_type;
    }

    public void setCompensation_type(int compensation_type) {
        this.compensation_type = compensation_type;
    }

    public BigDecimal getMoney_ql() {
        return money_ql;
    }

    public void setMoney_ql(BigDecimal money_ql) {
        this.money_ql = money_ql;
    }

    public BigDecimal getMoney_ljjl() {
        return money_ljjl;
    }

    public void setMoney_ljjl(BigDecimal money_ljjl) {
        this.money_ljjl = money_ljjl;
    }

    public BigDecimal getMoney_no_house() {
        return money_no_house;
    }

    public void setMoney_no_house(BigDecimal money_no_house) {
        this.money_no_house = money_no_house;
    }

    public BigDecimal getMoney_cy() {
        return money_cy;
    }

    public void setMoney_cy(BigDecimal money_cy) {
        this.money_cy = money_cy;
    }

    public BigDecimal getMoney_fwzh() {
        return money_fwzh;
    }

    public void setMoney_fwzh(BigDecimal money_fwzh) {
        this.money_fwzh = money_fwzh;
    }

    public List<PrjPreallocation> getSubHome() {
        return subHome;
    }

    public void setSubHome(List<PrjPreallocation> subHome) {
        this.subHome = subHome;
    }

    public List<PrjPreallocation> getSubBc() {
        return subBc;
    }

    public void setSubBc(List<PrjPreallocation> subBc) {
        this.subBc = subBc;
    }
}
