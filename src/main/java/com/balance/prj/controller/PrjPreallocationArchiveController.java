package com.balance.prj.controller;

import com.balance.common.vo.ComboboxVO;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.model.PrjSection;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.service.PrjSectionService;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.code.SerialNumUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.controller.BaseController;
import com.balance.util.date.DateUtil;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dsy on 2017/6/7.
 * 拆迁人归档 Controller
 */
@Controller
@RequestMapping("/prj/preallocation/archive")

public class PrjPreallocationArchiveController extends BaseController {
    @Autowired
    private PrjPreallocationService preallocationService;
    @Autowired
    private PrjSectionService prjSectionService;
    @Autowired
    private PubConfig pubConfig;

    @RequestMapping(value = {"", "index"})
    public ModelAndView index(HttpServletRequest request, PrjPreallocationSearchVO preallocationSearchVO) {
        setBtnAutho(request, "PrjPreallocationArch");

        ModelAndView mv = new ModelAndView("prj/preallocationArchiveIndex");
        List<PrjSection> sectionList = prjSectionService.listByprj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("sectionList", sectionList);

        List<ComboboxVO> townList = preallocationService.getTown(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("townList", townList);

        mv.addObject("preallocationSearchVO", preallocationSearchVO);

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
    public String updateArchive(@RequestParam(value = "file", required = false) MultipartFile file, String map_id,
                                String host_name, String archive_code, String file_path, String file_name, HttpServletRequest request) {
        if (file != null && file.getSize() != 0) {
            String uploadPath = pubConfig.getImageUploadPath();
            String storePath = "/uploadfile/" + DateUtil.getShortSystemDate() + "/";
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            //        String fileName = new Date().getTime()+".jpg";
            String createFilename = map_id + "_" + DateUtil.getShortSystemTime() + SerialNumUtil.createRandowmNum(6) + "." + suffix;
            File targetFile = new File(uploadPath + storePath, createFilename);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }


            if (file.getSize() > 10 * 1024 * 1024) {
                return "forward:/error.htm?msg=" + StringUtil.encodeUrl("归档失败，文件超过10M");
            } else {
                //保存
                try {
                    file.transferTo(targetFile);
                    file_path = storePath + File.separator + createFilename;
                    file_name = fileName;
                } catch (Exception e) {
                    e.printStackTrace();
                    return "forward:/error.htm?msg=" + StringUtil.encodeUrl("归档失败");
                }
            }
        }

        int project_id = SessionUtil.getUserSession(request).getCurrent_project_id();
        int flag = preallocationService.updateArchive(map_id, host_name, archive_code, project_id, 70, file_path, file_name);

        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("归档失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("归档成功");
    }
}
