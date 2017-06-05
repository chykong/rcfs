package com.balance.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.balance.prj.model.PrjBaseinfo;
import com.balance.prj.service.PrjBaseinfoService;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.controller.BaseController;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;

/**
 * Author 李晓明 Date 2017/6/4  .
 */
@RequestMapping("/base/project")
@Controller
public class BaseProjectintroController extends BaseController {

	@Autowired
	private PrjBaseinfoService prjBaseinfoService;

	/**
	 * 进入项目简介界面
	 *
	 * @return
	 */
	@RequestMapping("/intro")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,String type) {
		ModelAndView mv = new ModelAndView();
//		String type = request.getParameter("type");
		PrjBaseinfo prjBaseinfo = prjBaseinfoService.get(24);
		prjBaseinfo.setType(type);
		mv.addObject("prjBaseinfo", prjBaseinfo);// 把获取的对象放到mv里面
		mv.setViewName("/base/projectIntro");
		mv.addObject("backUrl", createUrl(type));// 设置返回url
		return mv;
	}
	/**
	 * 进入工作流程界面
	 *
	 * @return
	 */
	@RequestMapping("/flow")
	public ModelAndView indexFlow(HttpServletRequest request, HttpServletResponse response,String type) {
		ModelAndView mv = new ModelAndView();
		PrjBaseinfo prjBaseinfo = prjBaseinfoService.get(24);
//		String type = request.getParameter("type");
		prjBaseinfo.setType(type);
		mv.addObject("prjBaseinfo", prjBaseinfo);// 把获取的对象放到mv里面
		mv.setViewName("/base/projectIntro");
		mv.addObject("backUrl", createUrl(type));// 设置返回url
		return mv;
	}
	/**
	 * 进入组织架构界面
	 *
	 * @return
	 */
	@RequestMapping("/archi")
	public ModelAndView indexArchi(HttpServletRequest request, HttpServletResponse response,String type) {
		ModelAndView mv = new ModelAndView();
		PrjBaseinfo prjBaseinfo = prjBaseinfoService.get(24);
//		String type = request.getParameter("type");
		prjBaseinfo.setType(type);
		mv.addObject("prjBaseinfo", prjBaseinfo);// 把获取的对象放到mv里面
		mv.setViewName("/base/projectIntro");
		mv.addObject("backUrl", createUrl(type));// 设置返回url
		return mv;
	}

	private String createUrl(String type) {
		String url = "/base/project/intro.htm?type="+type;
		if (type.equals("2"))
			url = "/base/project/flow.htm?type="+type;
		if (type.equals("3"))
			url = "/base/project/archi.htm?type="+type;
		return url;
	}

	/**
	 * 进入简介添加界面
	 *
	 * @param request
	 * @param response
	 * @param
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String type = request.getParameter("type");
		String viewName = "/base/projectIntroAdd";// 跳转至项目简介页面
		if (type.equals("2"))
			viewName = "/base/projectFlowAdd";// 跳转至工作流程 页面
		if (type.equals("3"))
			viewName = "/base/projectArchiAdd";// 跳转至组织架构页面
		mv.setViewName(viewName);
		BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
		return mv;
	}

	/**
	 * 进入简介修改界面
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id, String type) {
		ModelAndView mv = new ModelAndView();
		PrjBaseinfo prjBaseinfo = prjBaseinfoService.get(id);
		mv.setViewName("/base/projectIntroUpdate");
		prjBaseinfo.setType(type);
		mv.addObject("prjBaseinfo", prjBaseinfo);
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

	/**
	 * 简介修改操作
	 * 
	 * @param request
	 * @param response
	 * @param prjBaseinfo
	 * @return
	 */
	@RequestMapping("/update")
	public String update(HttpServletRequest request, HttpServletResponse response, PrjBaseinfo prjBaseinfo) {
		String type= prjBaseinfo.getType();
		int flag = prjBaseinfoService.update(prjBaseinfo);
		String str = "项目简介";
		if(type.equals("2")) str = "工作流程";
		if(type.equals("3")) str = "组织架构";
		if (flag == 0)
			return "forward:/error.htm?msg=" + StringUtil.encodeUrl(str+"修改失败");
		else
			return "forward:/success.htm?msg=" + StringUtil.encodeUrl(str+"修改成功");
	}
}
