package com.balance.prj.dao;

import com.balance.prj.model.PrjBaseinfo;
import com.balance.prj.vo.PrjBaseinfoSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import com.balance.util.string.StringUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
@Repository
public class PrjBaseinfoDao extends BaseDao<PrjBaseinfo, PrjBaseinfoSearchVO> {
    /**
     * 新增
     *
     * @param prjBaseinfo
     * @return
     */
	public int add(PrjBaseinfo prjBaseinfo) {
		String sql = "insert     into t_prj_baseinfo(prj_name,introduction,created_at,created_by) values(:prj_name,:introduction,now(),:created_by)";
		return update(sql, prjBaseinfo);
	}

	/**
	 * 修改
	 *
	 * @param prjBaseinfo
	 * @return
	 */
	public int update(PrjBaseinfo prjBaseinfo) {
			String consql = "introduction=:introduction";
			String type = prjBaseinfo.getType();
			if (type.equals("2"))
				consql = "flow=:flow";
			if (type.equals("3"))
				consql = "architecture=:architecture";
			String sql = "update t_prj_baseinfo set  " + consql
					+ ",last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
			return update(sql, prjBaseinfo);
	}

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_prj_baseinfo where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public PrjBaseinfo get(int id) {
        String sql = "select * from t_prj_baseinfo t where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 分页列表
     *
     * @param prjBaseinfoSearchVO
     * @return
     */
    public List<PrjBaseinfo> list(PrjBaseinfoSearchVO prjBaseinfoSearchVO) {
        String sql = "select * from t_prj_baseinfo t where 1=1";
        sql += createSearchSql(prjBaseinfoSearchVO);
        sql = PageUtil.createMysqlPageSql(sql, prjBaseinfoSearchVO.getPageIndex(), prjBaseinfoSearchVO.getPageSize());
        return list(sql, prjBaseinfoSearchVO);
    }

    /**
     * 查询总数
     *
     * @param prjBaseinfoSearchVO
     * @return
     */
    public int listCount(PrjBaseinfoSearchVO prjBaseinfoSearchVO) {
        String sql = "select count(*) from t_prj_baseinfo t  where 1=1";
        sql += createSearchSql(prjBaseinfoSearchVO);
        return listCount(sql, prjBaseinfoSearchVO);
    }

    private String createSearchSql(PrjBaseinfoSearchVO prjBaseinfoSearchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(prjBaseinfoSearchVO.getPrj_name())) {//名称模糊查询
            sql += " and prj_name like :prj_name_str";
        }
        return sql;
    }

    /**
     * 全部项目列表
     *
     * @return 列表
     */
    public List<PrjBaseinfo> listAll() {
        String sql = "select * from t_prj_baseinfo t ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PrjBaseinfo.class));
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<PrjBaseinfo> list() {
        String sql = "select id,prj_name from t_prj_baseinfo t order by id desc";
        return list(sql);
    }

    /**
     * 获取该用户的所有项目
     *
     * @param user_id
     * @return
     */
    public List<PrjBaseinfo> listSelectProject(int user_id) {
        String sql = "select * from t_prj_baseinfo where id in (select prj_base_info_id from t_sys_userprojects where user_id=?)";
        return list(sql, new Object[]{user_id});
    }

}
