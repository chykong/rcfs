package com.balance.prj.controller;

import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.backurl.BackUrlUtil;
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
 * Created by dsy on 2017/6/7.
 * 拆迁人归档 Controller
 */
@Controller
@RequestMapping("/prj/preallocation/archive")

public class PrjPreallocationArchiveController {
    @Autowired
    private PrjPreallocationService preallocationService;

    @RequestMapping(value = {"", "index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("prj/preallocationArchiveIndex");

        BackUrlUtil.createBackUrl(mv, request, "/prj/preallocation/archive/index.htm");
        return mv;
    }

    @RequestMapping(value = "getPreallocation", method = RequestMethod.GET)
    @ResponseBody
    public Map getBasic(PrjPreallocationSearchVO preallocationSearchVO, HttpServletRequest request) {
        Map<Object, Object> map = new HashMap<>();

        int count = preallocationService.count(preallocationSearchVO);

        //已放款后 才可以归档
        preallocationSearchVO.setStatus(60);

        preallocationSearchVO.setBase_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        preallocationSearchVO.setLand_status(SessionUtil.getUserSession(request).getCurrent_land_status());
        List<PrjPreallocation> preallocations = preallocationService.findAll(preallocationSearchVO);

        map.put("data", preallocations);
        map.put("recordsTotal", count);   //必须有  否则报错
        map.put("recordsFiltered", count);  //必须有 否则报错
        map.put("searchDTO", preallocationSearchVO);
        return map;
    }


    @RequestMapping(value = "updateArchive", method = RequestMethod.POST)
    public String updateArchive(String map_id,String host_name,String archive_code, HttpServletRequest request) {
        SessionUtil.getUserSession(request).getCurrent_project_id();
        int flag = preallocationService.updateArchive(map_id,host_name,archive_code,70);

        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("归档失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("归档成功");
    }
}
