package com.balance.prj.controller;

import com.balance.base.service.BaseCompanyService;
import com.balance.common.vo.ComboboxVO;
import com.balance.prj.model.PrjSection;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.service.PrjSectionService;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.controller.BaseController;
import com.balance.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户信息  统计进度表Controller
 */
@Controller
@RequestMapping("/prj/preallocation/stat")
public class PrjPreallocationStatController extends BaseController {
    @Autowired
    private PrjPreallocationService preallocationService;
    @Autowired
    private PrjSectionService prjSectionService;

    @RequestMapping(value = {"", "index"})
    public ModelAndView index(HttpServletRequest request, PrjPreallocationSearchVO preallocationSearchVO) {
        setBtnAutho(request, "PrjPreallocationStat");
        ModelAndView mv = new ModelAndView("prj/preallocationStatIndex");

        int land_status = SessionUtil.getUserSession(request).getCurrent_land_status();
        mv.addObject("land_status", land_status);

        List<PrjSection> sectionList = prjSectionService.listByprj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("sectionList", sectionList);

        List<ComboboxVO> townList = preallocationService.getTown(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("townList",townList);

        mv.addObject("preallocationSearchVO", preallocationSearchVO);

        BackUrlUtil.createBackUrl(mv, request, "/prj/preallocation/stat/index.htm");
        return mv;
    }

}
