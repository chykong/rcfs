package com.balance.base.controller;

import com.balance.base.model.BaseHouse;
import com.balance.base.service.BaseHouseService;
import com.balance.base.vo.BaseHouseSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.page.PageNavigate;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 杨康康 on 2018/3/3.
 */
@RequestMapping("/base/house")
@Controller
public class BaseHousecontroller {
    @Autowired
    private BaseHouseService baseHouseService;
    @Autowired
    private PubConfig pubConfig;
    /**
     * 进入用户管理界面
     *
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, BaseHouseSearchVO baseHouseSearchVO) {
        ModelAndView mv = new ModelAndView();
        String url = createUrl(baseHouseSearchVO);
        baseHouseSearchVO.setPrjBaseInfoId(SessionUtil.getUserSession(request).getCurrent_project_id());//项目id
        int recordCount = baseHouseService.count(baseHouseSearchVO);// 获取查询总数
        PageNavigate pageNavigate = new PageNavigate(url, baseHouseSearchVO.getPageIndex(), baseHouseSearchVO.getPageSize(), recordCount);//定义分页对象
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        List<BaseHouse> list = baseHouseService.housesList(baseHouseSearchVO);
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/base/house");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(BaseHouseSearchVO baseHouseSearchVO) {
        String url = pubConfig.getDynamicServer() + "/base/house/index.htm?";
        if (StringUtil.isNotNullOrEmpty(baseHouseSearchVO.getName())) {
            url += "&name=" + baseHouseSearchVO.getName();
        }
        return url;
    }

    /**
     * 进入添加界面
     *
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/base/houseAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 进入修改界面
     *
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        BaseHouse baseHouse = baseHouseService.get(id);
        mv.addObject("baseHouse", baseHouse);
        mv.setViewName("/base/houseUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, BaseHouse baseHouse) {
        baseHouse.setPrjBaseInfoId(SessionUtil.getUserSession(request).getCurrent_project_id());
        int flag = baseHouseService.addHouse(baseHouse);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("户型新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("户型新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, BaseHouse baseHouse) {
        int flag = baseHouseService.updateHouse(baseHouse);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("户型修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("户型修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int flag = baseHouseService.deleteHouse(request.getParameter("id"));
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("户型删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("户型删除成功");
    }


}
