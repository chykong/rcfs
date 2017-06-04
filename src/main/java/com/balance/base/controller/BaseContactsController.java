package com.balance.base.controller;

import com.balance.base.model.BaseContacts;
import com.balance.base.service.BaseContactsService;
import com.balance.base.vo.BaseContactsSearchVO;
import com.balance.util.backurl.BackUrlUtil;
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

    /**
     * 进入用户管理界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, BaseContactsSearchVO baseContactsSearchVO) {
        ModelAndView mv = new ModelAndView();
        String url = createUrl(baseContactsSearchVO);
        List<BaseContacts> list = baseContactsService.list(baseContactsSearchVO);
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/base/contacts");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(BaseContactsSearchVO baseContactsSearchVO) {
        String url = "/base/contacts/index.htm?";
        return url;
    }

    /**
     * @param request
     * @param response
     * @param baseContactsSearchVO
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response, BaseContactsSearchVO baseContactsSearchVO) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/base/contactsAdd");
        return mv;
    }

}
