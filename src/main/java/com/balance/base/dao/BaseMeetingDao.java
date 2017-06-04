package com.balance.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.balance.base.model.BaseMeeting;
import com.balance.base.vo.BaseMeetingSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
@Repository
public class BaseMeetingDao extends BaseDao<BaseMeeting, BaseMeetingSearchVO>{
	/**
	 * 查询总数
	 * @return
	 */
	public int listCount(BaseMeetingSearchVO baseMeetingSearchVO){
		String sql="select count(*) from t_prj_meeting t";
		return listCount(sql, baseMeetingSearchVO);
	} 
	public List<BaseMeeting>list(BaseMeetingSearchVO baseMeetingSearchVO){
		String sql="select * from t_prj_meeting t";
		sql= PageUtil.createMysqlPageSql(sql, baseMeetingSearchVO.getPageIndex(), baseMeetingSearchVO.getPageSize());
		return  list(sql, baseMeetingSearchVO);
	}
	/**
	 * 新增会议内容
	 * @param baseMeeting
	 * @return
	 */ 
	public int add(BaseMeeting baseMeeting) {
		String sql="insert into t_prj_meeting(title,content,created_by,created_at)values(:title,:content,:created_by,now())";
		return update(sql, baseMeeting);
	}
}
