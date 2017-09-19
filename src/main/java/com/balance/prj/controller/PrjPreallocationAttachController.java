package com.balance.prj.controller;

import com.balance.prj.model.PrjPreallAttach;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.util.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户附件照片 信息Controller
 */
@Controller
@RequestMapping("/prj/preallocation/attach")
public class PrjPreallocationAttachController extends BaseController {
    @Autowired
    private PrjPreallocationService preallocationService;

    @RequestMapping(value = {"", "getAttachList"})
    public ModelAndView getAttachList(String map_id) {
        ModelAndView mv = new ModelAndView("prj/preallocationAttach");

        List<PrjPreallAttach> attachList = preallocationService.listAttach(map_id, 0);
        mv.addObject("attachList", attachList);
        return mv;
    }

}
