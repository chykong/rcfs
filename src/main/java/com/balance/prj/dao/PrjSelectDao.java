package com.balance.prj.dao;

import com.balance.prj.model.PrjSelected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目简报
 *
 * @author 刘凯
 * @date 2017年6月5日
 */
@Repository
public class PrjSelectDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PrjSelected getByMapId(String mapId, int projectId, String houseType, String landType) {
        String sql = "SELECT * FROM t_prj_selected t WHERE map_id=? and project_id=? AND house_type=? AND land_type=?";
        List<PrjSelected> selected = jdbcTemplate.query(sql, new Object[]{mapId,projectId, houseType, landType}, new BeanPropertyRowMapper<>(PrjSelected.class));
        return selected.size() > 0 ? selected.get(0) : null;
    }

    public int getMaxCode(int projectId, String houseType, String landType) {
        String sql = "SELECT ifnull(max(selected_code),0) FROM t_prj_selected t WHERE project_id=? AND house_type=? AND land_type=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{projectId, houseType, landType}, Integer.class);
    }


    /**
     * 新增
     *
     * @param prjSelected 实例
     * @return 成功1 失败0
     */
    public int add(PrjSelected prjSelected) {
        String sql = "INSERT INTO t_prj_selected(map_id, selected_code, project_id, house_type, land_type, selected_at)" +
                "VALUES(:map_id, :selected_code, :project_id, :house_type, :land_type, now())";
        SqlParameterSource param = new BeanPropertySqlParameterSource(prjSelected);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, param);
    }

    /**
     * 根据id查询prjBrief
     */
    public int update(PrjSelected prjSelected) {
        String sql = "UPDATE t_prj_selected SET selected_code=:selected_code,selected_at=now() where id=:id and map_id=:map_id and project_id=:project_id " +
                "AND house_type=:house_type AND land_type=:land_type";
        SqlParameterSource param = new BeanPropertySqlParameterSource(prjSelected);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, param);
    }

    public int updateType(PrjSelected prjSelected) {
        String sql = "UPDATE t_prj_selected SET compensation_type=:compensation_type,selected_at=now() where id=:id and map_id=:map_id and project_id=:project_id " +
                "AND house_type=:house_type AND land_type=:land_type";
        SqlParameterSource param = new BeanPropertySqlParameterSource(prjSelected);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, param);
    }

}
