package com.balance.base.controller;

/**
 * Created by  杨康康 on 2017/8/29.
 * 安置房概况  controller
 */

import com.balance.base.model.BasePlacement;
import com.balance.base.service.BasePlacementService;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/base/placement")
@Controller
public class BasePlacementController {

    @Autowired
    private BasePlacementService basePlacementService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入概况页面
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        int prj_base_info_id = SessionUtil.getUserSession(request).getCurrent_project_id();
        BasePlacement model = basePlacementService.get(prj_base_info_id);
        mv.addObject("model", model);
        mv.setViewName("/base/placement");
        BackUrlUtil.createBackUrl(mv, request, pubConfig.getDynamicServer() + "/base/placement/index.htm");// 设置返回url
        return mv;
    }

    /**
     * 更新页面
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, BasePlacement basePlacement) {
        int flag = basePlacementService.update(basePlacement);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("消息新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("消息新增成功");
    }

    /**
     * 更新页面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        int prj_base_info_id = SessionUtil.getUserSession(request).getCurrent_project_id();
        BasePlacement basePlacement = basePlacementService.get(prj_base_info_id);
        if (basePlacement == null) {
            basePlacement = new BasePlacement();
            basePlacement.setPrj_base_info_id(prj_base_info_id);
        }
        mv.addObject("basePlacement", basePlacement);
        mv.setViewName("/base/placementUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }
}
