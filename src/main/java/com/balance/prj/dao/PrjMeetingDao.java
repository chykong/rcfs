package com.balance.prj.dao;

import com.balance.prj.model.PrjMeeting;
import com.balance.prj.vo.PrjMeetingSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrjMeetingDao extends BaseDao<PrjMeeting, PrjMeetingSearchVO> {
    /**
     * 查询总数
     *
     * @return
     */
    public int listCount(PrjMeetingSearchVO prjMeetingSearchVO) {
        String sql = "select count(*) from t_prj_meeting t where 1=1";
        sql+=createSearchSql(prjMeetingSearchVO);
        return listCount(sql, prjMeetingSearchVO);
    }

    public List<PrjMeeting> list(PrjMeetingSearchVO prjMeetingSearchVO) {
        String sql = "select * from t_prj_meeting t where 1=1";
        sql+=createSearchSql(prjMeetingSearchVO);
        sql = PageUtil.createMysqlPageSql(sql, prjMeetingSearchVO.getPageIndex(), prjMeetingSearchVO.getPageSize());
        return list(sql, prjMeetingSearchVO);
    }

    private String createSearchSql(PrjMeetingSearchVO prjMeetingSearchVO) {
        String sql = "";
        if (prjMeetingSearchVO.getProgress() != null) {//名称模糊查询
            sql += " and progress =:progress";
        }
        return sql;
    }

    /**
     * 新增会议内容
     *
     * @param prjMeeting
     * @return
     */
    public int add(PrjMeeting prjMeeting) {
        String sql = "insert into t_prj_meeting(prj_base_info_id,title,content,progress,created_by,created_at)values(:prj_base_info_id,:title,:content,:progress,:created_by,now())";
        return update(sql, prjMeeting);
    }
}
