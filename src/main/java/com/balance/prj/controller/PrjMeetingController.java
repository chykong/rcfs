package com.balance.prj.controller;

import com.balance.prj.model.PrjMeeting;
import com.balance.prj.service.PrjMeetingService;
import com.balance.prj.vo.PrjMeetingSearchVO;
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
@RequestMapping("/prj/meeting")
@Controller
public class PrjMeetingController extends BaseController {
    @Autowired
    private PrjMeetingService prjMeetingService;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjMeetingSearchVO prjMeetingSearchVO) {
        if (prjMeetingSearchVO.getProgress() == null) {
            prjMeetingSearchVO.setProgress(1);
        }

        ModelAndView mv = new ModelAndView();
        int recordCount = prjMeetingService.listCount(prjMeetingSearchVO);// 获取查询总数
        String url = createUrl(prjMeetingSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, prjMeetingSearchVO.getPageIndex(), prjMeetingSearchVO.getPageSize(), recordCount);//定义分页对象
        List<PrjMeeting> list = prjMeetingService.list(prjMeetingSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/prj/meeting");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    public String createUrl(PrjMeetingSearchVO prjMeetingSearchVO) {
        String url = "/prj/meeting/index.htm?progress=" + prjMeetingSearchVO.getProgress();
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
        mv.setViewName("/prj/meetingAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 新增会议内容
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjMeeting prjMeeting) {
        UserSession session = SessionUtil.getUserSession(request);
        prjMeeting.setCreated_by(session.getRealname());//创建人
        prjMeeting.setPrj_base_info_id(session.getCurrent_project_id());//项目id
        int flag = prjMeetingService.add(prjMeeting);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("新增会议纪要失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("新增会议纪要成功");
        }

    }

    /**
     * 修改会议纪要页面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        PrjMeeting prjMeeting=prjMeetingService.findById(id);
        mv.addObject("prjMeeting", prjMeeting);
        mv.setViewName("/prj/meetingUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;  
    }
    
    /**
     * 修改会议纪要
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response,PrjMeeting prjMeeting){
    	prjMeeting.setLast_modified_by(SessionUtil.getUserSession(request).getRealname());
    	int flag =prjMeetingService.update(prjMeeting);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("修改会议纪要失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("修改会议纪要成功");
    	}
    	
    }
    /**
     * 删除会议纪要
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,int id){
    	int flag=prjMeetingService.delete(id);
    	if(flag==0){
    		return "forward:/error.htm?msg="+StringUtil.encodeUrl("删除会议纪要失败");
    	}else{
    		return "forward:/success.htm?msg="+StringUtil.encodeUrl("删除会议纪要成功");
    	}
    }

}




