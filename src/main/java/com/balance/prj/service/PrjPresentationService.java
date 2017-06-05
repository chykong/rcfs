package com.balance.prj.service;

import com.balance.prj.dao.PrjPresentationDao;
import com.balance.prj.model.PrjPresentation;
import com.balance.prj.vo.PrjPresentationSearchVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘凯
 * @date 2017年6月4日
 */
@Service
public class PrjPresentationService {
    @Autowired
    private PrjPresentationDao prjPresentationDao;

    /**
     * 查询总数
     *
     * @param prjPresentationSearchVO
     * @return
     */
    public int listCount(PrjPresentationSearchVO prjPresentationSearchVO) {
        return prjPresentationDao.listCount(prjPresentationSearchVO);
    }

    /**
     * 查询会议信息列表
     */
    public List<PrjPresentation> list(PrjPresentationSearchVO prjPresentationSearchVO) {
        return prjPresentationDao.list(prjPresentationSearchVO);

    }

    public int add(PrjPresentation prjPresentation) {
        return prjPresentationDao.add(prjPresentation);
    }
    public PrjPresentation findById(int id){
    	return prjPresentationDao.findById(id); 
    }
    public int update(PrjPresentation prjPresentation){
    	return prjPresentationDao.update( prjPresentation);
    }
    public int delete(int id){
    	return prjPresentationDao.delete(id);
    }
} 
