package com.balance.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balance.base.dao.BaseMeetingDao;
import com.balance.base.model.BaseMeeting;
import com.balance.base.vo.BaseMeetingSearchVO;

/**
 * 
 * @author 刘凯
 * @date 2017年6月4日
 */
@Service
public class BaseMeetingService {
	@Autowired
private BaseMeetingDao baseMeetingDao;
	/**
	 * 查询总数
	 * @param baseMeetingSearchVO
	 * @return
	 */
 public int listCount(BaseMeetingSearchVO baseMeetingSearchVO){
	 return baseMeetingDao.listCount(baseMeetingSearchVO);
 }
 /**
  * 查询会议信息列表
  */
 public List<BaseMeeting>list(BaseMeetingSearchVO baseMeetingSearchVO){
	 return baseMeetingDao.list(baseMeetingSearchVO);
	 
 }
public int add(BaseMeeting baseMeeting) {
	return baseMeetingDao.add(baseMeeting);
}
} 
