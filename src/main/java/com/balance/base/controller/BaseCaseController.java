package com.balance.base.controller;

import com.balance.base.model.BaseCase;
import com.balance.base.service.BaseCaseService;
import com.balance.base.vo.BaseCaseSearchVO;
import com.balance.prj.model.PrjBrief;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.controller.BaseController;
import com.balance.util.date.DateUtil;
import com.balance.util.page.PageNavigate;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;

/**
 * Author   刘凯
 * Date  2017/6/10.
 */
@RequestMapping("/base/case")
@Controller
public class BaseCaseController extends BaseController {

    @Autowired
    private BaseCaseService baseCaseService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入经典项目界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, BaseCaseSearchVO baseCaseSearchVO) {
        ModelAndView mv = new ModelAndView();

        int recordCount = baseCaseService.listCount(baseCaseSearchVO);// 获取查询总数
        String url = createUrl(baseCaseSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, baseCaseSearchVO.getPageIndex(), baseCaseSearchVO.getPageSize(), recordCount);//定义分页对象
        List<BaseCase> list = baseCaseService.list(baseCaseSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/base/case");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(BaseCaseSearchVO baseCaseSearchVO) {
        String url = pubConfig.getDynamicServer() + "/base/case/index.htm?";
        return url;
    }

    /**
     * 进入添加经典项目界面
     *
     * @param request
     * @param response
     * @param
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/base/caseAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 进入修改经典项目界面
     *
     * @param request
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        BaseCase baseCase = baseCaseService.get(id);
        mv.addObject("baseCase", baseCase);
        mv.setViewName("/base/caseUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, BaseCase baseCase) {
        baseCase.setCreated_by(SessionUtil.getUserSession(request).getRealname());
        int flag = baseCaseService.add(baseCase);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("经典项目新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("经典项目新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, BaseCase baseCase) {
        int flag = baseCaseService.update(baseCase);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("经典项目修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("经典项目修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = baseCaseService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("经典项目删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("经典项目删除成功");
    }
    /**
     * 详细
     */
    @RequestMapping("/toDetail")
    public ModelAndView toDetail(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        BaseCase baseCase = baseCaseService.get(id);
        mv.addObject("baseCase", baseCase);
        mv.setViewName("/base/caseDetail");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }
}
