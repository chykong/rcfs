package com.balance.prj.controller;

import com.balance.prj.model.PrjBrief;
import com.balance.prj.service.PrjBriefService;
import com.balance.prj.vo.PrjBriefSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.controller.BaseController;
import com.balance.util.page.PageNavigate;
import com.balance.util.session.SessionUtil;
import com.balance.util.session.UserSession;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目简报
 *
 * @author 刘凯
 * @date 2017年6月4日
 */
@RequestMapping("/prj/brief")
@Controller
public class PrjBriefController extends BaseController {
    @Autowired
    private PrjBriefService prjBriefService;
    @Autowired
    private PubConfig pubConfig;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjBriefSearchVO prjBriefSearchVO) {
        if (prjBriefSearchVO.getProgress() == null) {
            prjBriefSearchVO.setProgress(1);
        }
        prjBriefSearchVO.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        ModelAndView mv = new ModelAndView();
        int recordCount = prjBriefService.listCount(prjBriefSearchVO);// 获取查询总数
        String url = createUrl(prjBriefSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, prjBriefSearchVO.getPageIndex(), prjBriefSearchVO.getPageSize(), recordCount);//定义分页对象
        List<PrjBrief> list = prjBriefService.list(prjBriefSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/prj/brief");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    public String createUrl(PrjBriefSearchVO prjBriefSearchVO) {
        String url = pubConfig.getDynamicServer() + "/prj/brief/index.htm?progress=" + prjBriefSearchVO.getProgress();
        return url;
    }

    /**
     * 新增会议界面
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        int progress = Integer.parseInt(request.getParameter("progress"));
        ModelAndView mv = new ModelAndView();
        mv.addObject("progress", progress);
        mv.setViewName("/prj/briefAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 新增会议内容
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjBrief prjBrief) {
        UserSession session = SessionUtil.getUserSession(request);
        prjBrief.setCreated_by(session.getRealname());//创建人
        prjBrief.setPrj_base_info_id(session.getCurrent_project_id());//项目id
        int flag = prjBriefService.add(prjBrief);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("新增项目简报失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("新增项目简报成功");
        }

    }

    /**
     * 修改项目简报页面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        PrjBrief prjBrief = prjBriefService.findById(id);
        mv.addObject("prjBrief", prjBrief);
        mv.setViewName("/prj/briefUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 修改项目简报
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, PrjBrief prjBrief) {
        prjBrief.setLast_modified_by(SessionUtil.getUserSession(request).getRealname());
        int flag = prjBriefService.update(prjBrief);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("修改项目简报失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("修改项目简报成功");
        }

    }

    /**
     * 删除项目简报
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = prjBriefService.delete(id);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("删除项目简报失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("删除项目简报成功");
        }
    }

    /**
     * 详细
     */
    @RequestMapping("/toDetail")
    public ModelAndView toDetail(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        PrjBrief prjBrief = prjBriefService.findById(id);
        mv.addObject("prjBrief", prjBrief);
        mv.setViewName("/prj/briefDetail");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }
}