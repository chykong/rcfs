package com.balance.prj.dao;

import com.balance.prj.model.PrjPreallAttach;
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
 * Created by dsy on 2017/8/2.
 * 拆除腾退人图片附件 Dao
 */
@Repository
public class PrjPreallAttachDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PrjPreallAttach> list(String map_id) {
        String sql = "SELECT * FROM t_prj_preall_attach WHERE map_id = ?";
        return jdbcTemplate.query(sql, new Object[]{map_id}, new BeanPropertyRowMapper<>(PrjPreallAttach.class));
    }

    public List<PrjPreallAttach> listForType(String map_id, int type) {
        String sql = "SELECT * FROM t_prj_preall_attach WHERE map_id = ? AND type=? limit 2";
        return jdbcTemplate.query(sql, new Object[]{map_id, type}, new BeanPropertyRowMapper<>(PrjPreallAttach.class));
    }

    public int add(PrjPreallAttach prjPreallAttach) {
        String sql = "INSERT INTO t_prj_preall_attach(map_id, file_name, file_path, created_at, created_by,type) " +
                "VALUES(:map_id, :file_name, :file_path, now(), :created_by,:type)";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjPreallAttach);
        return new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, params);
    }

    public int batchAdd(List<PrjPreallAttach> prjPreallAttachs) {
        String sql = "INSERT INTO t_prj_preall_attach(map_id, file_name, file_path, created_at, created_by,type) " +
                "VALUES(:map_id, :file_name, :file_path, now(), :created_by,:type)";

        Map<String, Object>[] paramArray = new HashMap[prjPreallAttachs.size()];
        for (int i = 0; i < prjPreallAttachs.size(); i++) {
            Map<String, Object> mapParams = new HashMap<>();
            mapParams.put("map_id", prjPreallAttachs.get(i).getMap_id());
            mapParams.put("file_name", prjPreallAttachs.get(i).getFile_name());
            mapParams.put("file_path", prjPreallAttachs.get(i).getFile_path());
            mapParams.put("created_by", prjPreallAttachs.get(i).getCreated_by());
            mapParams.put("type", prjPreallAttachs.get(i).getType());
            paramArray[i] = mapParams;
        }
        int[] result = new NamedParameterJdbcTemplate(jdbcTemplate).batchUpdate(sql, paramArray);
        return result.length == prjPreallAttachs.size() ? 1 : 0;
    }

    public int delete(String map_id) {
        String sql = "DELETE  FROM t_prj_preall_attach WHERE map_id=?";
        return jdbcTemplate.update(sql, map_id);
    }

}
