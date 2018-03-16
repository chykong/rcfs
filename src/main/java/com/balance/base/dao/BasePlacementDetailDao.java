package com.balance.base.dao;

import com.balance.base.model.BaseHouse;
import com.balance.base.model.BasePlacementDetail;
import com.balance.base.model.BasePlacementFloor;
import com.balance.base.vo.BasePlacementDetailSearchVO;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dsy on 2017/8/23.
 * 安置房详细  Dao
 */
@Repository
public class BasePlacementDetailDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<BasePlacementDetail> listDetail(BasePlacementDetailSearchVO placementDetailSearchVO) {
        String sql = " SELECT t.*,(select name from t_base_house where id=t.house_type) house_type_name FROM t_base_placementdetail t WHERE 1=1";
        sql += createSql(placementDetailSearchVO);

        SqlParameterSource params = new BeanPropertySqlParameterSource(placementDetailSearchVO);
        return new NamedParameterJdbcTemplate(jdbcTemplate).query(sql, params, new BeanPropertyRowMapper<>(BasePlacementDetail.class));
    }

    private String createSql(BasePlacementDetailSearchVO placementDetailSearchVO) {
        String sql = " ";
        if (placementDetailSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id = :prj_base_info_id";
        }
        if (StringUtil.isNotNullOrEmpty(placementDetailSearchVO.getBuild_code())) {
            sql += " and build_code=:build_code";
        }
        if (StringUtil.isNotNullOrEmpty(placementDetailSearchVO.getMap_code())) {
            sql += " and map_code like :map_code_param";
        }
        return sql;
    }

    public int add(BasePlacementDetail basePlacementDetail) {
        String sql = " INSERT INTO t_base_placementdetail(build_code,map_code,house_type,area,bool_tt,bool_mc,bool_mw,prj_base_info_id,floor,money,note)" +
                " VALUES(:build_code,:map_code,:house_type,:area,:bool_tt,:bool_mc,:bool_mw,:prj_base_info_id,:floor,:money,:note)  ";
        SqlParameterSource param = new BeanPropertySqlParameterSource(basePlacementDetail);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, param);
    }

    public int batchAdd(List<BasePlacementDetail> basePlacementDetails) {
        String sql = "INSERT INTO t_base_placementdetail(build_code,map_code,house_type,area,bool_tt,bool_mc,bool_mw,prj_base_info_id,floor,money,note)" +
                " VALUES(:build_code,:map_code,:house_type,:area,:bool_tt,:bool_mc,:bool_mw,:prj_base_info_id,:floor,:money,:note) ";

        Map<String, Object>[] paramArray = new HashMap[basePlacementDetails.size()];
        for (int i = 0; i < basePlacementDetails.size(); i++) {
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("prj_base_info_id", basePlacementDetails.get(i).getPrj_base_info_id());
            mapParams.put("build_code", basePlacementDetails.get(i).getBuild_code());
            mapParams.put("map_code", basePlacementDetails.get(i).getMap_code());
            mapParams.put("house_type", basePlacementDetails.get(i).getHouse_type());
            mapParams.put("area", basePlacementDetails.get(i).getArea());
            mapParams.put("bool_tt", basePlacementDetails.get(i).getBool_tt());
            mapParams.put("bool_mc", basePlacementDetails.get(i).getBool_mc());
            mapParams.put("bool_mw", basePlacementDetails.get(i).getBool_mw());
            mapParams.put("floor", basePlacementDetails.get(i).getFloor());
            mapParams.put("money", basePlacementDetails.get(i).getMoney());
            mapParams.put("note", basePlacementDetails.get(i).getNote());
            paramArray[i] = mapParams;
        }
        int[] result = new NamedParameterJdbcTemplate(jdbcTemplate).batchUpdate(sql, paramArray);
        return result.length == basePlacementDetails.size() ? 1 : 0;
    }

    public int update(BasePlacementDetail basePlacementDetail) {
        String sql = " UPDATE  t_base_placementdetail SET build_code=:build_code,map_code=:map_code,house_type=:house_type," +
                "area=:area,bool_tt=:bool_tt,bool_mc=:bool_mc,bool_mw=:bool_mw,floor=:floor,money=:money,note=:note  WHERE id=:id";
        SqlParameterSource param = new BeanPropertySqlParameterSource(basePlacementDetail);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, param);
    }

    public BasePlacementDetail get(int id) {
        String sql = " SELECT t.*,(SELECT name FROM t_base_house WHERE id=t.house_type) house_type_name  FROM t_base_placementdetail t WHERE id= ?";
        List<BasePlacementDetail> basePlacements = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(BasePlacementDetail.class));
        return basePlacements.size() > 0 ? basePlacements.get(0) : null;
    }

    public BasePlacementDetail getByBuildAndMap(String build_code, String map_code) {
        String sql = " SELECT t.*,(SELECT name FROM t_base_house WHERE id=t.house_type) house_type_name  FROM t_base_placementdetail t WHERE build_code= ? AND map_code=?";
        List<BasePlacementDetail> basePlacements = jdbcTemplate.query(sql, new Object[]{build_code, map_code}, new BeanPropertyRowMapper<>(BasePlacementDetail.class));
        return basePlacements.size() > 0 ? basePlacements.get(0) : null;
    }

    public int delete(int id) {
        String sql = "DELETE FROM t_base_placementdetail WHERE id =?";
        return jdbcTemplate.update(sql, id);
    }

    //批量删除
    public int delete(List<Integer> ids) {
        String sql = "DELETE FROM t_base_placementdetail WHERE id IN(:ids)";
        Map<String, List<Integer>> params = new HashMap<>();
        params.put("ids", ids);
        return jdbcTemplate.update(sql, params);
    }

    public List<BaseHouse> houseList(int prjBaseInfoId) {
        String sql = "SELECT  *  FROM  t_base_house  WHERE prj_base_info_id=?";

        return jdbcTemplate.query(sql, new Object[]{prjBaseInfoId}, BeanPropertyRowMapper.newInstance(BaseHouse.class));
    }

    public boolean existsHouse(String name, int project_id) {
        String sql = "SELECT  count(*)  FROM  t_base_house  WHERE prj_base_info_id=? AND name=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{project_id, name}, Integer.class) > 0;
    }

    public int getHouseId(String name, int project_id) {
        String sql = "SELECT  id  FROM  t_base_house  WHERE prj_base_info_id=? AND name=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{project_id, name}, Integer.class);
    }


    public int countAll(int prj_base_info_id) {
        String sql = "SELECT count(*) FROM t_base_placementdetail WHERE prj_base_info_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{prj_base_info_id}, Integer.class);
    }

    public int countSelect(int prj_base_info_id) {
        String sql = "SELECT count(*) FROM t_base_placementdetail WHERE prj_base_info_id=? AND is_select=1";
        return jdbcTemplate.queryForObject(sql, new Object[]{prj_base_info_id}, Integer.class);
    }

    public List<String> getBuildCode(int prj_base_info_id) {
        List<String> buildCodeList = new ArrayList<>();
        String sql = "SELECT build_code FROM t_base_placementdetail WHERE prj_base_info_id=? GROUP BY build_code order by build_code desc";
        List<BasePlacementDetail> placementDetails = jdbcTemplate.query(sql, new Object[]{prj_base_info_id}, new BeanPropertyRowMapper<>(BasePlacementDetail.class));
        for (BasePlacementDetail placementDetail : placementDetails) {
            buildCodeList.add(placementDetail.getBuild_code());
        }
        return buildCodeList;
    }

    public List<BasePlacementFloor> getFloor(int prj_base_info_id) {
        String sql = "SELECT build_code,floor FROM t_base_placementdetail WHERE prj_base_info_id=? GROUP BY build_code,floor ORDER BY build_code desc,floor desc";
        return jdbcTemplate.query(sql, new Object[]{prj_base_info_id}, new BeanPropertyRowMapper<>(BasePlacementFloor.class));
    }

    public List<BasePlacementDetail> getAll(int prj_base_info_id) {
        String sql = "SELECT t.*,(select house_image from t_base_house where id=t.house_type) house_url FROM t_base_placementdetail t WHERE prj_base_info_id=?";
        return jdbcTemplate.query(sql, new Object[]{prj_base_info_id}, new BeanPropertyRowMapper<>(BasePlacementDetail.class));
    }

    public int updateSelect(String map_id,int id){
        String sql = "update t_base_placementdetail set map_id=?,selected_at=now(),is_select=1 WHERE id =?";
        return jdbcTemplate.update(sql, map_id,id);
    }
}
