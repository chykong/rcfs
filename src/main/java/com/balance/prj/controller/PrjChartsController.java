package com.balance.prj.controller;

import com.balance.common.vo.ComboboxVO;
import com.balance.prj.model.PrjChart;
import com.balance.prj.service.PrjChartsService;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.vo.ChartsDataVO;
import com.balance.prj.vo.PrjChartsSearchVO;
import com.balance.util.number.NumberUtil;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.ChartsUtil;
import com.balance.util.web.WebTag;
import com.balance.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/5.
 */
@RequestMapping("/prj/charts")
@Controller
public class PrjChartsController {
    @Autowired
    private PrjChartsService prjChartsService;
    @Autowired
    private PrjPreallocationService preallocationService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/prj/charts");
        return mv;
    }

    @RequestMapping("/entiretyIndex")
    public ModelAndView entiretyIndex(HttpServletRequest request, HttpServletResponse response, PrjChartsSearchVO prjChartsSearchVO) {
        ModelAndView mv = new ModelAndView();

        prjChartsSearchVO.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        List<PrjChart> inHostList = prjChartsService.getInHostList(prjChartsSearchVO);
        List<PrjChart> contractList = prjChartsService.getContractList(prjChartsSearchVO);
        List<PrjChart> handoverList = prjChartsService.getHandoverList(prjChartsSearchVO);

        mv.addObject("inHostList", inHostList);
        mv.addObject("contractList", contractList);
        mv.addObject("handoverList", handoverList);

        mv.setViewName("/prj/entiretyChartIndex");
        return mv;
    }

    /**
     * 整体进度图
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/entireIndex")
    public ModelAndView entireIndex(HttpServletRequest request, HttpServletResponse response, Integer type) {
        ModelAndView mv = new ModelAndView();
        if (type == null) type = 1;//默认按户数
        mv.addObject("type", type);
        mv.addObject("typeName", WebTag.getChartTitleByType(type));
        mv.setViewName("/prj/entireCharts");
        return mv;
    }

    /**
     * 生成整体进度图的json
     *
     * @param request
     * @param response
     * @param type
     */
    @RequestMapping("/getEntire")
    public void getGroupInHost(HttpServletRequest request, HttpServletResponse response, Integer type) {
        if (type == null) type = 1;//默认按户数
        int project_id = SessionUtil.getUserSession(request).getCurrent_project_id();//项目id
        String json = prjChartsService.createEntireJson(project_id, type);
        WebUtil.out(response, json);
    }

    /**
     * 分组进度图
     */
    @RequestMapping("/groupIndex")
    public ModelAndView groupIndex(HttpServletRequest request, HttpServletResponse response, PrjChartsSearchVO prjChartsSearchVO) {
        ModelAndView mv = new ModelAndView();
        if (prjChartsSearchVO.getType() == null) prjChartsSearchVO.setType(1);
        List<ComboboxVO> townList = preallocationService.getTown(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("townList", townList);
        mv.addObject("type", prjChartsSearchVO.getType());//类型
        mv.addObject("typeName", WebTag.getChartTitleByType(prjChartsSearchVO.getType()));//类型名称
        mv.setViewName("/prj/groupChartIndex");
        return mv;
    }

    @RequestMapping("/getGroup")
    public void getGroupInHost(HttpServletRequest request, HttpServletResponse response, PrjChartsSearchVO prjChartsSearchVO) {
        if (prjChartsSearchVO.getType() == null) prjChartsSearchVO.setType(1);//默认按户数
        int project_id = SessionUtil.getUserSession(request).getCurrent_project_id();
        prjChartsSearchVO.setPrj_base_info_id(project_id);
        List<PrjChart> list = new ArrayList<>();
        float total_homes = prjChartsService.getTotalHomes(prjChartsSearchVO);
        float over_homes = 0;

        ChartsDataVO vo = new ChartsDataVO();
        String title1 = "";
        if (prjChartsSearchVO.getSearch_type() != null) {
            switch (prjChartsSearchVO.getSearch_type()) {
                case 1:
                    title1 = "入户";
                    list = prjChartsService.getGroupInHostList(prjChartsSearchVO);
                    break;
                case 2:
                    title1 = "签约";
                    list = prjChartsService.getGroupSignList(prjChartsSearchVO);
                    break;
                case 3:
                    title1 = "交房";
                    list = prjChartsService.getGroupHandoverList(prjChartsSearchVO);
                    break;
                case 4:
                    title1 = "放款";
                    list = prjChartsService.getGroupMoneyList(prjChartsSearchVO);
                    break;
                default:
                    title1 = "入户";
                    list = prjChartsService.getGroupInHostList(prjChartsSearchVO);
                    break;
            }
        }
        vo.setBarTitle1("当日" + title1);//
        vo.setBarTitle2("累计" + title1);//

        float[] today_int = new float[list.size()];
        float[] total_int = new float[list.size()];
        String[] groups = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            PrjChart prjChart = list.get(i);
            today_int[i] = prjChart.getToday();
            total_int[i] = prjChart.getTotal();
            over_homes += prjChart.getTotal();
            groups[i] = prjChart.getGroups();
        }
        vo.setBarData1(today_int);//今日
        vo.setBarData2(total_int);//累计
        vo.setBarCategories(groups);//整体分组
        vo.setGuageTitle(title1 + "累计完成度");//仪表盘标题

        if (over_homes == 0) {
            vo.setGuageData(0);
        } else {
            vo.setGuageData(NumberUtil.formatFloat((float) over_homes * 100 / total_homes));//仪表单数据
        }
        vo.setType(prjChartsSearchVO.getType());//类型
        String json = ChartsUtil.createChartsJson(vo, total_homes);
        WebUtil.out(response, json);
    }

    @RequestMapping("/loaddata")
    public void loaddata(HttpServletRequest request, HttpServletResponse response) {
///*        String json = "{\"categories\": [\"一组\",\"二组\",\"三组\",\"四组\",\"五组\"], \"data\": [{" +
//                "\"name\":'当日交房'," +
//                "\"type\":\"bar\"," +
//                "\"data\":[2, 4,5,2,8]" +
//                "}," +
//                "{" +
//                "\"name\":'累计交房'," +
//                "\"type\":\"bar\"," +
//                "\"data\":[12,33,44,22,32]" +
//                "}],\"data2\":[{" +
//                "            name: '交房累计完成度'," +
//                "            type: 'gauge'," +
//                "            detail: {formatter:'{value}%'}," +
//                "            data: [{value: 80, name: '完成率'}]" +
//                "        }]}";*/
//
//        ChartsDataVO vo = new ChartsDataVO();
//        vo.setBarTitle1("当日入户");//
//        vo.setBarTitle2("累计入户");//
//        vo.setBarData1(new int[]{2, 4, 5, 2, 8});//第一组数据
//        vo.setBarData2(new int[]{12, 33, 44, 22, 32});//第二组数据
//        vo.setBarCategories(new String[]{"一组", "二组", "三组", "四组", "五组"});//整体分组
//        vo.setGuageTitle("入户累计完成度");//仪表盘标题
//        vo.setGuageData(76);//仪表单数据
//        String json = ChartsUtil.createChartsJson(vo);
//        System.out.println(json);
//        WebUtil.out(response, json);
    }

}
