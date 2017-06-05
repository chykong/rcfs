package com.balance.prj.controller;

import com.balance.prj.model.PrjPresentation;
import com.balance.prj.service.PrjPresentationService;
import com.balance.prj.vo.PrjPresentationSearchVO;
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
 * 项目简报
 * @author 刘凯
 * @date 2017年6月4日
 */
@RequestMapping("/prj/presentation")
@Controller
public class PrjPresentationController extends BaseController {
    @Autowired
    private PrjPresentationService prjPresentationService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjPresentationSearchVO prjPresentationSearchVO) {
        if (prjPresentationSearchVO.getProgress() == null) {
            prjPresentationSearchVO.setProgress(1);
        }

        ModelAndView mv = new ModelAndView();
        int recordCount = prjPresentationService.listCount(prjPresentationSearchVO);// 获取查询总数
        String url = createUrl(prjPresentationSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, prjPresentationSearchVO.getPageIndex(), prjPresentationSearchVO.getPageSize(), recordCount);//定义分页对象
        List<PrjPresentation> list = prjPresentationService.list(prjPresentationSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/prj/presentation");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    public String createUrl(PrjPresentationSearchVO prjPresentationSearchVO) {
        String url = "/prj/presentation/index.htm?progress=" + prjPresentationSearchVO.getProgress();
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
        mv.setViewName("/prj/presentationAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 新增会议内容
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjPresentation prjPresentation) {
        UserSession session = SessionUtil.getUserSession(request);
        prjPresentation.setCreated_by(session.getRealname());//创建人
        prjPresentation.setPrj_base_info_id(session.getCurrent_project_id());//项目id
        int flag = prjPresentationService.add(prjPresentation);
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
        PrjPresentation prjPresentation=prjPresentationService.findById(id);
        mv.addObject("prjPresentation", prjPresentation);
        mv.setViewName("/prj/presentationUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;  
    }
    
    /**
     * 修改项目简报
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response,PrjPresentation prjPresentation){
    	prjPresentation.setLast_modified_by(SessionUtil.getUserSession(request).getRealname());
    	int flag =prjPresentationService.update(prjPresentation);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("修改项目简报失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("修改项目简报成功");
    	}
    	
    }
    /**
     * 删除项目简报
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,int id){
    	int flag=prjPresentationService.delete(id);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("删除项目简报失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("删除项目简报成功");
    	}
    }

}




