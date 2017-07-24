package com.balance.prj.service;

import com.balance.prj.dao.PrjChartsDao;
import com.balance.prj.dao.PrjGroupDao;
import com.balance.prj.dao.PrjSectionDao;
import com.balance.prj.model.PrjChart;
import com.balance.prj.model.PrjGroup;
import com.balance.prj.model.PrjSection;
import com.balance.prj.vo.EntireStatVO;
import com.balance.prj.vo.PrjChartsSearchVO;
import com.balance.util.date.DateUtil;
import com.balance.util.json.JsonUtil;
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
    @Autowired
    private PrjGroupDao prjGroupDao;
    @Autowired
    private PrjSectionDao prjSectionDao;

    public List<PrjChart> getInHostList(PrjChartsSearchVO prjChartsSearchVO) {
        return getOverList(prjChartsDao.getInHostList(prjChartsSearchVO), prjChartsSearchVO.getPrj_base_info_id());
    }

    public List<PrjChart> getContractList(PrjChartsSearchVO prjChartsSearchVO) {
        return getOverList(prjChartsDao.getContractList(prjChartsSearchVO), prjChartsSearchVO.getPrj_base_info_id());
    }

    public List<PrjChart> getHandoverList(PrjChartsSearchVO prjChartsSearchVO) {
        return getOverList(prjChartsDao.getHandoverList(prjChartsSearchVO), prjChartsSearchVO.getPrj_base_info_id());
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
            } else {
                leftCount = map.get(key_date);

            }
            start_date.setTime(start_date.getTime() + 24 * 3600 * 1000);
        }

        List<Map.Entry<String, Integer>> infoIds = new ArrayList<>(map.entrySet());

        infoIds.sort(Comparator.comparing(o -> (o.getKey())));
        for (Map.Entry<String, Integer> mapping : infoIds) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
            overList.add(new PrjChart(mapping.getKey(), mapping.getValue()));

        }

        return overList;
    }

    /**
     * 获取总的户数
     *
     * @param prjChartsSearchVO
     * @return
     */
    public float getTotalHomes(PrjChartsSearchVO prjChartsSearchVO) {
        return prjChartsDao.getTotalHomes(prjChartsSearchVO);
    }


    /**
     * 生成整体进度图的json
     * 生成的json包括两部分，一部分是日期数组，一部分是数据list
     *
     * @param type
     * @param land_property 土地使用属性
     * @return
     */
    public String createEntireJson(int prj_base_info_id, String land_property, int type) {
        String s_date = DateUtil.getOpeDate(DateUtil.getSystemDate(), -29);//定义开始时间
        String e_date = DateUtil.getSystemDate();//定义结束时间
        String[] dates = new String[30];//定义30天的时间段
        for (int i = 0; i < 30; i++) {
            dates[i] = DateUtil.getOpeDate(s_date, i);
        }
        //计算入户
        List<EntireStatVO> listIn_host = prjChartsDao.listEntireByType(prj_base_info_id, land_property, s_date, e_date, type, 1);
        float existIn_host = prjChartsDao.getExist(prj_base_info_id, land_property, s_date, type, 1);
        float[] in_host = createDataArr(dates, listIn_host, existIn_host);//计算入户数
        //计算签约
        List<EntireStatVO> listSigned = prjChartsDao.listEntireByType(prj_base_info_id, land_property, s_date, e_date, type, 2);
        float existSigned = prjChartsDao.getExist(prj_base_info_id, land_property, s_date, type, 2);
        float[] signed = createDataArr(dates, listSigned, existSigned);//计算签约数
        //计算交房
        List<EntireStatVO> listHand = prjChartsDao.listEntireByType(prj_base_info_id, land_property, s_date, e_date, type, 3);
        float existHand = prjChartsDao.getExist(prj_base_info_id, land_property, s_date, type, 3);
        float[] hand = createDataArr(dates, listHand, existHand);//计算交房数

        StringBuilder sb = new StringBuilder();
        sb.append("{\"categories\":" + JsonUtil.toStr(dates) + ",");
        sb.append("\"lineData\":[{\"name\":\"入户\"," +
                "\"type\":\"line\"," +
                "\"data\":" + JsonUtil.toStr(in_host) + "},{\"name\":\"签约\"," +
                "\"type\":\"line\"," +
                "\"data\":" + JsonUtil.toStr(signed) + "},{" +
                "\"name\":\"交房\"," +
                "\"type\":\"line\"," +
                "\"data\":" + JsonUtil.toStr(hand) + "" +
                "}]");
        sb.append("}");
        return sb.toString();
    }

    /**
     * 根据数据和起始值，计算30天的数据
     *
     * @param dates
     * @param list
     * @param exist
     * @return
     */
    private float[] createDataArr(String[] dates, List<EntireStatVO> list, float exist) {
        float[] dataArr = new float[dates.length];
        for (int i = 0; i < dates.length; i++) {
            //计算方法是从exist开始，加上当天的数据为当天的合计数据
            float today = 0;//先计算当前有没有数据
            for (EntireStatVO vo : list) {
                if (vo.getDate().equals(dates[i])) {
                    today = vo.getData();
                    break;
                }
            }
            dataArr[i] = exist + today;
            exist += today;
        }
        return dataArr;
    }


    /**
     * 分组进度图
     *
     * @return
     */
    public List<PrjChart> listGroup(PrjChartsSearchVO prjChartsSearchVO) {
        List<PrjChart> list = new ArrayList<>();
        List<PrjGroup> listGroup = prjGroupDao.listByPrj_base_info_id(prjChartsSearchVO.getPrj_base_info_id());//所有组
        List<PrjChart> listTotal = prjChartsDao.getGroupTotalList(prjChartsSearchVO);//全部
        List<PrjChart> listToday = prjChartsDao.getGroupTodayList(prjChartsSearchVO);//昨日
        for (PrjGroup prjGroup : listGroup) {
            PrjChart prjChart = new PrjChart();
            prjChart.setGroups(prjGroup.getName());
            prjChart.setTotal(0);
            prjChart.setToday(0);
            for (PrjChart total : listTotal) {
                if (total.getGroups().equals(prjGroup.getName())) {
                    prjChart.setTotal(total.getTotal());
                }
            }
            for (PrjChart today : listToday) {
                if (today.getGroups().equals(prjGroup.getName())) {
                    prjChart.setToday(today.getToday());
                }
            }
            list.add(prjChart);
        }
        return list;
    }

    /**
     * 标段进度图
     *
     * @return
     */
    public List<PrjChart> listSection(PrjChartsSearchVO prjChartsSearchVO) {
        List<PrjChart> list = new ArrayList<>();
        List<PrjSection> listSection = prjSectionDao.listByprj_base_info_id(prjChartsSearchVO.getPrj_base_info_id());//所有标段
        List<PrjChart> listIs = prjChartsDao.getSectionIsList(prjChartsSearchVO);//分标段已完成
        List<PrjChart> listNo = prjChartsDao.getSectionNoList(prjChartsSearchVO);//分标段未完成
        for (PrjSection prjSection : listSection) {
            PrjChart prjChart = new PrjChart();
            prjChart.setSection(prjSection.getName());
            prjChart.setIs(0);
            prjChart.setNo(0);
            for (PrjChart is : listIs) {
                if (is.getSection().equals(prjSection.getName())) {
                    prjChart.setIs(is.getIs());
                }
            }
            for (PrjChart no : listNo) {
                if (no.getSection().equals(prjSection.getName())) {
                    prjChart.setNo(no.getNo());
                }
            }
            list.add(prjChart);
        }
        return list;
    }
}
