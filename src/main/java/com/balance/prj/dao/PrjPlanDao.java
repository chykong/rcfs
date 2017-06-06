package com.balance.prj.dao;

import com.balance.prj.model.PrjPlan;
import com.balance.prj.vo.PrjPlanSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrjPlanDao extends BaseDao<PrjPlan, PrjPlanSearchVO> {
    /**
     * 查询总数
     *
     * @return
     */
    public int listCount(PrjPlanSearchVO prjPlanSearchVO) {
        String sql = "select count(*) from t_prj_plan t where 1=1";
        sql += createSearchSql(prjPlanSearchVO);
        return listCount(sql, prjPlanSearchVO);
    }

    public List<PrjPlan> list(PrjPlanSearchVO prjPlanSearchVO) {
        String sql = "select * from t_prj_plan t where 1=1";
        sql += createSearchSql(prjPlanSearchVO);
        sql += " order by id desc";
        sql = PageUtil.createMysqlPageSql(sql, prjPlanSearchVO.getPageIndex(), prjPlanSearchVO.getPageSize());
        return list(sql, prjPlanSearchVO);
    }

    private String createSearchSql(PrjPlanSearchVO prjPlanSearchVO) {
        String sql = "";
        if (prjPlanSearchVO.getProgress() != null) {//名称模糊查询
            sql += " and progress =:progress";
        }
        if (prjPlanSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id=:prj_base_info_id";
        }
        return sql;
    }

    /**
     * 新增会议内容
     *
     * @param prjPlan
     * @return
     */
    public int add(PrjPlan prjPlan) {
        String sql = "insert into t_prj_plan(prj_base_info_id,title,content,progress,created_by,created_at)values(:prj_base_info_id,:title,:content,:progress,:created_by,now())";
        return update(sql, prjPlan);
    }

    /**
     * 根据id查询prjPlan
     */
    public PrjPlan findById(int id) {
        String sql = "select * from t_prj_plan where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 修改会议纪要
     */
    public int update(PrjPlan prjPlan) {
        String sql = "update t_prj_plan set title=:title,content=:content,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, prjPlan);
    }

    /**
     * 删除会议纪要
     */
    public int delete(int id) {
        String sql = "delete from t_prj_plan where id=?";
        return delete(sql, new Object[]{id});
    }
}
