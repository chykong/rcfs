package com.balance.prj.service;

import com.balance.prj.dao.PrjBriefDao;
import com.balance.prj.model.PrjBrief;
import com.balance.prj.vo.PrjBriefSearchVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘凯
 * @date 2017年6月4日
 */
@Service
public class PrjBriefService {
    @Autowired
    private PrjBriefDao prjBriefDao;

    /**
     * 查询总数
     *
     * @param prjBriefSearchVO
     * @return
     */
    public int listCount(PrjBriefSearchVO prjBriefSearchVO) {
        return prjBriefDao.listCount(prjBriefSearchVO);
    }

    /**
     * 查询会议信息列表
     */
    public List<PrjBrief> list(PrjBriefSearchVO prjBriefSearchVO) {
        return prjBriefDao.list(prjBriefSearchVO);

    }

    public int add(PrjBrief prjBrief) {
        return prjBriefDao.add(prjBrief);
    }
    public PrjBrief findById(int id){
    	return prjBriefDao.findById(id); 
    }
    public int update(PrjBrief prjBrief){
    	return prjBriefDao.update( prjBrief);
    }
    public int delete(int id){
    	return prjBriefDao.delete(id);
    }
} 
