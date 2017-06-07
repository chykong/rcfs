package com.balance.prj.controller;

import com.balance.prj.model.PrjChart;
import com.balance.prj.service.PrjChartsService;
import com.balance.prj.vo.ChartsDataVO;
import com.balance.prj.vo.PrjChartsSearchVO;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.ChartsUtil;
import com.balance.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        mv.addObject("inHostList",inHostList);
        mv.addObject("contractList",contractList);
        mv.addObject("handoverList",handoverList);

        mv.setViewName("/prj/entiretyChartIndex");
        return mv;
    }

    @RequestMapping("/loaddata")
    public void loaddata(HttpServletRequest request, HttpServletResponse response) {
/*        String json = "{\"categories\": [\"一组\",\"二组\",\"三组\",\"四组\",\"五组\"], \"data\": [{" +
                "\"name\":'当日交房'," +
                "\"type\":\"bar\"," +
                "\"data\":[2, 4,5,2,8]" +
                "}," +
                "{" +
                "\"name\":'累计交房'," +
                "\"type\":\"bar\"," +
                "\"data\":[12,33,44,22,32]" +
                "}],\"data2\":[{" +
                "            name: '交房累计完成度'," +
                "            type: 'gauge'," +
                "            detail: {formatter:'{value}%'}," +
                "            data: [{value: 80, name: '完成率'}]" +
                "        }]}";*/

        ChartsDataVO vo = new ChartsDataVO();
        vo.setBarTitle1("当日交房");//
        vo.setBarTitle2("累计交房");//
        vo.setBarData1(new int[]{2, 4, 5, 2, 8});//第一组数据
        vo.setBarData2(new int[]{12, 33, 44, 22, 32});//第二组数据
        vo.setBarCategories(new String[]{"一组", "二组", "三组", "四组", "五组"});//整体分组
        vo.setGuageTitle("交房累计完成度");//仪表盘标题
        vo.setGuageData(76);//仪表单数据
        String json = ChartsUtil.createChartsJson(vo);
        System.out.println(json);
        WebUtil.out(response, json);
    }
}
