package com.balance.api.controller;

import com.balance.api.dto.ChartDTO;
import com.balance.api.dto.SimpleProjectDTO;
import com.balance.api.service.StatisticsApiService;
import com.balance.prj.model.PrjBaseinfo;
import com.balance.prj.service.PrjBaseinfoService;
import com.balance.prj.service.PrjChartsService;
import com.balance.prj.vo.PrjChartsSearchVO;
import com.balance.util.session.SessionUtil;
import com.balance.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 * 选定项目的进度API
 */

@RequestMapping("/api/statistics")
@RestController
public class StatisticsApiController {
    @Autowired
    private StatisticsApiService statisticsApiService;
    @Autowired
    private PrjChartsService prjChartsService;
    @Autowired
    private PrjBaseinfoService prjBaseinfoService;

    /**
     * 获取进度图
     *
     * @return
     */
    @RequestMapping("charts")
    public ChartDTO charts(HttpServletRequest request, Integer projectId) {
        if (projectId == null) {
            projectId = SessionUtil.getAppSession(request).getCurrent_project_id();
        }
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "项目id是必须的");
        }
        String current_land_name = SessionUtil.getAppSession(request).getCurrent_land_name();

        ChartDTO chartDTO = statisticsApiService.create(projectId, current_land_name);
        return chartDTO;
    }


    /**
     * 获取进度图
     *
     * @return
     */
    @RequestMapping("getChartsData")
    public void getChartsData(HttpServletRequest request, HttpServletResponse response, Integer projectId, Integer type, Integer search_type) {
        if (projectId == null) {
            projectId = SessionUtil.getAppSession(request).getCurrent_project_id();
        }
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "项目id是必须的");
        }
        String current_land_name = SessionUtil.getAppSession(request).getCurrent_land_name();
        PrjChartsSearchVO prjChartsSearchVO = new PrjChartsSearchVO();
        prjChartsSearchVO.setPrj_base_info_id(projectId);//项目id
        prjChartsSearchVO.setCurrent_land_name(current_land_name);//土地性质
        if (type == null)
            prjChartsSearchVO.setType(1);
        else
            prjChartsSearchVO.setType(type);
        if (search_type == null)
            prjChartsSearchVO.setSearch_type(1);
        else
            prjChartsSearchVO.setSearch_type(search_type);
        PrjBaseinfo prjBaseinfo = prjBaseinfoService.get(projectId);
        String json = prjChartsService.createChartsData(prjChartsSearchVO,prjBaseinfo.getArea_type());
        WebUtil.out(response, json);
    }

    /**
     * 获取当前进度
     *
     * @return
     */
    @RequestMapping("getProgress")
    public SimpleProjectDTO getProgress(HttpServletRequest request, HttpServletResponse response, Integer projectId, Integer type, Integer search_type) {
        if (projectId == null) {
            projectId = SessionUtil.getAppSession(request).getCurrent_project_id();
        }
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "项目id是必须的");
        }
        PrjBaseinfo prjBaseinfo = prjBaseinfoService.get(projectId);
        SimpleProjectDTO dto = new SimpleProjectDTO(prjBaseinfo.getId(), prjBaseinfo.getPrj_name(), prjBaseinfo.getProgress());
        return dto;
    }
}
