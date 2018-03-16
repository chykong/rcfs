package com.balance.prj.controller;

import com.balance.base.model.BasePlacementSelect;
import com.balance.base.service.BasePlacementDetailService;
import com.balance.common.vo.ComboboxVO;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.model.PrjSection;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.service.PrjSectionService;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.controller.BaseController;
import com.balance.util.json.JsonUtil;
import com.balance.util.session.SessionUtil;
import com.balance.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户信息Controller
 */
@Controller
@RequestMapping("/prj/preallocation/choose")
public class PrjPreallocationChooseController extends BaseController {
    @Autowired
    private PrjPreallocationService preallocationService;
    @Autowired
    private PrjSectionService prjSectionService;
    @Autowired
    private BasePlacementDetailService placementDetailService;

    @RequestMapping(value = {"", "index"})
    public ModelAndView index(HttpServletRequest request, PrjPreallocationSearchVO preallocationSearchVO) {
        setBtnAutho(request, "PrjPreallocationBase");
        ModelAndView mv = new ModelAndView();

        List<PrjSection> sectionList = prjSectionService.listByprj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("sectionList", sectionList);

        List<ComboboxVO> townList = preallocationService.getTown(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("townList", townList);

        mv.addObject("preallocationSearchVO", preallocationSearchVO);

        BasePlacementSelect basePlacementSelect = placementDetailService.getSelectList(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("basePlacementSelect", basePlacementSelect);
        mv.setViewName("prj/preallocationChoose");

        int role_id = SessionUtil.getUserSession(request).getRole_id();
        mv.addObject("role_id", role_id);

        int land_status = SessionUtil.getUserSession(request).getCurrent_land_status();
        mv.addObject("land_status", land_status);

        int building_type = SessionUtil.getUserSession(request).getCurrent_building_type();
        mv.addObject("building_type", building_type);

        BackUrlUtil.createBackUrl(mv, request, "/prj/preallocation/choose/index.htm");
        return mv;
    }

    @RequestMapping(value = "getPreallocation", method = RequestMethod.GET)
    @ResponseBody
    public Map getBasic(PrjPreallocationSearchVO preallocationSearchVO, HttpServletRequest request) {
        Map<Object, Object> map = new HashMap<>();
        preallocationSearchVO.setBase_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        preallocationSearchVO.setLand_status(SessionUtil.getUserSession(request).getCurrent_land_status());
        preallocationSearchVO.setHouse_status(SessionUtil.getUserSession(request).getCurrent_building_type());

        int count = preallocationService.count(preallocationSearchVO);

        List<PrjPreallocation> preallocations = preallocationService.findAll(preallocationSearchVO);

        map.put("data", preallocations);
        map.put("recordsTotal", count);   //必须有  否则报错
        map.put("recordsFiltered", count);  //必须有 否则报错
        map.put("searchDTO", preallocationSearchVO);
        return map;
    }

    @RequestMapping("/getMaxCode")
    public void getMaxCode(HttpServletRequest request, HttpServletResponse response, String map_id, int select_type) {
        String houseType = SessionUtil.getUserSession(request).getCurrent_building_name();
        String landType = SessionUtil.getUserSession(request).getCurrent_land_name();
        int projectId = SessionUtil.getUserSession(request).getCurrent_project_id();
        WebUtil.out(response, "{\"success\":true,\"max\":\"" + preallocationService.updateMaxCode(map_id, projectId, houseType, landType, select_type) + "\"}");
    }

    @RequestMapping("/getBySelectCode")
    public void getBySelectCode(HttpServletRequest request, HttpServletResponse response, String select_code) {
        PrjPreallocation preallocation = preallocationService.getBySelect(select_code);
        WebUtil.out(response, JsonUtil.toStr(preallocation));
    }

    @RequestMapping("/saveSelectHouse")
    public void saveSelectHouse(HttpServletRequest request, HttpServletResponse response, String id, String map_id) {
        int flag = preallocationService.saveSelectHouse(id, map_id, SessionUtil.getUserSession(request).getCurrent_project_id());
        if (flag == 0)
            WebUtil.out(response, JsonUtil.createOperaStr(false, "选房失败"));
        else
            WebUtil.out(response, JsonUtil.createOperaStr(true, "选房成功"));
    }
}
