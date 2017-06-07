package com.balance.prj.dao;

import com.balance.prj.model.PrjGroup;
import com.balance.prj.vo.PrjGroupSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
@Service
public class PrjGroupDao extends BaseDao<PrjGroup, PrjGroupSearchVO> {

    /**
     * 新增
     *
     * @param prjGroup
     * @return
     */
    public int add(PrjGroup prjGroup) {
        String sql = "insert into t_prj_group(name,prj_base_info_id,section_id,total_homes,created_at,created_by)" +
                " values(:name,:prj_base_info_id,:section_id,:total_homes,now(),:created_by)";
        return update(sql, prjGroup);
    }

    /**
     * 修改
     *
     * @param prjGroup
     * @return
     */
    public int update(PrjGroup prjGroup) {
        String sql = "update t_prj_group set name=:name,prj_base_info_id=:prj_base_info_id,section_id=:section_id,total_homes=:total_homes,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, prjGroup);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_prj_group where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public PrjGroup get(int id) {
        String sql = "select t.*,(select prj_name from t_prj_baseinfo where id=t.prj_base_info_id) prj_name," +
                "(select name from t_prj_secion where id=t.section_id) section_name" +
                " from t_prj_group t where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 分页列表
     *
     * @param prjGroupSearchVO
     * @return
     */
    public List<PrjGroup> list(PrjGroupSearchVO prjGroupSearchVO) {
        String sql = "select t.*,(select prj_name from t_prj_baseinfo where id=t.prj_base_info_id) prj_name," +
                "(select name from t_prj_secion where id=t.section_id) section_name from t_prj_group t ";
        sql += createSearchSql(prjGroupSearchVO);
        sql = PageUtil.createMysqlPageSql(sql, prjGroupSearchVO.getPageIndex(), prjGroupSearchVO.getPageSize());
        return list(sql, prjGroupSearchVO);
    }

    /**
     * 查询总数
     *
     * @param prjGroupSearchVO
     * @return
     */
    public int listCount(PrjGroupSearchVO prjGroupSearchVO) {
        String sql = "select count(*) from t_prj_group t where 1=1 ";
        sql += createSearchSql(prjGroupSearchVO);
        return listCount(sql, prjGroupSearchVO);
    }

    private String createSearchSql(PrjGroupSearchVO baseGroupSearchVO) {
        String sql = "";
        if (baseGroupSearchVO.getPrj_base_info_id() != null) {//项目id
            sql += " and prj_base_info_id=:prj_base_info_id";
        }
        if (baseGroupSearchVO.getSection_id() != null) {
            sql += " and section_id=:section_id";//sectionid
        }
        return sql;
    }

    /**
     * 根据id查组别
     *
     * @param prj_base_info_id
     * @return
     */
    public List<PrjGroup> list(int prj_base_info_id, int section_id) {
        String sql = "select * from t_prj_group t where prj_base_info_id=? and section_id=?";
        return list(sql, new Object[]{prj_base_info_id, section_id});
    }

}
