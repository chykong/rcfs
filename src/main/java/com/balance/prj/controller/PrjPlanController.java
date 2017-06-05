package com.balance.prj.controller;

import com.balance.prj.model.PrjPlan;
import com.balance.prj.service.PrjPlanService;
import com.balance.prj.vo.PrjPlanSearchVO;
import com.balance.util.backurl.BackUrlUtil;
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
 * 工作计划
 * @author 刘凯
 * @date 2017年6月4日
 */
@RequestMapping("/prj/plan")
@Controller
public class PrjPlanController extends BaseController {
    @Autowired
    private PrjPlanService prjPlanService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjPlanSearchVO prjPlanSearchVO) {
        if (prjPlanSearchVO.getProgress() == null) {
            prjPlanSearchVO.setProgress(1);
        }
        ModelAndView mv = new ModelAndView();
        int recordCount = prjPlanService.listCount(prjPlanSearchVO);// 获取查询总数
        String url = createUrl(prjPlanSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, prjPlanSearchVO.getPageIndex(), prjPlanSearchVO.getPageSize(), recordCount);//定义分页对象
        List<PrjPlan> list = prjPlanService.list(prjPlanSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/prj/plan");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    public String createUrl(PrjPlanSearchVO prjPlanSearchVO) {
        String url = "/prj/plan/index.htm?progress=" + prjPlanSearchVO.getProgress();
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
        mv.setViewName("/prj/planAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 新增会议内容
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjPlan prjPlan) {
        UserSession session = SessionUtil.getUserSession(request);
        prjPlan.setCreated_by(session.getRealname());//创建人
        prjPlan.setPrj_base_info_id(session.getCurrent_project_id());//项目id
        int flag = prjPlanService.add(prjPlan);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("新增工作计划失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("新增工作计划成功");
        }

    }

    /**
     * 修改工作计划页面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        PrjPlan prjPlan=prjPlanService.findById(id);
        mv.addObject("prjPlan", prjPlan);
        mv.setViewName("/prj/planUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;  
    }
    
    /**
     * 修改工作计划
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response,PrjPlan prjPlan){
    	prjPlan.setLast_modified_by(SessionUtil.getUserSession(request).getRealname());
    	int flag =prjPlanService.update(prjPlan);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("修改工作计划失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("修改工作计划成功");
    	}
    	
    }
    /**
     * 删除工作计划
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,int id){
    	int flag=prjPlanService.delete(id);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("删除工作计划失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("删除工作计划成功");
    	}
    }

}




