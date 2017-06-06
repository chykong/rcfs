package com.balance.base.controller;

import com.balance.base.model.BaseContacts;
import com.balance.base.service.BaseContactsService;
import com.balance.base.vo.BaseContactsSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
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
 * Date  2017/6/3.
 */
@RequestMapping("/base/contacts")
@Controller
public class BaseContactsController {
    @Autowired
    private BaseContactsService baseContactsService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入用户管理界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, BaseContactsSearchVO baseContactsSearchVO, int type) {
        ModelAndView mv = new ModelAndView();
        String url = createUrl(baseContactsSearchVO, type);
        baseContactsSearchVO.setType(type);
        baseContactsSearchVO.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());//项目id
        int recordCount = baseContactsService.listCount(baseContactsSearchVO);// 获取查询总数
        PageNavigate pageNavigate = new PageNavigate(url, baseContactsSearchVO.getPageIndex(), baseContactsSearchVO.getPageSize(), recordCount);//定义分页对象
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        List<BaseContacts> list = baseContactsService.list(baseContactsSearchVO);
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.addObject("type", type);// 把获取的记录放到mv里面
        mv.setViewName("/base/contacts");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(BaseContactsSearchVO baseContactsSearchVO, int type) {
        String url = pubConfig.getDynamicServer() + "/base/contacts/index.htm?type=" + type;
        if (StringUtil.isNotNullOrEmpty(baseContactsSearchVO.getName())) {
            url += "&name=" + baseContactsSearchVO.getName();
        }
        if (StringUtil.isNotNullOrEmpty(baseContactsSearchVO.getMobile())) {
            url += "&mobile=" + baseContactsSearchVO.getMobile();
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
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response, int type) {
        System.out.println(request.getParameter("type"));
        ModelAndView mv = new ModelAndView();
        mv.addObject("type", type);
        mv.setViewName("/base/contactsAdd");
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
        System.out.println(request.getParameter("id"));
        ModelAndView mv = new ModelAndView();
        BaseContacts baseContacts = baseContactsService.get(id);
        mv.addObject("baseContacts", baseContacts);
        mv.setViewName("/base/contactsUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, BaseContacts baseContacts) {
        baseContacts.setCreated_by(SessionUtil.getUserSession(request).getRealname());
        baseContacts.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        int flag = baseContactsService.add(baseContacts);
        String msg = "公司人员";
        if (baseContacts.getType() == 2) msg = "参与人员";
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl(msg + "新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl(msg + "新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, BaseContacts baseContacts) {
        int flag = baseContactsService.update(baseContacts);
        String msg = "公司人员";
        if (baseContacts.getType() == 2) msg = "参与人员";
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl(msg + "信息修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl(msg + "信息修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id, int type) {
        System.out.println(request.getParameter("id"));
        System.out.println(request.getParameter("type"));
        int flag = baseContactsService.delete(id);
        String msg = "公司人员";
        if (type == 2) msg = "参与人员";
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl(msg + "删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl(msg + "公司人员删除成功");
    }

}
