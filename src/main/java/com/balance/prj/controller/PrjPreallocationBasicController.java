package com.balance.prj.controller;

import com.balance.base.model.BaseCompany;
import com.balance.base.service.BaseCompanyService;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.sys.service.SysUserService;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.controller.BaseController;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户信息Controller
 */
@Controller
@RequestMapping("/prj/preallocation/basic")
public class PrjPreallocationBasicController extends BaseController {
    @Autowired
    private PrjPreallocationService preallocationService;
    @Autowired
    private BaseCompanyService baseCompanyService;

    @RequestMapping(value = {"", "index"})
    public String index() {
        return "prj/preallocationIndex";
    }

    @RequestMapping(value = "getPreallocation", method = RequestMethod.GET)
    @ResponseBody
    public Map getBasic(PrjPreallocationSearchVO preallocationSearchVO, HttpServletRequest request) {
        Map<Object, Object> map = new HashMap<>();

        int count = preallocationService.count(preallocationSearchVO);

        preallocationSearchVO.setBase_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        preallocationSearchVO.setLand_status(SessionUtil.getUserSession(request).getCurrent_land_status());
        List<PrjPreallocation> preallocations = preallocationService.findAll(preallocationSearchVO);

        map.put("data", preallocations);
        map.put("recordsTotal", count);   //必须有  否则报错
        map.put("recordsFiltered", count);  //必须有 否则报错
        map.put("searchDTO", preallocationSearchVO);
        return map;
    }


    @RequestMapping(value = "toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/prj/preallocationAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        PrjPreallocation preallocation = new PrjPreallocation();

        String land_status = SessionUtil.getUserSession(request).getCurrent_land_status()==1?"国有":"集体";
        preallocation.setLand_property(land_status);
        mv.addObject("preallocation", preallocation);

//        List<PrjSection> sectionList = projectService.getSectionsByProjectId(getCurrentProject().getProjectId());
//        model.addAttribute("sectionList", sectionList);
//
        List<BaseCompany> companyList = baseCompanyService.listAll();
        mv.addObject("companyList", companyList);

        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(PrjPreallocation preallocation, HttpServletRequest request) {
        preallocation.setCreated_by(SessionUtil.getUserSession(request).getUser_name());
        preallocation.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        preallocation.setStatus(20);
        int flag = preallocationService.add(preallocation);

        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("拆除腾退人新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("拆除腾退人新增成功");
    }

    @RequestMapping(value = "toUpdate", method = RequestMethod.GET)
    public ModelAndView toUpdate(int id, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/prj/preallocationUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url

        PrjPreallocation preallocation = preallocationService.getById(id);
        if (preallocation == null) {
            mv.setViewName("/error.htm");
            mv.addObject("msg", "拆除腾退人不存在");
            return mv;
        }
//        List<PrjPreallocation> sectionList = projectService.getSectionsByProjectId(getCurrentProject().getProjectId());
//        model.addAttribute("sectionList", sectionList);
//
//        List<Company> companyList = companiesService.search();
//        model.addAttribute("companyList", companyList);

        mv.addObject("preallocation", preallocation);
        return mv;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(PrjPreallocation preallocation) {
        PrjPreallocation preallocation_DB = preallocationService.getById(preallocation.getId());
        preallocation.setPrj_base_info_id(preallocation_DB.getPrj_base_info_id());
        preallocation.setStatus(10);
        preallocation.setCreated_by(preallocation_DB.getCreated_by());

        int flag = preallocationService.update(preallocation);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("拆除腾退人修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("拆除腾退人修改成功");
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(int id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/prj/preallocationView");

        PrjPreallocation preallocation = preallocationService.getById(id);
        if (preallocation == null) {
            mv.setViewName("/error.htm");
            mv.addObject("msg", "拆除腾退人不存在");
            return mv;
        }
        mv.addObject("preallocation", preallocation);
        mv.addObject("view_type", 1);
        return mv;
    }

    @RequestMapping("/checkMapId")
    @ResponseBody
    public boolean checkMapId(String mapId, HttpServletRequest request) {
        int base_info_id = SessionUtil.getUserSession(request).getCurrent_project_id();
        return !preallocationService.existByMapId(mapId, base_info_id);
    }

}
