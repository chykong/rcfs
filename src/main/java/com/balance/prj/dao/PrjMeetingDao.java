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
        sql += createSearchSql(prjMeetingSearchVO);
        return listCount(sql, prjMeetingSearchVO);
    }

    public List<PrjMeeting> list(PrjMeetingSearchVO prjMeetingSearchVO) {
        String sql = "select * from t_prj_meeting t where 1=1";
        sql += createSearchSql(prjMeetingSearchVO);
        sql += " order by id desc";
        sql = PageUtil.createMysqlPageSql(sql, prjMeetingSearchVO.getPageIndex(), prjMeetingSearchVO.getPageSize());
        return list(sql, prjMeetingSearchVO);
    }

    private String createSearchSql(PrjMeetingSearchVO prjMeetingSearchVO) {
        String sql = "";
        if (prjMeetingSearchVO.getProgress() != null) {//名称模糊查询
            sql += " and progress =:progress";
        }
        if (prjMeetingSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id=:prj_base_info_id";
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

    /**
     * 根据id查询prjMetting
     */
    public PrjMeeting findById(int id) {
        String sql = "select * from t_prj_meeting where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 修改会议纪要
     */
    public int update(PrjMeeting prjMeeting) {
        String sql = "update t_prj_meeting set progress=:progress,title=:title,content=:content,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, prjMeeting);
    }

    /**
     * 删除会议纪要
     */
    public int delete(int id) {
        String sql = "delete from t_prj_meeting where id=?";
        return delete(sql, new Object[]{id});
    }
}
