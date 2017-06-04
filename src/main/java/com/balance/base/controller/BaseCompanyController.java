package com.balance.base.controller;

import com.balance.base.model.BaseCompany;
import com.balance.base.service.BaseCompanyService;
import com.balance.base.vo.BaseCompanySearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.controller.BaseController;
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
 * Author  孔垂云
 * Date  2017/6/3.
 */
@RequestMapping("/base/company")
@Controller
public class BaseCompanyController extends BaseController {

    @Autowired
    private BaseCompanyService baseCompanyService;

    /**
     * 进入用户管理界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, BaseCompanySearchVO baseCompanySearchVO) {
        ModelAndView mv = new ModelAndView();
        int recordCount = baseCompanyService.listCount(baseCompanySearchVO);// 获取查询总数
        String url = createUrl(baseCompanySearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, baseCompanySearchVO.getPageIndex(), baseCompanySearchVO.getPageSize(), recordCount);//定义分页对象
        List<BaseCompany> list = baseCompanyService.list(baseCompanySearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/base/company");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
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
        mv.setViewName("/base/companyAdd");
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
        BaseCompany baseCompany = baseCompanyService.get(id);
        mv.addObject("baseCompany", baseCompany);
        mv.setViewName("/base/companyUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, BaseCompany baseCompany) {
        baseCompany.setCreated_by(SessionUtil.getUserSession(request).getRealname());
        int flag = baseCompanyService.add(baseCompany);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("公司新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("公司新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, BaseCompany baseCompany) {
        int flag = baseCompanyService.update(baseCompany);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("公司修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("公司修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = baseCompanyService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("公司删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("公司删除成功");
    }
}
