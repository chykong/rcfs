package com.balance.prj.controller;

import com.balance.prj.model.PrjGroup;
import com.balance.prj.service.PrjBaseinfoService;
import com.balance.prj.service.PrjGroupService;
import com.balance.prj.vo.PrjGroupSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
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
@RequestMapping("/prj/group")
@Controller
public class PrjGroupController extends BaseController {
    @Autowired
    private PrjGroupService prjGroupService;
    @Autowired
    private PrjBaseinfoService prjBaseinfoService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入用户管理界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjGroupSearchVO prjGroupSearchVO) {
        ModelAndView mv = new ModelAndView();
        int recordCount = prjGroupService.listCount(prjGroupSearchVO);// 获取查询总数
        String url = createUrl(prjGroupSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, prjGroupSearchVO.getPageIndex(), prjGroupSearchVO.getPageSize(), recordCount);//定义分页对象
        List<PrjGroup> list = prjGroupService.list(prjGroupSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/prj/group");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(PrjGroupSearchVO prjGroupSearchVO) {
        String url = pubConfig.getDynamicServer() + "/prj/group/index.htm?";
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
        PrjGroup prjGroup = new PrjGroup();
        mv.addObject("prjGroup", prjGroup);
        mv.addObject("listProject", prjBaseinfoService.list());
        mv.setViewName("/prj/groupAdd");
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
        PrjGroup prjGroup = prjGroupService.get(id);
        mv.addObject("prjGroup", prjGroup);
        mv.addObject("listProject", prjBaseinfoService.list());
        mv.setViewName("/prj/groupUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjGroup prjGroup) {
        prjGroup.setCreated_by(SessionUtil.getUserSession(request).getRealname());
        int flag = prjGroupService.add(prjGroup);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("组别新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("组别新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, PrjGroup prjGroup) {
        int flag = prjGroupService.update(prjGroup);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("组别修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("组别修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = prjGroupService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("组别删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("组别删除成功");
    }
}
