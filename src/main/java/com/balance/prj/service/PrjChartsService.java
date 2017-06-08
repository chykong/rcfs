package com.balance.prj.service;

import com.balance.prj.dao.PrjChartsDao;
import com.balance.prj.model.PrjChart;
import com.balance.prj.vo.PrjChartsSearchVO;
import com.balance.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dsy on 2017/6/7.
 * 图表Service
 */
@Service
public class PrjChartsService {
    @Autowired
    private PrjChartsDao prjChartsDao;

    public List<PrjChart> getInHostList(PrjChartsSearchVO prjChartsSearchVO) {
        return getOverList(prjChartsDao.getInHostList(prjChartsSearchVO),prjChartsSearchVO.getPrj_base_info_id());
    }

    public List<PrjChart> getContractList(PrjChartsSearchVO prjChartsSearchVO) {
        return getOverList(prjChartsDao.getContractList(prjChartsSearchVO),prjChartsSearchVO.getPrj_base_info_id());
    }

    public List<PrjChart> getHandoverList(PrjChartsSearchVO prjChartsSearchVO) {
        return getOverList(prjChartsDao.getHandoverList(prjChartsSearchVO),prjChartsSearchVO.getPrj_base_info_id());
    }

    private List<PrjChart> getOverList(List<PrjChart> list, int project_id) {
        List<PrjChart> overList = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();

        for (PrjChart prjChart : list) {
            map.put(prjChart.getTitle(), prjChart.getCountLeftDay());
        }

        Date start_date = DateUtil.stringToDate(prjChartsDao.getMinDate(project_id), "yyyy-MM-dd");
        Date end_date = DateUtil.stringToDate(prjChartsDao.getMaxDate(project_id), "yyyy-MM-dd");

        int leftCount = 0;
        while (start_date.getTime() < end_date.getTime()) {
            String key_date = DateUtil.dateToString(start_date, "yyyy-MM-dd");
            if (!map.containsKey(key_date)) {
                map.put(key_date, leftCount);
            }else{
                leftCount = map.get(key_date);
            }
            start_date.setTime(start_date.getTime() + 24 * 3600 * 1000);
        }

        List<Map.Entry<String, Integer>> infoIds = new ArrayList<>(map.entrySet());

        infoIds.sort(Comparator.comparing(o -> (o.getKey())));
        for(Map.Entry<String,Integer> mapping:infoIds){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
            overList.add(new PrjChart(mapping.getKey(), mapping.getValue()));

        }

        return overList;
    }

    public List<PrjChart> getGroupInHostList(PrjChartsSearchVO prjChartsSearchVO) {
        return prjChartsDao.getGroupInHostList(prjChartsSearchVO);
    }
    public List<PrjChart> getGroupSignList(PrjChartsSearchVO prjChartsSearchVO) {
        return prjChartsDao.getGroupSignList(prjChartsSearchVO);
    }
    public List<PrjChart> getGroupHandoverList(PrjChartsSearchVO prjChartsSearchVO) {
        return prjChartsDao.getGroupHandoverList(prjChartsSearchVO);
    }
    public List<PrjChart> getGroupMoneyList(PrjChartsSearchVO prjChartsSearchVO) {
        return prjChartsDao.getGroupMoneyList(prjChartsSearchVO);
    }

    public int getTotalHomes(int prj_project_id){
        return prjChartsDao.getTotalHomes(prj_project_id);
    }
}
