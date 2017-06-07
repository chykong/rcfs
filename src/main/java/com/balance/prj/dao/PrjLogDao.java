package com.balance.prj.dao;

import com.balance.prj.model.PrjLog;
import com.balance.prj.vo.PrjLogSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrjLogDao extends BaseDao<PrjLog, PrjLogSearchVO> {
    /**
     * 查询总数
     *
     * @return
     */
    public int listCount(PrjLogSearchVO prjLogSearchVO) {
        String sql = "select count(*) from t_prj_log t where 1=1";
        sql += createSearchSql(prjLogSearchVO);
        return listCount(sql, prjLogSearchVO);
    }

    public List<PrjLog> list(PrjLogSearchVO prjLogSearchVO) {
        String sql = "select * from t_prj_log t where 1=1";
        sql += createSearchSql(prjLogSearchVO);
        sql += " order by id desc";
        sql = PageUtil.createMysqlPageSql(sql, prjLogSearchVO.getPageIndex(), prjLogSearchVO.getPageSize());
        return list(sql, prjLogSearchVO);
    }

    private String createSearchSql(PrjLogSearchVO prjLogSearchVO) {
        String sql = "";
        if (prjLogSearchVO.getProgress() != null) {//名称模糊查询
            sql += " and progress =:progress";
        }
        if (prjLogSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id=:prj_base_info_id";
        }
        return sql;
    }

    /**
     * 新增会议内容
     *
     * @param prjLog
     * @return
     */
    public int add(PrjLog prjLog) {
        String sql = "insert into t_prj_log(prj_base_info_id,title,content,progress,created_by,created_at)values(:prj_base_info_id,:title,:content,:progress,:created_by,now())";
        return update(sql, prjLog);
    }

    /**
     * 根据id查询prjMetting
     */
    public PrjLog findById(int id) {
        String sql = "select * from t_prj_log where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 修改会议纪要
     */
    public int update(PrjLog prjLog) {
        String sql = "update t_prj_log set progress=:progress,title=:title,content=:content,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, prjLog);
    }

    /**
     * 删除会议纪要
     */
    public int delete(int id) {
        String sql = "delete from t_prj_log where id=?";
        return delete(sql, new Object[]{id});
    }
}
