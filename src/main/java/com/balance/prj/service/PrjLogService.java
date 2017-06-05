package com.balance.prj.service;

import com.balance.prj.dao.PrjLogDao;
import com.balance.prj.model.PrjLog;
import com.balance.prj.vo.PrjLogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘凯
 * @date 2017年6月4日
 */
@Service
public class PrjLogService {
    @Autowired
    private PrjLogDao prjLogDao;

    /**
     * 查询总数
     *
     * @param prjLogSearchVO
     * @return
     */
    public int listCount(PrjLogSearchVO prjLogSearchVO) {
        return prjLogDao.listCount(prjLogSearchVO);
    }

    /**
     * 查询会议信息列表
     */
    public List<PrjLog> list(PrjLogSearchVO prjLogSearchVO) {
        return prjLogDao.list(prjLogSearchVO);

    }

    public int add(PrjLog prjLog) {
        return prjLogDao.add(prjLog);
    }
    public PrjLog findById(int id){
    	return prjLogDao.findById(id); 
    }
    public int update(PrjLog prjLog){
    	return prjLogDao.update( prjLog);
    }
    public int delete(int id){
    	return prjLogDao.delete(id);
    }
} 
