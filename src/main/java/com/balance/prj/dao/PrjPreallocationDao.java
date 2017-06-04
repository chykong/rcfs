package com.balance.prj.dao;


import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import com.balance.util.string.StringUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dsy on 2017/1/7.
 * 拆迁户信息 Dao
 */
@Repository
public class PrjPreallocationDao extends BaseDao<PrjPreallocation, PrjPreallocationSearchVO> {

    public List<PrjPreallocation> findAll(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        String sql = "select plc.* from t_prj_preallocation plc";
        sql += createSearchSql(prjPreallocationSearchVO);
        sql += " order by plc.id desc";
        sql = PageUtil.createMysqlPageSql(sql, prjPreallocationSearchVO.getPageIndex(), prjPreallocationSearchVO.getPageSize());
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocationSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjPreallocation.class));
    }

    public int count(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        String sql = "select count(*) from t_prj_preallocation plc";
        sql += createSearchSql(prjPreallocationSearchVO);

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocationSearchVO);
        return getNamedParameterJdbcTemplate().queryForObject(sql, params, Integer.class);
    }

    public int add(PrjPreallocation prjPreallocation) {
        String sql = "INSERT INTO t_prj_preallocation(map_id, host_name, location, id_card, house_property," +
                " money_homestead, money_adjunct, incentive_fees, money_relocate, money_ssbcf, money_dhyjf,money_yxdsyjf," +
                " money_ktyjf, money_rsqyjf, subsidy_relocate,total_compensation, handover_house_date,signed_date, leader," +
                " management_co, geo_co, appraise_co, demolish_co, pulledown_co, prj_base_info_id, appraise_compensation," +
                " archive_date, demolished_date, audit_date, remarks, created_by, created_at, section, groups, other_file_name," +
                " other_file_path, lessee_name, legal_name, land_property, total_land_area, card_land_area, cog_land_area," +
                " lessee_land_area, total_homestead_area, card_homestead_area, no_card_homestead_area, management_homestead_area," +
                " money_machine, project_cooperate_award, money_kd, money_qt, relocate_date, handover_date, audit_co, " +
                "demolition_card_code, demolition_year_code, money_date, village, town, before_area, between_area, after_area," +
                " management_house_area, field_house_area, no_sign_reson) " +
                "VALUES(:map_id, :host_name, :location, :id_card, :house_property," +
                " :money_homestead, :money_adjunct, :incentive_fees, :money_relocate, :money_ssbcf, :money_dhyjf,:money_yxdsyjf," +
                " :money_ktyjf, :money_rsqyjf, :subsidy_relocate,:total_compensation, :handover_house_date,:signed_date, :leader," +
                " :management_co, :geo_co, :appraise_co, :demolish_co, :pulledown_co, :prj_base_info_id, :appraise_compensation," +
                " :archive_date, :demolished_date, :audit_date, :remarks, :created_by, :created_at, :section, :groups, :other_file_name," +
                " :other_file_path, :lessee_name, :legal_name, :land_property, :total_land_area, :card_land_area, :cog_land_area," +
                " :lessee_land_area, :total_homestead_area, :card_homestead_area, :no_card_homestead_area, :management_homestead_area," +
                " :money_machine, :project_cooperate_award, :money_kd, :money_qt, :relocate_date, :handover_date, :audit_co, " +
                " :demolition_card_code, :demolition_year_code, :money_date, :village, :town, :before_area, :between_area, :after_area," +
                " :management_house_area, :field_house_area, :no_sign_reson)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, param);
    }

    public int update(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET  host_name=:host_name, location=:location, id_card=:id_card, house_property=:house_property," +
                " money_homestead=:money_homestead, money_adjunct=:money_adjunct, incentive_fees=:incentive_fees," +
                " money_relocate=:money_relocate, money_ssbcf=:money_ssbcf, money_dhyjf=:money_dhyjf,money_yxdsyjf=:money_yxdsyjf," +
                " money_ktyjf=:money_ktyjf, money_rsqyjf=:money_rsqyjf, subsidy_relocate=:subsidy_relocate,total_compensation=:total_compensation," +
                " handover_house_date=:handover_house_date,signed_date=:signed_date, leader=:leader," +
                " management_co=:management_co, geo_co=:geo_co, appraise_co=:appraise_co, demolish_co=:demolish_co, pulledown_co=:pulledown_co," +
                " prj_base_info_id=:prj_base_info_id, appraise_compensation=:appraise_compensation," +
                " archive_date=:archive_date, demolished_date=:demolished_date, audit_date=:audit_date, remarks=:remarks, created_by=:created_by," +
                " created_at=:created_at, section=:section, groups=:groups, other_file_name=:other_file_name," +
                " other_file_path=:other_file_path, lessee_name=:lessee_name, legal_name=:legal_name, land_property=:land_property," +
                " total_land_area=:total_land_area, card_land_area=:card_land_area, cog_land_area=:cog_land_area," +
                " lessee_land_area=:lessee_land_area, total_homestead_area=:total_homestead_area, card_homestead_area=:card_homestead_area," +
                " no_card_homestead_area=:no_card_homestead_area, management_homestead_area=:management_homestead_area," +
                " money_machine=:money_machine, project_cooperate_award=:project_cooperate_award, money_kd=:money_kd," +
                " money_qt=:money_qt, relocate_date=:relocate_date, handover_date=:handover_date, audit_co=:audit_co, " +
                " demolition_card_code=:demolition_card_code, demolition_year_code=:demolition_year_code, money_date=:money_date," +
                " village=:village, town=:town, before_area=:before_area, between_area=:between_area, after_area=:after_area," +
                " management_house_area=:management_house_area, field_house_area=:field_house_area, no_sign_reson=:no_sign_reson" +
                " WHERE id=:id AND map_id=:map_id";

        SqlParameterSource param = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, param);
    }

    public PrjPreallocation getById(int id) {
        String sql = "SELECT * FROM t_prj_preallocation WHERE id=?";
        List<PrjPreallocation> prjPreallocations = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(PrjPreallocation.class));
        return prjPreallocations.size() > 0 ? prjPreallocations.get(0) : null;
    }

    public boolean existByMapId(String map_Id, int project_id) {
        String sql = "SELECT * FROM t_prj_preallocation WHERE map_id= ? AND prj_base_info_id=?";
        return jdbcTemplate.query(sql, new Object[]{map_Id, project_id}, new BeanPropertyRowMapper<>(PrjPreallocation.class)).size() > 0;
    }

    public boolean existByName(String name, int project_id) {
        String sql = "SELECT * FROM t_prj_preallocation WHERE host_name= ?  AND prj_base_info_id=?";
        return jdbcTemplate.query(sql, new Object[]{name,project_id}, new BeanPropertyRowMapper<>(PrjPreallocation.class)).size() > 0;
    }

    public List<PrjPreallocation> getHostNameByMapId(String mapId) {
        mapId += "%";
        String sql = "SELECT id,map_id,host_name,section,groups,geo_co,demolish_co,pulledown_co,appraise_co" +
                " FROM t_prj_preallocation WHERE map_id LIKE ? AND status IN (10,20) LIMIT 10";
        return jdbcTemplate.query(sql, new Object[]{mapId}, new BeanPropertyRowMapper<>(PrjPreallocation.class));
    }

    public PrjPreallocation updateBasic(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET map_id=:mapId,host_name=:hostName,location=:location," +
                "id_card=:idCard,house_property=:houseProperty,house_register_num=:houseRegisterNum," +
                "other_file_name=:other_file_name,other_file_path=:other_file_path " +
                " WHERE id=:id";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, params) > 0 ? prjPreallocation : null;
    }

    public PrjPreallocation updateInHost(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET section=:section,groups=:groups,geo_co=:geoCo,appraise_co=:appraiseCo," +
                "demolish_co=:demolishCo,pulledown_co=:pulledownCo,status=20,in_host_date=now() WHERE map_id=:mapId";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, params) > 0 ? prjPreallocation : null;
    }

    public PrjPreallocation updateCompensation(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET money_adjunct=:moneyAdjunct," +
                " incentive_fees=:incentiveFees ,money_homestead=:moneyHomestead,money_new_house=:moneyNewHouse," +
                "money_relocate=:moneyRelocate,money_ssbcf=:moneySsbcf,money_dhyjf=:moneyDhyjf,money_yxdsyjf=:moneyYxdsyjf,money_ktyjf=:moneyKtyjf," +
                "money_rsqyjf=:moneyRsqyjf,subsidy_relocate=:subsidyRelocate," +
                "total_compensation=:totalCompensation,az_area=:azArea,float_az_area=:floatAzArea,one_room_area=:oneRoomArea," +
                "one_room_num=:oneRoomNum,two_room_area=:twoRoomArea,two_room_num=:twoRoomNum,three_room_area=:threeRoomArea," +
                "three_room_num=:threeRoomNum,az_room_budget_num=:azRoomBudgetNum,az_room_budget_area=:azRoomBudgetArea," +
                "budget_compensation=:budgetCompensation,budget_sub=:budgetSub,appraise_compensation=:appraiseCompensation" +
                " WHERE id=:id";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, params) > 0 ? prjPreallocation : null;
    }

    public PrjPreallocation updateChooseRoom(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET choose_room_position =:chooseRoomPosition," +
                "allocation_housing_assess_price=:allocationHousingAssessPrice, az_house_host=:azHouseHost,az_house_room=:azHouseRoom," +
                "az_house_area=:azHouseArea,az_house_host2=:azHouseHost2,az_house_room2=:azHouseRoom2," +
                "az_house_area2=:azHouseArea2,az_house_host3=:azHouseHost3,az_house_room3=:azHouseRoom3," +
                "az_house_area3=:azHouseArea3,az_house_host4=:azHouseHost4,az_house_room4=:azHouseRoom4," +
                "az_house_area4=:azHouseArea4,total_az_house_area=:totalAzHouseArea," +
                "float_az_house_area=:floatAzHouseArea,money_az_house=:moneyAzHouse,money_float_az_house=:moneyFloatAzHouse," +
                "total_az_pay_house=:totalAzPayHouse,first_calculate_sub=:firstCalculateSub,choose_room_date=now()," +
                "status=:status WHERE id=:id";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, params) > 0 ? prjPreallocation : null;
    }

    public PrjPreallocation updateArchive(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET archives_cabinet_number=:archivesCabinetNumber," +
                "archive_date=now(),status=:status WHERE id=:id";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, params) > 0 ? prjPreallocation : null;
    }

    public boolean updateToUnaudited(int id) {
        String sql = "UPDATE t_prj_preallocation SET status=20 WHERE status=21 AND id=? ";

        return jdbcTemplate.update(sql, id) > 0;
    }

    private String createSearchSql(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        String sql = " where 1=1";
//        if (prjPreallocationSearchVO.getLandStatus() != null) {
//            sql += " and plc.house_property=:landStatus ";
//        }
        if (StringUtil.isNotNullOrEmpty(prjPreallocationSearchVO.getMap_id())) {
            sql += " and plc.map_id like :map_id_param ";
        }
//        if (prjPreallocationSearchVO.getPrjBaseInfoId() != null && prjPreallocationSearchVO.getPrjBaseInfoId() != 0) {
//            sql += " and plc.prj_base_info_id = :prjBaseInfoId ";
//        }
//        if (StringUtils.isNotEmpty(prjPreallocationSearchVO.getHostName())) {
//            sql += " and plc.host_name like :hostNameParam ";
//        }
//        if (StringUtils.isNotEmpty(prjPreallocationSearchVO.getLocation())) {
//            sql += " and plc.location like :locationParam ";
//        }
//        if (StringUtils.isNotEmpty(prjPreallocationSearchVO.getIdCard())) {
//            sql += " and plc.id_card like :idCardParam ";
//        }
//        if (StringUtils.isNotEmpty(prjPreallocationSearchVO.getHostPhone())) {
//            sql += " and (plc.host_phone like :hostPhoneParam or plc.host_phone_back like :hostPhoneParam) ";
//        }
//        if (StringUtils.isNotEmpty(prjPreallocationSearchVO.getSection())) {
//            sql += " and plc.section = :section ";
//        }
//        if (StringUtils.isNotEmpty(prjPreallocationSearchVO.getGroups())) {
//            sql += " and plc.groups = :groups ";
//        }
//        if (StringUtils.isNotEmpty(prjPreallocationSearchVO.getSelectHouseCode())) {
//            sql += " and plc.select_house_code like :selectHouseCodeParam";
//        }
//        if (prjPreallocationSearchVO.getStatus() != null) {
//            sql += " and plc.status = :status";
//        }
//        if (prjPreallocationSearchVO.getStatusList() != null && prjPreallocationSearchVO.getStatusList().size() > 0) {
//            sql += " and plc.status in (:statusList)";
//        }
//        if(prjPreallocationSearchVO.getIs_section() != null && prjPreallocationSearchVO.getIs_section() == 1){
//            sql +=" and section in(select name from prj_sections where id in(select section_id from user_sections where user_id=:userId))";
//        }
//        if(prjPreallocationSearchVO.getIs_group() != null && prjPreallocationSearchVO.getIs_group() == 1){
//            sql +=" and groups in(select name from prj_groups where id in(select group_id from user_groups where user_id=:userId))";
//        }
//        if (prjPreallocationSearchVO.getExportStatus() != null) {
//            switch (prjPreallocationSearchVO.getExportStatus()) {
//                case 10:
//                    sql += " and plc.status < 30";
//                    break;
//                case 20:
//                    sql += " and plc.status >= 30";
//                    break;
//                default:
//                    break;
//            }
//        }
        return sql;
    }
}
