package com.balance.prj.controller;

import com.balance.prj.model.PrjSection;
import com.balance.prj.service.PrjBaseinfoService;
import com.balance.prj.service.PrjSectionService;
import com.balance.prj.vo.PrjSectionSearchVO;
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
 * Date  2017/6/4.
 */
@RequestMapping("/prj/section")
@Controller
public class PrjSectionController extends BaseController {


    @Autowired
    private PrjSectionService prjSectionService;
    @Autowired
    private PrjBaseinfoService prjBaseinfoService;

    /**
     * 进入用户管理界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjSectionSearchVO prjSectionSearchVO) {
        ModelAndView mv = new ModelAndView();
        int recordCount = prjSectionService.listCount(prjSectionSearchVO);// 获取查询总数
        String url = createUrl(prjSectionSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, prjSectionSearchVO.getPageIndex(), prjSectionSearchVO.getPageSize(), recordCount);//定义分页对象
        List<PrjSection> list = prjSectionService.list(prjSectionSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/base/company");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(PrjSectionSearchVO prjSectionSearchVO) {
        String url = "/prj/section/index.htm?";
        return url;
    }

    /**
     * 进入添加界面
     *
     * @param request
     * @param response
     * @param
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        PrjSection prjSection = new PrjSection();
        mv.addObject("prjSection", prjSection);
        mv.addObject("listProjects", prjBaseinfoService.list());
        mv.setViewName("/prj/sectionAdd");
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
        PrjSection prjSection = prjSectionService.get(id);
        mv.addObject("prjSection", prjSection);
        mv.addObject("listProjects", prjBaseinfoService.list());
        mv.setViewName("/prj/sectionUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjSection prjSection) {
        prjSection.setCreated_by(SessionUtil.getUserSession(request).getRealname());
        int flag = prjSectionService.add(prjSection);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("标段新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("标段新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, PrjSection prjSection) {
        int flag = prjSectionService.update(prjSection);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("标段修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("标段修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = prjSectionService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("标段删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("标段删除成功");
    }
}
