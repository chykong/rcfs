package com.balance.prj.dao;

import com.balance.prj.model.PrjBrief;
import com.balance.prj.vo.PrjBriefSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目简报
 *
 * @author 刘凯
 * @date 2017年6月5日
 */
@Repository
public class PrjBriefDao extends BaseDao<PrjBrief, PrjBriefSearchVO> {
    /**
     * 查询总数
     *
     * @return
     */
    public int listCount(PrjBriefSearchVO prjBriefSearchVO) {
        String sql = "select count(*) from t_prj_brief t where 1=1";
        sql += createSearchSql(prjBriefSearchVO);
        return listCount(sql, prjBriefSearchVO);
    }

    public List<PrjBrief> list(PrjBriefSearchVO prjBriefSearchVO) {
        String sql = "select * from t_prj_brief t where 1=1";
        sql += createSearchSql(prjBriefSearchVO);
        sql += " order by id desc";
        sql = PageUtil.createMysqlPageSql(sql, prjBriefSearchVO.getPageIndex(), prjBriefSearchVO.getPageSize());
        return list(sql, prjBriefSearchVO);
    }

    private String createSearchSql(PrjBriefSearchVO prjBriefSearchVO) {
        String sql = "";
        if (prjBriefSearchVO.getProgress() != null) {//名称模糊查询
            sql += " and progress =:progress";
        }
        if (prjBriefSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id=:prj_base_info_id";
        }
        return sql;
    }

    /**
     * 新增简报内容
     *
     * @param prjBrief
     * @return
     */
    public int add(PrjBrief prjBrief) {
        String sql = "insert into t_prj_brief(prj_base_info_id,title,content,file_name,file_path,progress,created_by,created_at)" +
                "values(:prj_base_info_id,:title,:content,:file_name,:file_path,:progress,:created_by,now())";
        return update(sql, prjBrief);
    }

    /**
     * 根据id查询prjBrief
     */
    public PrjBrief findById(int id) {
        String sql = "select * from t_prj_brief where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 修改项目简报
     */
    public int update(PrjBrief prjBrief) {
        String sql = "update t_prj_brief set progress=:progress,title=:title,content=:content,file_name=:file_name,file_path=:file_path,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, prjBrief);
    }

    /**
     * 删除项目简报
     */
    public int delete(int id) {
        String sql = "delete from t_prj_brief where id=?";
        return delete(sql, new Object[]{id});
    }
}
