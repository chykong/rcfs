package com.balance.prj.controller;

import com.balance.prj.model.PrjLog;
import com.balance.prj.service.PrjLogService;
import com.balance.prj.vo.PrjLogSearchVO;
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
 * @author 刘凯
 * @date 2017年6月4日
 */
@RequestMapping("/prj/log")
@Controller
public class PrjLogController extends BaseController {
    @Autowired
    private PrjLogService prjLogService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjLogSearchVO prjLogSearchVO) {
        if (prjLogSearchVO.getProgress() == null) {
            prjLogSearchVO.setProgress(1);
        }
        prjLogSearchVO.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        ModelAndView mv = new ModelAndView();
        int recordCount = prjLogService.listCount(prjLogSearchVO);// 获取查询总数
        String url = createUrl(prjLogSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, prjLogSearchVO.getPageIndex(), prjLogSearchVO.getPageSize(), recordCount);//定义分页对象
        List<PrjLog> list = prjLogService.list(prjLogSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/prj/log");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    public String createUrl(PrjLogSearchVO prjLogSearchVO) {
        String url = "/prj/log/index.htm?progress=" + prjLogSearchVO.getProgress();
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
        mv.setViewName("/prj/logAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 新增会议内容
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjLog prjLog) {
        UserSession session = SessionUtil.getUserSession(request);
        prjLog.setCreated_by(session.getRealname());//创建人
        prjLog.setPrj_base_info_id(session.getCurrent_project_id());//项目id
        int flag = prjLogService.add(prjLog);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("新增项目日志失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("新增项目日志成功");
        }

    }

    /**
     * 修改项目日志页面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        PrjLog prjLog=prjLogService.findById(id);
        mv.addObject("prjLog", prjLog);
        mv.setViewName("/prj/logUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;  
    }
    
    /**
     * 修改项目日志
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response,PrjLog prjLog){
    	prjLog.setLast_modified_by(SessionUtil.getUserSession(request).getRealname());
    	int flag =prjLogService.update(prjLog);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("修改项目日志失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("修改项目日志成功");
    	}
    	
    }
    /**
     * 删除项目日志
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,int id){
    	int flag=prjLogService.delete(id);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("删除项目日志失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("删除项目日志成功");
    	}
    }
    /**
     * 显示文本内容
     */
    @RequestMapping("/toDetail")
    public ModelAndView toDetail(HttpServletRequest request, HttpServletResponse response){
    	int id=Integer.parseInt(request.getParameter("id"));
    	PrjLog prjLog=prjLogService.findById(id);
    	ModelAndView mv=new ModelAndView();
    	mv.addObject("prjLog", prjLog);
    	mv.setViewName("/prj/logDetail");
    	BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
    	return mv;
    }

}




