package com.balance.api.controller;

import com.balance.api.dto.ChartDTO;
import com.balance.api.service.StatisticsApiService;
import com.balance.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

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
}
