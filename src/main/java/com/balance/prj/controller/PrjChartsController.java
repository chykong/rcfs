package com.balance.prj.controller;

import com.balance.util.web.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author  孔垂云
 * Date  2017/6/5.
 */
@RequestMapping("/prj/charts")
@Controller
public class PrjChartsController {

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/prj/charts");
        return mv;
    }

    @RequestMapping("/loaddata")
    public void loaddata(HttpServletRequest request, HttpServletResponse response) {
        String json = "{\"categories\": [\"一组\",\"二组\",\"三组\",\"四组\",\"五组\"], \"data\": [{" +
                "\"name\":'当日交房'," +
                "\"type\":\"bar\"," +
                "\"data\":[2, 4,5,2,8]" +
                "}," +
                "{" +
                "\"name\":'累计交房'," +
                "\"type\":\"bar\"," +
                "\"data\":[12,33,44,22,32]" +
                "}],\"data2\":{" +
                "            name: '交房累计完成度'," +
                "            type: 'gauge'," +
                "            detail: {formatter:'{value}%'},\n" +
                "            data: [{value: 50, name: '完成率'}]\n" +
                "        }}";
        System.out.println(json);
        WebUtil.out(response, json);
    }
}
