package com.balance.prj.service;

import com.balance.prj.dao.PrjPlanDao;
import com.balance.prj.model.PrjPlan;
import com.balance.prj.vo.PrjPlanSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘凯
 * @date 2017年6月4日
 */
@Service
public class PrjPlanService {
    @Autowired
    private PrjPlanDao prjPlanDao;

    /**
     * 查询总数
     *
     * @param prjPlanSearchVO
     * @return
     */
    public int listCount(PrjPlanSearchVO prjPlanSearchVO) {
        return prjPlanDao.listCount(prjPlanSearchVO);
    }

    /**
     * 查询会议信息列表
     */
    public List<PrjPlan> list(PrjPlanSearchVO prjPlanSearchVO) {
        return prjPlanDao.list(prjPlanSearchVO);

    }

    public int add(PrjPlan prjPlan) {
        return prjPlanDao.add(prjPlan);
    }
    public PrjPlan findById(int id){
    	return prjPlanDao.findById(id); 
    }
    public int update(PrjPlan prjPlan){
    	return prjPlanDao.update( prjPlan);
    }
    public int delete(int id){
    	return prjPlanDao.delete(id);
    }
} 
