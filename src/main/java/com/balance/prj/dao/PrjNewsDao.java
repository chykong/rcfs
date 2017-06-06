package com.balance.prj.dao;

import com.balance.prj.model.PrjNews;
import com.balance.prj.vo.PrjNewsSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrjNewsDao extends BaseDao<PrjNews, PrjNewsSearchVO> {
    /**
     * 查询总数
     *
     * @return
     */
    public int listCount(PrjNewsSearchVO prjNewsSearchVO) {
        String sql = "select count(*) from t_prj_news t where 1=1";
        sql += createSearchSql(prjNewsSearchVO);
        return listCount(sql, prjNewsSearchVO);
    }

    public List<PrjNews> list(PrjNewsSearchVO prjNewsSearchVO) {
        String sql = "select * from t_prj_news t where 1=1";
        sql += createSearchSql(prjNewsSearchVO);
        sql += " order by id desc";
        sql = PageUtil.createMysqlPageSql(sql, prjNewsSearchVO.getPageIndex(), prjNewsSearchVO.getPageSize());
        return list(sql, prjNewsSearchVO);
    }

    private String createSearchSql(PrjNewsSearchVO prjNewsSearchVO) {
        String sql = "";
        if (prjNewsSearchVO.getProgress() != null) {//名称模糊查询
            sql += " and progress =:progress";
        }
        if (prjNewsSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id=:prj_base_info_id";
        }
        return sql;
    }

    /**
     * 新增会议内容
     *
     * @param prjNews
     * @return
     */
    public int add(PrjNews prjNews) {
        String sql = "insert into t_prj_news(prj_base_info_id,title,content,progress,created_by,created_at)values(:prj_base_info_id,:title,:content,:progress,:created_by,now())";
        return update(sql, prjNews);
    }

    /**
     * 根据id查询prjNews
     */
    public PrjNews findById(int id) {
        String sql = "select * from t_prj_news where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 修改会议纪要
     */
    public int update(PrjNews prjNews) {
        String sql = "update t_prj_news set title=:title,content=:content,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, prjNews);
    }

    /**
     * 删除会议纪要
     */
    public int delete(int id) {
        String sql = "delete from t_prj_news where id=?";
        return delete(sql, new Object[]{id});
    }
}
