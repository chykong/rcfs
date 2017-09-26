package com.balance.prj.dao;


import com.balance.api.dto.GisDTO;
import com.balance.api.dto.HouseholdersDTO;
import com.balance.api.dto.HouseholdersDetailDTO;
import com.balance.common.vo.ComboboxVO;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import com.balance.util.string.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by dsy on 2017/1/7.
 * 拆迁户信息 Dao
 */
@Repository
public class PrjPreallocationDao extends BaseDao<PrjPreallocation, PrjPreallocationSearchVO> {

    public List<PrjPreallocation> findAll(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        String sql = "select plc.* from t_prj_preallocation plc";
        sql += createSearchSql(prjPreallocationSearchVO);
        sql += " order by plc.id asc,status";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocationSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjPreallocation.class));
    }

    /**
     * 分页列表
     *
     * @param prjPreallocationSearchVO
     * @return
     */
    public List<HouseholdersDTO> list(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        String sql = "select plc.id,plc.map_id,plc.cog_land_area ,plc.host_name from t_prj_preallocation plc ";
        sql += createSearchSql(prjPreallocationSearchVO);
        sql += " order by  CONVERT(host_name USING gbk)";
        sql = PageUtil.createMysqlPageSql(sql, prjPreallocationSearchVO.getPageIndex(), prjPreallocationSearchVO.getPageSize());
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocationSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(HouseholdersDTO.class));
    }

    public List<GisDTO> listForGis(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        String sql = "select plc.* from t_prj_preallocation plc";
        sql += createSearchSql(prjPreallocationSearchVO);
        sql += " order by  CONVERT(host_name USING gbk)";
        sql = PageUtil.createMysqlPageSql(sql, prjPreallocationSearchVO.getPageIndex(), prjPreallocationSearchVO.getPageSize());
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocationSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(GisDTO.class));
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
                " money_machine, project_cooperate_award, money_kd, money_qt, relocate_date, audit_co, " +
                "demolition_card_code, demolition_year_code, money_date, village, town, before_area, between_area, after_area," +
                " management_house_area, field_house_area, no_sign_reason,in_host_date,status,total_yjf,appraise_money," +
                "float_people,car_num,rmgl_num,rqgl_num,zqgl_num,jzzz_num,scqg_num,qx_num,fz_num,wl_num,qt_num,scattered_coal,last_area,content) " +
                "VALUES(:map_id, :host_name, :location, :id_card, :house_property," +
                " :money_homestead, :money_adjunct, :incentive_fees, :money_relocate, :money_ssbcf, :money_dhyjf,:money_yxdsyjf," +
                " :money_ktyjf, :money_rsqyjf, :subsidy_relocate,:total_compensation, :handover_house_date,:signed_date, :leader," +
                " :management_co, :geo_co, :appraise_co, :demolish_co, :pulledown_co, :prj_base_info_id, :appraise_compensation," +
                " :archive_date, :demolished_date, :audit_date, :remarks, :created_by, now(), :section, :groups, :other_file_name," +
                " :other_file_path, :lessee_name, :legal_name, :land_property, :total_land_area, :card_land_area, :cog_land_area," +
                " :lessee_land_area, :total_homestead_area, :card_homestead_area, :no_card_homestead_area, :management_homestead_area," +
                " :money_machine, :project_cooperate_award, :money_kd, :money_qt, :relocate_date, :audit_co, " +
                " :demolition_card_code, :demolition_year_code, :money_date, :village, :town, :before_area, :between_area, :after_area," +
                " :management_house_area, :field_house_area, :no_sign_reason,:in_host_date,:status,:total_yjf,:appraise_money," +
                ":float_people,:car_num,:rmgl_num,:rqgl_num,:zqgl_num,:jzzz_num,:scqg_num,:qx_num,:fz_num,:wl_num,:qt_num,:scattered_coal,:last_area,:content)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, param);
    }

    public int update(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET  map_id=:map_id, host_name=:host_name, location=:location, id_card=:id_card, house_property=:house_property," +
                " money_homestead=:money_homestead, money_adjunct=:money_adjunct, incentive_fees=:incentive_fees," +
                " money_relocate=:money_relocate, money_ssbcf=:money_ssbcf, money_dhyjf=:money_dhyjf,money_yxdsyjf=:money_yxdsyjf," +
                " money_ktyjf=:money_ktyjf, money_rsqyjf=:money_rsqyjf, subsidy_relocate=:subsidy_relocate,total_compensation=:total_compensation," +
                " handover_house_date=:handover_house_date,signed_date=:signed_date, leader=:leader,in_host_date=:in_host_date," +
                " management_co=:management_co, geo_co=:geo_co, appraise_co=:appraise_co, demolish_co=:demolish_co, pulledown_co=:pulledown_co," +
                " prj_base_info_id=:prj_base_info_id, appraise_compensation=:appraise_compensation," +
                " archive_date=:archive_date, demolished_date=:demolished_date, audit_date=:audit_date, remarks=:remarks, section=:section, groups=:groups, other_file_name=:other_file_name," +
                " other_file_path=:other_file_path, lessee_name=:lessee_name, legal_name=:legal_name, land_property=:land_property," +
                " total_land_area=:total_land_area, card_land_area=:card_land_area, cog_land_area=:cog_land_area," +
                " lessee_land_area=:lessee_land_area, total_homestead_area=:total_homestead_area, card_homestead_area=:card_homestead_area," +
                " no_card_homestead_area=:no_card_homestead_area, management_homestead_area=:management_homestead_area," +
                " money_machine=:money_machine, project_cooperate_award=:project_cooperate_award, money_kd=:money_kd," +
                " money_qt=:money_qt, relocate_date=:relocate_date, audit_co=:audit_co,total_yjf=:total_yjf,appraise_money=:appraise_money, " +
                " demolition_card_code=:demolition_card_code, demolition_year_code=:demolition_year_code, money_date=:money_date," +
                " village=:village, town=:town, before_area=:before_area, between_area=:between_area, after_area=:after_area," +
                " management_house_area=:management_house_area, field_house_area=:field_house_area, no_sign_reason=:no_sign_reason,status=:status," +
                " float_people=:float_people,car_num=:car_num,rmgl_num=:rmgl_num,rqgl_num=:rqgl_num,zqgl_num=:zqgl_num,jzzz_num=:jzzz_num" +
                ",scqg_num=:scqg_num,qx_num=:qx_num,fz_num=:fz_num,wl_num=:wl_num,qt_num=:qt_num,scattered_coal=:scattered_coal,last_area=:last_area,content=:content WHERE id=:id ";

        SqlParameterSource param = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, param);
    }

    public int updateBasic(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET  host_name=:host_name, location=:location, id_card=:id_card," +
                " leader=:leader, management_co=:management_co, geo_co=:geo_co, appraise_co=:appraise_co," +
                " demolish_co=:demolish_co, pulledown_co=:pulledown_co, prj_base_info_id=:prj_base_info_id," +
                " remarks=:remarks, section=:section, groups=:groups, lessee_name=:lessee_name, legal_name=:legal_name," +
                " land_property=:land_property," +
                " total_land_area=:total_land_area, card_land_area=:card_land_area, cog_land_area=:cog_land_area," +
                " lessee_land_area=:lessee_land_area, total_homestead_area=:total_homestead_area, card_homestead_area=:card_homestead_area," +
                " no_card_homestead_area=:no_card_homestead_area, management_homestead_area=:management_homestead_area," +
                " audit_co=:audit_co, demolition_card_code=:demolition_card_code, demolition_year_code=:demolition_year_code," +
                " relocate_date=:relocate_date,status=:status," +
                " village=:village, town=:town, before_area=:before_area, between_area=:between_area, after_area=:after_area, last_area=:last_area" +
                " ,demolish_person=:demolish_person,appraise_person=:appraise_person,in_host_date=:in_host_date," +
                "float_people=:float_people,car_num=:car_num,rmgl_num=:rmgl_num,rqgl_num=:rqgl_num,zqgl_num=:zqgl_num,jzzz_num=:jzzz_num" +
                ",scqg_num=:scqg_num,qx_num=:qx_num,fz_num=:fz_num,wl_num=:wl_num,qt_num=:qt_num,scattered_coal=:scattered_coal WHERE map_id=:map_id";

        SqlParameterSource param = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, param);
    }

    public int updateCompensation(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET  money_homestead=:money_homestead, money_adjunct=:money_adjunct," +
                " money_machine=:money_machine, appraise_compensation=:appraise_compensation, " +
                " money_ssbcf=:money_ssbcf, project_cooperate_award=:project_cooperate_award, money_relocate=:money_relocate," +
                " money_dhyjf=:money_dhyjf, money_ktyjf=:money_ktyjf, money_kd=:money_kd, money_yxdsyjf=:money_yxdsyjf," +
                " money_rsqyjf=:money_rsqyjf, money_qt=:money_qt," +
                " subsidy_relocate=:subsidy_relocate, total_compensation=:total_compensation, signed_date=:signed_date," +
                " handover_house_date=:handover_house_date, demolished_date=:demolished_date," +
                " audit_date=:audit_date, money_date=:money_date, no_sign_reason=:no_sign_reason,status=:status" +
                " WHERE map_id=:map_id";

        SqlParameterSource param = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, param);
    }

    public PrjPreallocation getById(int id) {
        String sql = "SELECT * FROM t_prj_preallocation WHERE id=?";
        List<PrjPreallocation> prjPreallocations = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(PrjPreallocation.class));
        return prjPreallocations.size() > 0 ? prjPreallocations.get(0) : null;
    }

    public PrjPreallocation getByMapId(String map_id) {
        String sql = "SELECT * FROM t_prj_preallocation WHERE map_id=?";
        List<PrjPreallocation> prjPreallocations = jdbcTemplate.query(sql, new Object[]{map_id}, new BeanPropertyRowMapper<>(PrjPreallocation.class));
        return prjPreallocations.size() > 0 ? prjPreallocations.get(0) : null;
    }

    public boolean existByMapId(String map_Id, int project_id) {
        String sql = "SELECT * FROM t_prj_preallocation WHERE map_id= ? AND prj_base_info_id=?";
        return jdbcTemplate.query(sql, new Object[]{map_Id, project_id}, new BeanPropertyRowMapper<>(PrjPreallocation.class)).size() > 0;
    }

    public boolean existByName(String name, int project_id) {
        String sql = "SELECT * FROM t_prj_preallocation WHERE host_name= ?  AND prj_base_info_id=?";
        return jdbcTemplate.query(sql, new Object[]{name, project_id}, new BeanPropertyRowMapper<>(PrjPreallocation.class)).size() > 0;
    }

    public List<PrjPreallocation> getHostNameByMapId(String mapId) {
        mapId += "%";
        String sql = "SELECT id,map_id,host_name,section,groups,geo_co,demolish_co,pulledown_co,appraise_co" +
                " FROM t_prj_preallocation WHERE map_id LIKE ? AND status IN (10,20) LIMIT 10";
        return jdbcTemplate.query(sql, new Object[]{mapId}, new BeanPropertyRowMapper<>(PrjPreallocation.class));
    }

    public PrjPreallocation updateInHost(PrjPreallocation prjPreallocation) {
        String sql = "UPDATE t_prj_preallocation SET section=:section,groups=:groups,geo_co=:geoCo,appraise_co=:appraiseCo," +
                "demolish_co=:demolishCo,pulledown_co=:pulledownCo,status=20,in_host_date=now() WHERE map_id=:mapId";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallocation);
        return getNamedParameterJdbcTemplate().update(sql, params) > 0 ? prjPreallocation : null;
    }

    /**
     * 删除
     *
     * @param id
     */
    public int delete(int id) {
        String sql = "DELETE FROM t_prj_preallocation   WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    public int deleteBatch(List<String> map_ids, int prj_base_info_id) {
        String sql = "DELETE FROM t_prj_preallocation   WHERE map_id IN (:map_ids) AND prj_base_info_id=:prj_base_info_id";
        Map<String, Object> param = new HashedMap();
        param.put("map_ids", map_ids);
        param.put("prj_base_info_id", prj_base_info_id);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, param);
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
        if (prjPreallocationSearchVO.getLand_status() != null) {
            switch (prjPreallocationSearchVO.getLand_status()) {
                case 1:
                    sql += " and plc.land_property='国有'";
                    break;
                case 2:
                    sql += " and plc.land_property='集体'";
                    break;
                default:
                    sql += "";
                    break;
            }
        }
        if (prjPreallocationSearchVO.getHouse_status() != null) {
            switch (prjPreallocationSearchVO.getHouse_status()) {
                case 1:
                    sql += " and plc.house_property='住宅'";
                    break;
                case 2:
                    sql += " and plc.house_property='非住宅'";
                    break;
                default:
                    sql += "";
                    break;
            }
        }
        if (prjPreallocationSearchVO.getSearch_date() != null && prjPreallocationSearchVO.getSearch_blank() != null) {
            sql += " and (plc." + getSearchField(prjPreallocationSearchVO.getSearch_date());
            if (prjPreallocationSearchVO.getSearch_blank() == 0) {
                sql += " is null or plc." + getSearchField(prjPreallocationSearchVO.getSearch_date()) + "='')";
            } else {
                sql += " is not null and plc." + getSearchField(prjPreallocationSearchVO.getSearch_date()) + "!=''";
            }
        }
        if (StringUtil.isNotNullOrEmpty(prjPreallocationSearchVO.getMap_id())) {
            sql += " and plc.map_id like :map_id_param ";
        }
        if (prjPreallocationSearchVO.getBase_info_id() != null && prjPreallocationSearchVO.getBase_info_id() != 0) {
            sql += " and plc.prj_base_info_id = :base_info_id ";
        }
        if (StringUtil.isNotNullOrEmpty(prjPreallocationSearchVO.getLocation())) {
            sql += " and plc.location like :location_param ";
        }
        if (StringUtil.isNotNullOrEmpty(prjPreallocationSearchVO.getHost_name())) {
            sql += " and plc.host_name like :host_name_param ";
        }
        if (StringUtil.isNotNullOrEmpty(prjPreallocationSearchVO.getTown())) {
            sql += " and plc.town = :town ";
        }
        if (StringUtil.isNotNullOrEmpty(prjPreallocationSearchVO.getVillage())) {
            sql += " and plc.village = :village ";
        }
        if (StringUtil.isNotNullOrEmpty(prjPreallocationSearchVO.getSection())) {
            sql += " and plc.section = :section ";
        }
        if (StringUtil.isNotNullOrEmpty(prjPreallocationSearchVO.getGroups())) {
            sql += " and plc.groups = :groups ";
        }
        if (prjPreallocationSearchVO.getStatus() != null) {
            sql += " and plc.status = :status";
        }
        if (prjPreallocationSearchVO.getStatusList() != null && prjPreallocationSearchVO.getStatusList().size() > 0) {
            sql += " and plc.status in (:statusList)";
        }
        if (prjPreallocationSearchVO.getIs_section() != null && prjPreallocationSearchVO.getIs_section() == 1) {
            sql += " and section in(select name from prj_sections where id in(select section_id from user_sections where user_id=:userId))";
        }
        if (prjPreallocationSearchVO.getIs_group() != null && prjPreallocationSearchVO.getIs_group() == 1) {
            sql += " and groups in(select name from prj_groups where id in(select group_id from user_groups where user_id=:userId))";
        }
        if (prjPreallocationSearchVO.getExport_status() != null) {
            switch (prjPreallocationSearchVO.getExport_status()) {
                case 10:
                    sql += " and plc.status < 30";
                    break;
                case 20:
                    sql += " and plc.status >= 30";
                    break;
                default:
                    break;
            }
        }

        if (StringUtil.isNotNullOrEmpty(prjPreallocationSearchVO.getTerm())) {
            prjPreallocationSearchVO.setTerm("%" + prjPreallocationSearchVO.getTerm() + "%");
            sql += " and (plc.host_name like :term or map_id like :term)";
        }

        if (prjPreallocationSearchVO.getContent() != null) {
            switch (prjPreallocationSearchVO.getContent()) {
                case 1:
                    sql += " and plc.content != ''  and plc.content is not null";
                    break;
                case 2:
                    sql += " and (plc.content ='' or plc.content is null)";
                    break;
                default:
                    break;
            }
        }
        return sql;
    }

    private String getSearchField(int search_type) {
        String field = "";
        if (search_type == 1) {
            field = "in_host_date";
        } else if (search_type == 2) {
            field = "signed_date";
        } else if (search_type == 3) {
            field = "handover_house_date";
        } else if (search_type == 4) {
            field = "demolished_date";
        } else if (search_type == 5) {
            field = "audit_date";
        } else if (search_type == 6) {
            field = "money_date";
        }
        return field;
    }

    public int updateArchive(String map_id, String host_name, String archives_code, int project_id, int status, String file_path, String file_name) {
        String sql = "UPDATE t_prj_preallocation SET archives_cabinet_number=?,status=?,archive_date=now(),archive_file_path=?,archive_file_name=? WHERE map_id=? AND host_name=? AND prj_base_info_id=?";
        return jdbcTemplate.update(sql, archives_code, status, file_path, file_name, map_id, host_name, project_id);
    }

    public List<ComboboxVO> getTown(int prj_base_info_id) {
        String sql = "SELECT  DISTINCT town value,town content FROM t_prj_preallocation WHERE prj_base_info_id = ? ";
        return jdbcTemplate.query(sql, new Object[]{prj_base_info_id}, new BeanPropertyRowMapper<>(ComboboxVO.class));
    }

    public List<ComboboxVO> getVillage(int prj_base_info_id) {
        String sql = "SELECT village value,village content FROM t_prj_preallocation WHERE prj_base_info_id = ? GROUP BY village ";
        return jdbcTemplate.query(sql, new Object[]{prj_base_info_id}, new BeanPropertyRowMapper<>(ComboboxVO.class));
    }

    public List<ComboboxVO> getVillageByTown(int prj_base_info_id, String town) {
        String sql = "SELECT village value,village content FROM t_prj_preallocation WHERE prj_base_info_id = ? AND town=? GROUP BY village ";
        return jdbcTemplate.query(sql, new Object[]{prj_base_info_id, town}, new BeanPropertyRowMapper<>(ComboboxVO.class));
    }

    /**
     * 接口里面调用，获取拆迁详细
     *
     * @param id
     * @return
     */
    public HouseholdersDetailDTO getByIdInApi(int id) {
        String sql = "SELECT id,host_name,cog_land_area,map_id,location,status,total_homestead_area,remarks,before_area,between_area,after_area,no_sign_reason,appraise_co,demolish_co,groups  FROM t_prj_preallocation WHERE id=?";
        List<HouseholdersDetailDTO> prjPreallocations = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(HouseholdersDetailDTO.class));
        return prjPreallocations.size() > 0 ? prjPreallocations.get(0) : null;
    }

    public List<PrjPreallocation> findAllForExport(PrjPreallocationSearchVO preallocationSearchVO) {
        String sql = "select plc.* from t_prj_preallocation plc";
        sql += createSearchSql(preallocationSearchVO);
        sql += " order by plc.id asc,status";
        SqlParameterSource params = new BeanPropertySqlParameterSource(preallocationSearchVO);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(PrjPreallocation.class));
    }
}
