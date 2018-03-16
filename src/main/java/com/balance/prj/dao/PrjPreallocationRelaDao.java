package com.balance.prj.dao;

import com.balance.prj.model.PreallocationRela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dsy on 2017/12/7.
 * 承租人Dao
 */
@Repository
public class PrjPreallocationRelaDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PreallocationRela> list(String map_id, int prj_base_info_id) {
        String sql = "SELECT * FROM t_prj_preallocationrela WHERE map_id=? AND prj_base_info_id=?";
        return jdbcTemplate.query(sql, new Object[]{map_id, prj_base_info_id}, new BeanPropertyRowMapper<>(PreallocationRela.class));
    }

    public int add(PreallocationRela preallocationRelas) {
        String sql = "INSERT INTO t_prj_preallocationrela(map_id, name, birthday, host_relation, householder_relation, note, " +
                "created_at, created_by, prj_base_info_id, id_no, telphone, job_company, " +
                "marriage_status, cog_status, show_status, az_area, sex, is_sign, other_card_no)" +
                " VALUES(:map_id, :name, :birthday, :host_relation, :householder_relation, :note, " +
                "now(), :created_by, :prj_base_info_id, :id_no, :telphone, :job_company, " +
                ":marriage_status, :cog_status, :show_status, :az_area, :sex, :is_sign, :other_card_no)";
        SqlParameterSource params = new BeanPropertySqlParameterSource(preallocationRelas);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, params);
    }

    public int batchAdd(List<PreallocationRela> preallocationRelas) {
        String sql = "INSERT INTO t_prj_preallocationrela(map_id, name, birthday, host_relation, householder_relation, note, " +
                "created_at, created_by, prj_base_info_id, id_no, telphone, job_company, " +
                "marriage_status, cog_status, show_status, az_area, sex, is_sign, other_card_no)" +
                " VALUES(:map_id, :name, :birthday, :host_relation, :householder_relation, :note, " +
                "now(), :created_by, :prj_base_info_id, :id_no, :telphone, :job_company, " +
                ":marriage_status, :cog_status, :show_status, :az_area, :sex, :is_sign, :other_card_no)";
        Map<String, Object>[] paramArray = new HashMap[preallocationRelas.size()];
        for (int i = 0; i < preallocationRelas.size(); i++) {
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("map_id", preallocationRelas.get(i).getMap_id());
            mapParams.put("name", preallocationRelas.get(i).getName());
            mapParams.put("birthday", preallocationRelas.get(i).getBirthday());
            mapParams.put("host_relation", preallocationRelas.get(i).getHost_relation());
            mapParams.put("householder_relation", preallocationRelas.get(i).getHouseholder_relation());
            mapParams.put("note", preallocationRelas.get(i).getNote());
            mapParams.put("created_by", preallocationRelas.get(i).getCreated_by());
            mapParams.put("prj_base_info_id", preallocationRelas.get(i).getPrj_base_info_id());
            mapParams.put("id_no", preallocationRelas.get(i).getId_no());
            mapParams.put("telphone", preallocationRelas.get(i).getTelphone());
            mapParams.put("job_company", preallocationRelas.get(i).getJob_company());
            mapParams.put("marriage_status", preallocationRelas.get(i).getMarriage_status());
            mapParams.put("cog_status", preallocationRelas.get(i).getCog_status());
            mapParams.put("show_status", preallocationRelas.get(i).getShow_status());
            mapParams.put("az_area", preallocationRelas.get(i).getAz_area());
            mapParams.put("sex", preallocationRelas.get(i).getSex());
            mapParams.put("is_sign", preallocationRelas.get(i).getIs_sign());
            mapParams.put("other_card_no", preallocationRelas.get(i).getOther_card_no());
            paramArray[i] = mapParams;
        }
        int[] result = new NamedParameterJdbcTemplate(jdbcTemplate).batchUpdate(sql, paramArray);
        return result.length == preallocationRelas.size() ? 1 : 0;
    }

    public int delete(String map_id, int prj_base_info_id) {
        String sql = "DELETE  FROM t_prj_preallocationrela WHERE map_id=? AND prj_base_info_id=?";
        return jdbcTemplate.update(sql, map_id, prj_base_info_id);
    }

    public int count(String map_id, int prj_base_info_id) {
        String sql = "SELECT count(*) FROM t_prj_preallocationrela WHERE map_id=? AND prj_base_info_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{map_id, prj_base_info_id}, Integer.class);
    }
}
