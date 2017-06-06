package com.balance.prj.controller;

import com.balance.prj.model.PrjNews;
import com.balance.prj.service.PrjNewsService;
import com.balance.prj.vo.PrjNewsSearchVO;
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
 * 新闻进度
 * @author 刘凯
 * @date 2017年6月4日
 */
@RequestMapping("/prj/news")
@Controller
public class PrjNewsController extends BaseController {
    @Autowired
    private PrjNewsService prjNewsService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjNewsSearchVO prjNewsSearchVO) {
        if (prjNewsSearchVO.getProgress() == null) {
            prjNewsSearchVO.setProgress(1);
        }
        prjNewsSearchVO.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        ModelAndView mv = new ModelAndView();
        int recordCount = prjNewsService.listCount(prjNewsSearchVO);// 获取查询总数
        String url = createUrl(prjNewsSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, prjNewsSearchVO.getPageIndex(), prjNewsSearchVO.getPageSize(), recordCount);//定义分页对象
        List<PrjNews> list = prjNewsService.list(prjNewsSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/prj/news");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    public String createUrl(PrjNewsSearchVO prjNewsSearchVO) {
        String url = "/prj/news/index.htm?progress=" + prjNewsSearchVO.getProgress();
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
        mv.setViewName("/prj/newsAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 新增会议内容
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjNews prjNews) {
        UserSession session = SessionUtil.getUserSession(request);
        prjNews.setCreated_by(session.getRealname());//创建人
        prjNews.setPrj_base_info_id(session.getCurrent_project_id());//项目id
        int flag = prjNewsService.add(prjNews);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("新增新闻进度失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("新增新闻进度成功");
        }

    }

    /**
     * 修改新闻进度页面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        PrjNews prjNews=prjNewsService.findById(id);
        mv.addObject("prjNews", prjNews);
        mv.setViewName("/prj/newsUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;  
    }
    
    /**
     * 修改新闻进度
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response,PrjNews prjNews){
    	prjNews.setLast_modified_by(SessionUtil.getUserSession(request).getRealname());
    	int flag =prjNewsService.update(prjNews);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("修改新闻进度失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("修改新闻进度成功");
    	}
    	
    }
    /**
     * 删除新闻进度
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,int id){
    	int flag=prjNewsService.delete(id);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("删除新闻进度失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("删除新闻进度成功");
    	}
    }
    /**
     * 显示文本内容
     */
    @RequestMapping("/toDetail")
    public ModelAndView toDetail(HttpServletRequest request, HttpServletResponse response){
    	int id=Integer.parseInt(request.getParameter("id"));
    	PrjNews prjNews=prjNewsService.findById(id);
    	ModelAndView mv=new ModelAndView();
    	mv.addObject("prjNews", prjNews);
    	mv.setViewName("/prj/newsDetail");
    	BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
    	return mv;
    }
}




