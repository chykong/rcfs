package com.balance.base.controller;

import com.balance.base.model.BaseMessage;
import com.balance.base.service.BaseMessageService;
import com.balance.base.vo.BaseMessageSearchVO;
import com.balance.sys.service.SysUserService;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.controller.BaseController;
import com.balance.util.date.DateUtil;
import com.balance.util.page.PageNavigate;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Author 李晓明 Date 2017/6/11.
 */
@RequestMapping("/base/message")
@Controller
public class BaseMessageController extends BaseController{
    @Autowired
    private BaseMessageService baseMessageService;
    @Autowired
    private PubConfig pubConfig;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 进入消息发送界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,
                              BaseMessageSearchVO baseMessageSearchVO) {
        ModelAndView mv = new ModelAndView();
        String url = createUrl(baseMessageSearchVO);
        int recordCount = baseMessageService.listCount(baseMessageSearchVO);// 获取查询总数
        PageNavigate pageNavigate = new PageNavigate(url, baseMessageSearchVO.getPageIndex(),
                baseMessageSearchVO.getPageSize(), recordCount);// 定义分页对象
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        List<BaseMessage> list = baseMessageService.list(baseMessageSearchVO);
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/base/message");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(BaseMessageSearchVO baseMessageSearchVO) {
        String url = pubConfig.getDynamicServer() + "/base/message/index.htm?";
        if (StringUtil.isNotNullOrEmpty(baseMessageSearchVO.getTitle())) {
            url
                    += "&title=" + baseMessageSearchVO.getTitle();
        }
        return url;
    }

    /**
     * 跳转至新增界面
     *
     * @param request
     * @param response
     * @param
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        Date date = new Date();
        mv.addObject("create_date", DateUtil.getSystemDate());
        mv.addObject("listUser", sysUserService.listAll());
        mv.addObject("baseMessage", new BaseMessage());
        mv.setViewName("/base/messageAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, BaseMessage baseMessage) {
        baseMessage.setSend_by(SessionUtil.getUserSession(request).getRealname());
        int flag = baseMessageService.add(baseMessage);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("消息新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("消息新增成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int flag = baseMessageService.delete(Integer.parseInt(request.getParameter("id")));
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("消息删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("消息删除成功");
    }

    /**
     * 显示文本内容
     */
    @RequestMapping("/toDetail")
    public ModelAndView toDetail(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        BaseMessage baseMessage = baseMessageService.get(id);
        baseMessageService.update(baseMessage);
        ModelAndView mv = new ModelAndView();
        mv.addObject("baseMessage", baseMessage);
        mv.setViewName("/base/messageDetail");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

}
