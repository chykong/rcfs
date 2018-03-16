package com.balance.base.dao;

import com.balance.base.model.BasePlacement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by  杨康康 on 2017/8/29.
 * 安置房概况  dao
 */
@Repository
public class BasePlacementDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(BasePlacement basePlacement) {
        String sql = " INSERT  into t_base_placement(content,attach_name,attach_path,prj_base_info_id) values(:content,:attach_name,:attach_path,:prj_base_info_id)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(basePlacement);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, param);
    }

    public int update(BasePlacement basePlacement) {
        String sql = " UPDATE  t_base_placement SET content=:content,attach_name=:attach_name,attach_path=:attach_path  WHERE prj_base_info_id=:prj_base_info_id";
        SqlParameterSource param = new BeanPropertySqlParameterSource(basePlacement);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, param);
    }

    public BasePlacement get(int prj_base_info_id) {
        String sql = " SELECT * FROM t_base_placement WHERE prj_base_info_id=? ";
        List<BasePlacement> basePlacements = jdbcTemplate.query(sql, new Object[]{prj_base_info_id}, new BeanPropertyRowMapper<>(BasePlacement.class));
        return basePlacements.size() > 0 ? basePlacements.get(0) : null;
    }


}
