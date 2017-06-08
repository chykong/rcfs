package com.balance.prj.dao;

import com.balance.prj.model.PrjChart;
import com.balance.prj.vo.PrjChartsSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.string.StringUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dsy on 2017/6/7.
 * 图标Dao
 */
@Repository
public class PrjChartsDao extends BaseDao<PrjChart, PrjChartsSearchVO> {

    public List<PrjChart> getInHostList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "SELECT DATE_FORMAT(in_host_date,'%Y-%m-%d') title," +
                "(select count(*) from  t_prj_preallocation where in_host_date<DATE_ADD(in_host_date,INTERVAL 1 day)" +
                createSql(prjChartsSearchVO) +
                ") countLeftDay FROM t_prj_preallocation where 1=1 and in_host_date is not null " +
                createSql(prjChartsSearchVO) +
                " GROUP BY title";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    public List<PrjChart> getContractList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "SELECT DATE_FORMAT(signed_date,'%Y-%m-%d') title," +
                "(select count(*) from  t_prj_preallocation where signed_date<DATE_ADD(signed_date,INTERVAL 1 day)" +
                createSql(prjChartsSearchVO) +
                ") countLeftDay FROM t_prj_preallocation where 1=1 and signed_date is not null " +
                createSql(prjChartsSearchVO) +
                " GROUP BY title";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    public List<PrjChart> getHandoverList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "SELECT DATE_FORMAT(handover_house_date,'%Y-%m-%d') title," +
                "(select count(*) from  t_prj_preallocation where handover_house_date<DATE_ADD(handover_house_date,INTERVAL 1 day)" +
                createSql(prjChartsSearchVO) +
                ") countLeftDay FROM t_prj_preallocation where 1=1 and handover_house_date is not null " +
                createSql(prjChartsSearchVO) +
                " GROUP BY title";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    public String getMinDate(int prj_base_info_id) {
        String sql = "SELECT ifnull(DATE_FORMAT(min(in_host_date), '%Y-%m-%d'),now()) FROM t_prj_preallocation WHERE prj_base_info_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{prj_base_info_id}, String.class);
    }

    public String getMaxDate(int prj_base_info_id) {
        String sql = "SELECT ifnull(DATE_FORMAT(max(handover_house_date), '%Y-%m-%d'),now()) FROM t_prj_preallocation WHERE prj_base_info_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{prj_base_info_id}, String.class);
    }


    private String createSql(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "";
        if (prjChartsSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id=:prj_base_info_id";
        }
        if (StringUtil.isNotNullOrEmpty(prjChartsSearchVO.getTown())) {
            sql += " and town=:town";
        }
        if (StringUtil.isNotNullOrEmpty(prjChartsSearchVO.getVillage())) {
            sql += " and village=:village";
        }
        return sql;
    }

    public int getTotalHomes(int project_id) {
        String sql = "SELECT count(*) FROM t_prj_preallocation WHERE prj_base_info_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{project_id}, Integer.class);
    }

    public List<PrjChart> getGroupInHostList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "select t1.*,ifnull(t2.today,0) today from (select groups,count(*) total " +
                "from t_prj_preallocation  where  in_host_date is not null " +
                createSql(prjChartsSearchVO) + " group by groups) t1 left join " +
                "(select groups,count(*) today " +
                "from t_prj_preallocation  where in_host_date= CURDATE() " +
                createSql(prjChartsSearchVO) + " group by groups) t2 on t1.groups=t2.groups ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    public List<PrjChart> getGroupSignList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "select t1.*,ifnull(t2.today,0) today from (select groups,count(*) total " +
                "from t_prj_preallocation  where  signed_date is not null " +
                createSql(prjChartsSearchVO) + " group by groups) t1 left join " +
                "(select groups,count(*) today " +
                "from t_prj_preallocation  where signed_date= CURDATE() " +
                createSql(prjChartsSearchVO) + " group by groups) t2 on t1.groups=t2.groups ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    public List<PrjChart> getGroupHandoverList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "select t1.*,ifnull(t2.today,0) today from (select groups,count(*) total " +
                "from t_prj_preallocation  where  handover_house_date is not null " +
                createSql(prjChartsSearchVO) + " group by groups) t1 left join " +
                "(select groups,count(*) today " +
                "from t_prj_preallocation  where handover_house_date= CURDATE() " +
                createSql(prjChartsSearchVO) + " group by groups) t2 on t1.groups=t2.groups ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    public List<PrjChart> getGroupMoneyList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "select t1.*,ifnull(t2.today,0) today from (select groups,count(*) total " +
                "from t_prj_preallocation  where  money_date is not null " +
                createSql(prjChartsSearchVO) + " group by groups) t1 left join " +
                "(select groups,count(*) today " +
                "from t_prj_preallocation  where money_date= CURDATE() " +
                createSql(prjChartsSearchVO) + " group by groups) t2 on t1.groups=t2.groups ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }
}
