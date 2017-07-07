package com.balance.api.service;

import com.balance.api.dto.ChartDTO;
import com.balance.prj.dao.PrjChartsDao;
import com.balance.prj.model.PrjChart;
import com.balance.prj.service.PrjChartsService;
import com.balance.prj.vo.ChartsDataVO;
import com.balance.prj.vo.PrjChartsSearchVO;
import com.balance.util.number.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @autho 孔垂云
 * @date 2017/6/24.
 */
@Service
public class StatisticsApiService {
    @Autowired
    PrjChartsDao prjChartsDao;
    @Autowired
    PrjChartsService prjChartsService;

    /**
     * 根据项目id获取图表数据
     *
     * @param projectId
     * @param current_land_name 土地性质
     * @return
     */
    public ChartDTO create(int projectId, String current_land_name) {
        ChartDTO chartDTO = new ChartDTO();
        PrjChartsSearchVO prjChartsSearchVO = new PrjChartsSearchVO();
        prjChartsSearchVO.setPrj_base_info_id(projectId);
        prjChartsSearchVO.setCurrent_land_name(current_land_name);
        prjChartsSearchVO.setType(1);//按户数

        prjChartsSearchVO.setSearch_type(1);//入户累计
        float total = prjChartsDao.getTotalHomes(prjChartsSearchVO);
        chartDTO.setIn_host_count(total);//入户按户数总数
        float over = createOver(prjChartsSearchVO);//已完成
        chartDTO.setIn_host_percent(NumberUtil.calPercent(over, total));
        chartDTO.setIn_host_over(over);//已完成


        prjChartsSearchVO.setSearch_type(2);//签约累计
        total = prjChartsDao.getTotalHomes(prjChartsSearchVO);
        chartDTO.setSign_host_count(total);//签约按户数总数
        over = createOver(prjChartsSearchVO);//已完成
        chartDTO.setSign_host_percent(NumberUtil.calPercent(over, total));
        chartDTO.setSign_host_over(over);//已完成

        prjChartsSearchVO.setSearch_type(3);//交房累计
        total = prjChartsDao.getTotalHomes(prjChartsSearchVO);
        chartDTO.setHandover_host_count(total);//交房按户数总数
        over = createOver(prjChartsSearchVO);//已完成
        chartDTO.setHandover_host_percent(NumberUtil.calPercent(over, total));
        chartDTO.setHandover_host_over(over);//已完成
        /////
        prjChartsSearchVO.setType(2);//按占地面积
        prjChartsSearchVO.setSearch_type(1);//入户累计
        total = prjChartsDao.getTotalHomes(prjChartsSearchVO);
        chartDTO.setIn_landarea_count(total);//入户按户数总数
        over = createOver(prjChartsSearchVO);//已完成
        chartDTO.setIn_landarea_percent(NumberUtil.calPercent(over, total));
        chartDTO.setIn_landarea_over(over);//已完成


        prjChartsSearchVO.setSearch_type(2);//签约累计
        total = prjChartsDao.getTotalHomes(prjChartsSearchVO);
        chartDTO.setSign_landarea_count(total);//签约按户数总数
        over = createOver(prjChartsSearchVO);//已完成
        chartDTO.setSign_landarea_percent(NumberUtil.calPercent(over, total));
        chartDTO.setSign_landarea_over(over);//已完成


        prjChartsSearchVO.setSearch_type(3);//交房累计
        total = prjChartsDao.getTotalHomes(prjChartsSearchVO);
        chartDTO.setHandover_landarea_count(total);//交房按户数总数
        over = createOver(prjChartsSearchVO);//已完成
        chartDTO.setHandover_landarea_percent(NumberUtil.calPercent(over, total));
        chartDTO.setHandover_landarea_over(over);//已完成
        /////
        prjChartsSearchVO.setType(3);//按建筑面积
        prjChartsSearchVO.setSearch_type(1);//入户累计
        total = prjChartsDao.getTotalHomes(prjChartsSearchVO);
        chartDTO.setIn_homestead_count(total);//入户按户数总数
        over = createOver(prjChartsSearchVO);//已完成
        chartDTO.setIn_homestead_percent(NumberUtil.calPercent(over, total));
        chartDTO.setIn_homestead_over(over);//已完成

        prjChartsSearchVO.setSearch_type(2);//签约累计
        total = prjChartsDao.getTotalHomes(prjChartsSearchVO);
        chartDTO.setSign_homestead_count(total);//签约按户数总数
        over = createOver(prjChartsSearchVO);//已完成
        chartDTO.setSign_homestead_percent(NumberUtil.calPercent(over, total));
        chartDTO.setSign_homestead_over(over);////已完成

        prjChartsSearchVO.setSearch_type(3);//交房累计
        total = prjChartsDao.getTotalHomes(prjChartsSearchVO);
        chartDTO.setHandover_homestead_count(total);//交房按户数总数
        over = createOver(prjChartsSearchVO);//已完成
        chartDTO.setHandover_homestead_percent(NumberUtil.calPercent(over, total));
        chartDTO.setHandover_homestead_over(over);////已完成

        return chartDTO;
    }


    /**
     * 计算已完成数据
     *
     * @param prjChartsSearchVO
     * @return
     */
    private float createOver(PrjChartsSearchVO prjChartsSearchVO) {

        List<PrjChart> list = new ArrayList<>();
        float total_homes = prjChartsService.getTotalHomes(prjChartsSearchVO);
        float over_homes = 0;

        ChartsDataVO vo = new ChartsDataVO();
        if (prjChartsSearchVO.getSearch_type() != null) {
            switch (prjChartsSearchVO.getSearch_type()) {
                case 1:
                    list = prjChartsService.listGroup(prjChartsSearchVO,1);
                    break;
                case 2:
                    list = prjChartsService.listGroup(prjChartsSearchVO,2);
                    break;
                case 3:
                    list = prjChartsService.listGroup(prjChartsSearchVO,3);
                    break;
                default:
                    list = prjChartsService.listGroup(prjChartsSearchVO,4);
                    break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            PrjChart prjChart = list.get(i);
            over_homes += prjChart.getTotal();
        }
        return over_homes;
    }


}
