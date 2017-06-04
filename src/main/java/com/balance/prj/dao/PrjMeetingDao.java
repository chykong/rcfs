package com.balance.prj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.balance.prj.model.PrjMeeting;
import com.balance.prj.vo.PrjMeetingSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
@Repository
public class PrjMeetingDao extends BaseDao<PrjMeeting, PrjMeetingSearchVO>{
	/**
	 * 查询总数
	 * @return
	 */
	public int listCount(PrjMeetingSearchVO prjMeetingSearchVO){
		String sql="select count(*) from t_prj_meeting t";
		return listCount(sql, prjMeetingSearchVO);
	}   
	public List<PrjMeeting>list(PrjMeetingSearchVO prjMeetingSearchVO){
		String sql="select * from t_prj_meeting t";
		sql= PageUtil.createMysqlPageSql(sql, prjMeetingSearchVO.getPageIndex(), prjMeetingSearchVO.getPageSize());
		return  list(sql, prjMeetingSearchVO);
	} 
	/**
	 * 新增会议内容 
	 * @param baseMeeting
	 * @return
	 */
	public int add(PrjMeeting prjMeeting) {
		String sql="insert into t_prj_meeting(prj_base_info_id,title,content,project_progress,last_modified_at,created_by,created_at)values(:prj_base_info_id,:title,:content,:project_progress,now(),:created_by,now())";
		return update(sql, prjMeeting);
	}
}
