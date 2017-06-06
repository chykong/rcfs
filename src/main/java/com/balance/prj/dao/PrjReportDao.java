package com.balance.prj.dao;

import com.balance.prj.model.PrjReport;
import com.balance.prj.vo.PrjReportSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrjReportDao extends BaseDao<PrjReport, PrjReportSearchVO> {
    /**
     * 查询总数
     *
     * @return
     */
    public int listCount(PrjReportSearchVO prjReportSearchVO) {
        String sql = "select count(*) from t_prj_report t where 1=1";
        sql += createSearchSql(prjReportSearchVO);
        return listCount(sql, prjReportSearchVO);
    }

    public List<PrjReport> list(PrjReportSearchVO prjReportSearchVO) {
        String sql = "select * from t_prj_report t where 1=1";
        sql += createSearchSql(prjReportSearchVO);
        sql += " order by id desc";
        sql = PageUtil.createMysqlPageSql(sql, prjReportSearchVO.getPageIndex(), prjReportSearchVO.getPageSize());
        return list(sql, prjReportSearchVO);
    }

    private String createSearchSql(PrjReportSearchVO prjReportSearchVO) {
        String sql = "";
        if (prjReportSearchVO.getProgress() != null) {//进度
            sql += " and progress =:progress";
        }
        if (prjReportSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id=:prj_base_info_id";
        }
        return sql;
    }

    /**
     * 新增会议内容
     *
     * @param prjReport
     * @return
     */
    public int add(PrjReport prjReport) {
        String sql = "insert into t_prj_report(prj_base_info_id,title,content,progress,created_by,created_at)values(:prj_base_info_id,:title,:content,:progress,:created_by,now())";
        return update(sql, prjReport);
    }

    /**
     * 根据id查询prjMetting
     */
    public PrjReport findById(int id) {
        String sql = "select * from t_prj_report where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 修改会议纪要
     */
    public int update(PrjReport prjReport) {
        String sql = "update t_prj_report set title=:title,content=:content,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, prjReport);
    }

    /**
     * 删除会议纪要
     */
    public int delete(int id) {
        String sql = "delete from t_prj_report where id=?";
        return delete(sql, new Object[]{id});
    }
}
