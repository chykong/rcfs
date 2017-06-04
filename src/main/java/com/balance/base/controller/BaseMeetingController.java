package com.balance.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.balance.base.model.BaseMeeting;
import com.balance.base.service.BaseMeetingService;
import com.balance.base.vo.BaseMeetingSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.controller.BaseController;
import com.balance.util.page.PageNavigate;
import com.balance.util.session.SessionUtil;
import com.balance.util.session.UserSession;
import com.balance.util.string.StringUtil;
/**
 * 
 * @author 刘凯
 * @date 2017年6月4日
 */
@RequestMapping("/base/meeting")
@Controller
public class BaseMeetingController extends BaseController {
	@Autowired
private BaseMeetingService baseMeetingService;
@RequestMapping("/index")
public ModelAndView index(HttpServletRequest request, HttpServletResponse response, BaseMeetingSearchVO baseCompanySearchVO) {
	ModelAndView mv=new ModelAndView();
	int recordCount = baseMeetingService.listCount(baseCompanySearchVO);// 获取查询总数
	String url=createUrl();
	PageNavigate pageNavigate=new PageNavigate(url, baseCompanySearchVO.getPageIndex(), baseCompanySearchVO.getPageSize(), recordCount);//定义分页对象
	List<BaseMeeting>list=baseMeetingService.list(baseCompanySearchVO);
	mv.addObject("pageNavigate",pageNavigate);// 设置分页的变量
	mv.addObject("list", list);// 把获取的记录放到mv里面
	mv.setViewName("/base/meetingIndex");// 跳转至指定页面
	BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
	return mv;
}
// 设置分页url，一般有查询条件的才会用到
public String createUrl(){
	String url="/base/meeting/index.htm?";
	return url;
}
/**
 * 新增会议界面
 */
@RequestMapping("/toAdd")
public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response){
	ModelAndView mv=new ModelAndView();
	mv.setViewName("/base/meetingAdd");
	return mv;
}
/**
 * 新增会议内容
 */
@RequestMapping("/add")
public String add(HttpServletRequest request, HttpServletResponse response,BaseMeeting baseMeeting){
	UserSession session=SessionUtil.getUserSession(request);
	baseMeeting.setCreated_by(session.getRealname());//创建人
	baseMeeting.setPrj_base_info_id(session.getCurrent_project_id());//项目id
	String project_progress=request.getParameter("project_progress");
	baseMeeting.setProject_progress(project_progress);//项目进度
	
	int flag=baseMeetingService.add( baseMeeting);
	if(flag==0){
		return "forward:/error.htm?msg="+StringUtil.encodeUrl("新增会议纪要失败");
	}else{
		return "forward:/success.htm?msg="+StringUtil.encodeUrl("新增会议纪要成功");
	}
	 
}
}




