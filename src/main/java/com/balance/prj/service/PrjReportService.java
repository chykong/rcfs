package com.balance.prj.service;

import com.balance.prj.dao.PrjReportDao;
import com.balance.prj.model.PrjReport;
import com.balance.prj.vo.PrjReportSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘凯
 * @date 2017年6月4日
 */
@Service
public class PrjReportService {
    @Autowired
    private PrjReportDao prjReportDao;

    /**
     * 查询总数
     *
     * @param prjReportSearchVO
     * @return
     */
    public int listCount(PrjReportSearchVO prjReportSearchVO) {
        return prjReportDao.listCount(prjReportSearchVO);
    }

    /**
     * 查询会议信息列表
     */
    public List<PrjReport> list(PrjReportSearchVO prjReportSearchVO) {
        return prjReportDao.list(prjReportSearchVO);

    }

    public int add(PrjReport prjReport) {
        return prjReportDao.add(prjReport);
    }
    public PrjReport findById(int id){
    	return prjReportDao.findById(id); 
    }
    public int update(PrjReport prjReport){
    	return prjReportDao.update( prjReport);
    }
    public int delete(int id){
    	return prjReportDao.delete(id);
    }
} 
