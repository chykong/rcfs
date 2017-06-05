package com.balance.prj.service;

import com.balance.prj.dao.PrjNewsDao;
import com.balance.prj.model.PrjNews;
import com.balance.prj.vo.PrjNewsSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘凯
 * @date 2017年6月4日
 */
@Service
public class PrjNewsService {
    @Autowired
    private PrjNewsDao prjNewsDao;

    /**
     * 查询总数
     *
     * @param prjNewsSearchVO
     * @return
     */
    public int listCount(PrjNewsSearchVO prjNewsSearchVO) {
        return prjNewsDao.listCount(prjNewsSearchVO);
    }

    /**
     * 查询会议信息列表
     */
    public List<PrjNews> list(PrjNewsSearchVO prjNewsSearchVO) {
        return prjNewsDao.list(prjNewsSearchVO);

    }

    public int add(PrjNews prjNews) {
        return prjNewsDao.add(prjNews);
    }
    public PrjNews findById(int id){
    	return prjNewsDao.findById(id); 
    }
    public int update(PrjNews prjNews){
    	return prjNewsDao.update( prjNews);
    }
    public int delete(int id){
    	return prjNewsDao.delete(id);
    }
} 
