package com.balance.prj.dao;

import com.balance.prj.model.PrjChart;
import com.balance.prj.vo.EntireStatVO;
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
                ") countLeftDay FROM t_prj_preallocation where 1=1 and in_host_date is not null and in_host_date != '' " +
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


    public String getMaxDate(int prj_base_info_id) {
        String sql = "SELECT " +
                "DATE_FORMAT(if(if(max(in_host_date) > max(signed_date),max(in_host_date),max(signed_date))  > max(handover_house_date)," +
                "if(max(in_host_date) > max(signed_date),max(in_host_date),max(signed_date)),max(handover_house_date)),'%Y-%m-%d')  " +
                "FROM t_prj_preallocation WHERE prj_base_info_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{prj_base_info_id}, String.class);
    }

    public String getMinDate(int prj_base_info_id) {
        String sql = "SELECT " +
                "DATE_FORMAT(if(if(min(in_host_date) < min(signed_date),min(in_host_date),min(signed_date))  < min(handover_house_date)," +
                "if(min(in_host_date) < min(signed_date),min(in_host_date),min(signed_date)),min(handover_house_date)),'%Y-%m-%d')  " +
                "FROM t_prj_preallocation WHERE prj_base_info_id = ?";
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
        if (StringUtil.isNotNullOrEmpty(prjChartsSearchVO.getDate()) && prjChartsSearchVO.getSearch_type() != null) {
            switch (prjChartsSearchVO.getSearch_type()) {
                case 1:
                    sql += " and in_host_date=:date";
                    break;
                case 2:
                    sql += " and signed_date=:date";
                    break;
                case 3:
                    sql += " and handover_house_date=:date";
                    break;
                case 4:
                    sql += " and money_date=:date";
                    break;
            }
        }
        return sql;
    }

    public float getTotalHomes(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "SELECT ifnull(" + getSearchByType(prjChartsSearchVO.getType()) + ",0) FROM t_prj_preallocation WHERE 1=1 and  land_property=:current_land_name ";
        sql += createSql(prjChartsSearchVO);
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().queryForObject(sql, params, Float.class);
    }

    /**
     * 入户
     *
     * @param prjChartsSearchVO
     * @return
     */
    public List<PrjChart> getGroupInHostList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "select t1.groups,ifnull(t1.total,0) total,ifnull(t2.today,0) today from (select groups," + getSearchByType(prjChartsSearchVO.getType()) + " total " +
                "from t_prj_preallocation  where  land_property=:current_land_name and in_host_date is not null and in_host_date!=''  and groups is not null and groups!=''" +
                createSql(prjChartsSearchVO) + " group by groups) t1 left join " +
                "(select groups," + getSearchByType(prjChartsSearchVO.getType()) + " today " +
                "from t_prj_preallocation  where in_host_date= CURDATE() " +
                createSql(prjChartsSearchVO) + " group by groups) t2 on t1.groups=t2.groups ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    /**
     * 签约
     *
     * @param prjChartsSearchVO
     * @return
     */
    public List<PrjChart> getGroupSignList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "select t1.groups,ifnull(t1.total,0) total,ifnull(t2.today,0) today from (select groups," + getSearchByType(prjChartsSearchVO.getType()) + " total " +
                "from t_prj_preallocation  where land_property=:current_land_name and signed_date is not null and signed_date!=''  and groups is not null and groups!=''" +
                createSql(prjChartsSearchVO) + " group by groups) t1 left join " +
                "(select groups," + getSearchByType(prjChartsSearchVO.getType()) + " today " +
                "from t_prj_preallocation  where signed_date= CURDATE() " +
                createSql(prjChartsSearchVO) + " group by groups) t2 on t1.groups=t2.groups ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    /**
     * 交房
     *
     * @param prjChartsSearchVO
     * @return
     */
    public List<PrjChart> getGroupHandoverList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "select t1.groups,ifnull(t1.total,0) total,ifnull(t2.today,0) today from (select groups," + getSearchByType(prjChartsSearchVO.getType()) + " total " +
                "from t_prj_preallocation  where land_property=:current_land_name and handover_house_date is not null and handover_house_date !=''  and groups is not null and groups!=''" +
                createSql(prjChartsSearchVO) + " group by groups) t1 left join " +
                "(select groups," + getSearchByType(prjChartsSearchVO.getType()) + " today " +
                "from t_prj_preallocation  where handover_house_date= CURDATE() " +
                createSql(prjChartsSearchVO) + " group by groups) t2 on t1.groups=t2.groups ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    /**
     * 放款
     *
     * @param prjChartsSearchVO
     * @return
     */
    public List<PrjChart> getGroupMoneyList(PrjChartsSearchVO prjChartsSearchVO) {
        String sql = "select t1.groups,ifnull(t1.total,0) total,ifnull(t2.today,0) today from (select groups," + getSearchByType(prjChartsSearchVO.getType()) + " total " +
                "from t_prj_preallocation  where land_property=:current_land_name and money_date is not null and money_date !='' and groups is not null and groups!=''" +
                createSql(prjChartsSearchVO) + " group by groups) t1 left join " +
                "(select groups," + getSearchByType(prjChartsSearchVO.getType()) + " today " +
                "from t_prj_preallocation  where money_date= CURDATE() " +
                createSql(prjChartsSearchVO) + " group by groups) t2 on t1.groups=t2.groups ";

        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    /**
     * 根据类型判断是查询哪一种，1查询户数2查询占地面积3建筑面积
     *
     * @param type
     * @return
     */
    private String getSearchByType(int type) {
        String content = "";
        if (type == 1) return "count(*)";
        else if (type == 2) return "sum(cog_land_area)";
        else if (type == 3) return "sum(total_homestead_area)";
        else return "count(*)";
    }

    /**
     * 查询在一个月内的所有拆迁数
     *
     * @return
     */
    public List<EntireStatVO> listEntireByType(int prj_base_info_id, String land_property, String s_date, String e_date, int type, int search_type) {
        String field = getStatField(search_type);//获取查询字段
        String sql = "select " + field + " date,ifnull(" + getSearchByType(type) + ",0) data " +
                "from t_prj_preallocation where  prj_base_info_id=? and land_property=?  and " + field + ">=? and " + field + "<=? and "
                + field + " is not null and " + field + " !=''" +
                " group by " + field + " order by " + field + "";
        return jdbcTemplate.query(sql, new Object[]{prj_base_info_id, land_property, s_date, e_date}, new BeanPropertyRowMapper<>(EntireStatVO.class));
    }

    /**
     * 查询在一个月前已完成的拆迁数
     *
     * @param land_property 土地使用属性
     * @return
     */
    public float getExist(int prj_base_info_id, String land_property, String s_date, int type, int search_type) {
        String field = getStatField(search_type);//获取查询字段
        String sql = "select ifnull(" + getSearchByType(type) + ",0) cnt from t_prj_preallocation where prj_base_info_id=? and land_property=? and  "
                + field + "<?  and " + field + " is not null and " + field + " !=''";
        return jdbcTemplate.queryForObject(sql, new Object[]{prj_base_info_id, land_property, s_date}, Float.class);
    }

    /**
     * 根据查询字段，实际用哪个字段进行查询1入户日期，2签约日期3交房日期
     *
     * @param search_type
     * @return
     */
    private String getStatField(int search_type) {
        String field = "";
        if (search_type == 1) {
            field = "in_host_date";
        } else if (search_type == 2) {
            field = "signed_date";
        } else if (search_type == 3) {
            field = "handover_house_date";
        } else if (search_type == 4) {
            field = "money_date";
        }
        return field;
    }


    /**
     * 总计数据
     *
     * @param prjChartsSearchVO
     * @return
     */
    public List<PrjChart> getGroupTotalList(PrjChartsSearchVO prjChartsSearchVO, int type) {
        String sql = "SELECT groups,"+getSearchByType(prjChartsSearchVO.getType())+" total FROM t_prj_preallocation " +
                " WHERE " + getStatField(type) + " IS NOT NULL AND " + getStatField(type) + " != '' " +
                " AND groups IS NOT NULL AND groups != '' AND prj_base_info_id =:prj_base_info_id and land_property=:current_land_name  GROUP BY groups";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }

    /**
     * 当日数量
     *
     * @param prjChartsSearchVO
     * @return
     */
    public List<PrjChart> getGroupTodayList(PrjChartsSearchVO prjChartsSearchVO, int type) {
        String sql = "SELECT groups,"+getSearchByType(prjChartsSearchVO.getType())+" today FROM t_prj_preallocation" +
                " WHERE " + getStatField(type) + " = CURDATE() AND prj_base_info_id =:prj_base_info_id and land_property=:current_land_name " +
                " GROUP BY groups ";
        SqlParameterSource params = new BeanPropertySqlParameterSource(prjChartsSearchVO);
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(PrjChart.class));
    }
}
