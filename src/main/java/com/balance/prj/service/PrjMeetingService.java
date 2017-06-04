package com.balance.prj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balance.prj.dao.PrjMeetingDao;
import com.balance.prj.model.PrjMeeting;
import com.balance.prj.vo.PrjMeetingSearchVO;

/**
 * 
 * @author 刘凯
 * @date 2017年6月4日
 */
@Service
public class PrjMeetingService {
	@Autowired
private PrjMeetingDao prjMeetingDao;
	/**
	 * 查询总数
	 * @param baseMeetingSearchVO
	 * @return
	 */
 public int listCount(PrjMeetingSearchVO prjMeetingSearchVO){
	 return prjMeetingDao.listCount(prjMeetingSearchVO);
 }
 /**
  * 查询会议信息列表
  */
 public List<PrjMeeting>list(PrjMeetingSearchVO prjMeetingSearchVO){
	 return prjMeetingDao.list(prjMeetingSearchVO);
	 
 }
public int add(PrjMeeting prjMeeting) {
	return prjMeetingDao.add(prjMeeting);
}
} 
