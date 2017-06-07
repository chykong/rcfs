package com.balance.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.balance.base.model.BasePolicy;
import com.balance.base.service.BasePolicyService;
import com.balance.base.vo.BasePolicySearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.controller.BaseController;
import com.balance.util.page.PageNavigate;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;

/**
 * Author   孔垂云
 * Date  2017/6/3.
 */
@RequestMapping("/base/policy")
@Controller
public class BasePolicyController extends BaseController {

    @Autowired
    private BasePolicyService basePolicyService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入用户管理界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, BasePolicySearchVO basePolicySearchVO) {
        ModelAndView mv = new ModelAndView();
        basePolicySearchVO.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        int recordCount = basePolicyService.listCount(basePolicySearchVO);// 获取查询总数
        String url = createUrl(basePolicySearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, basePolicySearchVO.getPageIndex(), basePolicySearchVO.getPageSize(), recordCount);//定义分页对象
        List<BasePolicy> list = basePolicyService.list(basePolicySearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/base/policy");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(BasePolicySearchVO basePolicySearchVO) {
        String url = pubConfig.getDynamicServer() + "/base/policy/index.htm?";
        if (StringUtil.isNotNullOrEmpty(basePolicySearchVO.getTitle())) {
            url += "title=" + basePolicySearchVO.getTitle();
        }
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
        mv.setViewName("/base/policyAdd");
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
        BasePolicy basePolicy = basePolicyService.get(id);
        mv.addObject("basePolicy", basePolicy);
        mv.setViewName("/base/policyUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, BasePolicy basePolicy) {
        basePolicy.setCreated_by(SessionUtil.getUserSession(request).getRealname());
        basePolicy.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());//当前项目id
        int flag = basePolicyService.add(basePolicy);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("政策新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("政策新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, BasePolicy basePolicy) {
        int flag = basePolicyService.update(basePolicy);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("政策修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("政策修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = basePolicyService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("政策删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("政策删除成功");
    }
    /**
     *   显示文本内容
     */
    @RequestMapping("/toDetail")
    public ModelAndView toDetail(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        BasePolicy basePolicy = basePolicyService.get(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("basePolicy", basePolicy);
        mv.setViewName("/base/policyDetail");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

}
