package com.balance.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.balance.base.model.BaseCompany;
import com.balance.base.service.BaseCompanyService;
import com.balance.base.vo.BaseCompanySearchVO;
import com.balance.prj.model.PrjBaseinfo;
import com.balance.prj.service.PrjBaseinfoService;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.controller.BaseController;
import com.balance.util.page.PageNavigate;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
@RequestMapping("/base/project")
@Controller
public class BaseProjectintroController extends BaseController {

    @Autowired
    private BaseCompanyService baseCompanyService;
    @Autowired
    private PrjBaseinfoService prjBaseinfoService;

    /**
     * 进入用户管理界面
     *
     * @return
     */
    @RequestMapping("/intro")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjBaseinfo prjBaseinfoVO) {
        ModelAndView mv = new ModelAndView();
        PrjBaseinfo prjBaseinfo  = prjBaseinfoService.get(24);
//        String url = createUrl(prjBaseinfoVO);
        mv.addObject("prjBaseinfo", prjBaseinfo);// 把获取的对象放到mv里面
        mv.setViewName("/base/projectIntro");// 跳转至指定页面
//        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(BaseCompanySearchVO baseCompanySearchVO) {
        String url = "/base/company/index.htm?";
        return url;
    }

    /**
     * 进入添加用户界面
     *
     * @param request
     * @param response
     * @param
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/base/projectIntroAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 进入修改用户界面
     *
     * @param request
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        PrjBaseinfo prjBaseinfo = prjBaseinfoService.get(id);
        mv.addObject("prjBaseinfo", prjBaseinfo);
        mv.setViewName("/base/projectIntroUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjBaseinfo prjBaseinfo) {
    	prjBaseinfo.setCreated_by(SessionUtil.getUserSession(request).getRealname());
        int flag = prjBaseinfoService.add(prjBaseinfo);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("项目简介新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("项目简介新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, PrjBaseinfo prjBaseinfo) {
        int flag = prjBaseinfoService.update(prjBaseinfo);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("公司修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("公司修改成功");
    }
}
