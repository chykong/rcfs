package com.balance.prj.controller;

import com.balance.base.model.BaseCompany;
import com.balance.base.service.BaseCompanyService;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.backurl.BackUrlUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户补偿信息 Controller
 */
@Controller
@RequestMapping("/prj/preallocation/compensation")
public class PrjPreallocationCompenController extends BaseController {
    @Autowired
    private PrjPreallocationService preallocationService;
    @Autowired
    private BaseCompanyService baseCompanyService;

    @RequestMapping(value = {"", "index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("prj/preallocationCompenIndex");

        BackUrlUtil.createBackUrl(mv, request, "/prj/preallocation/compensation/index.htm");
        return mv;
    }

    @RequestMapping(value = "getPreallocation", method = RequestMethod.GET)
    @ResponseBody
    public Map getBasic(PrjPreallocationSearchVO PrjpreallocationSearchVO, HttpServletRequest request) {
        Map<Object, Object> map = new HashMap<>();

        int count = preallocationService.count(PrjpreallocationSearchVO);

        PrjpreallocationSearchVO.setBase_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        PrjpreallocationSearchVO.setLand_status(SessionUtil.getUserSession(request).getCurrent_land_status());
        List<PrjPreallocation> preallocations = preallocationService.findAll(PrjpreallocationSearchVO);

        map.put("data", preallocations);
        map.put("recordsTotal", count);   //必须有  否则报错
        map.put("recordsFiltered", count);  //必须有 否则报错
        map.put("searchDTO", PrjpreallocationSearchVO);
        return map;
    }


}
