package com.balance.base.controller;

/*
  Created by dsy on 2017/8/29.
  安置房详情 Controller
 */

import com.balance.base.model.BaseHouse;
import com.balance.base.model.BasePlacementDetail;
import com.balance.base.service.BasePlacementDetailService;
import com.balance.base.vo.BasePlacementDetailSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.code.SerialNumUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.date.DateUtil;
import com.balance.util.file.FileUtil;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import com.balance.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@RequestMapping("/base/placementdetail")
@Controller
public class BasePlacementDetailController {

    @Autowired
    private BasePlacementDetailService basePlacementDetailService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入概况页面
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, BasePlacementDetailSearchVO placementDetailSearchVO) {
        ModelAndView mv = new ModelAndView();
        placementDetailSearchVO.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());

        List<BasePlacementDetail> list = basePlacementDetailService.listDetail(placementDetailSearchVO);
        mv.addObject("list", list);
        mv.addObject("placementDetailSearchVO", placementDetailSearchVO);
        mv.setViewName("base/placementDetail");
        String url = createUrl(placementDetailSearchVO);
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回的url
        return mv;
    }

    private String createUrl(BasePlacementDetailSearchVO placementDetailSearchVO) {
        String url = pubConfig.getDynamicServer() + "/base/placementdetail/index.htm?___=_";
        if (StringUtil.isNotNullOrEmpty(placementDetailSearchVO.getBuild_code())) {
            url += "&build_code=" + placementDetailSearchVO.getBuild_code();
        }
        if (StringUtil.isNotNullOrEmpty(placementDetailSearchVO.getMap_code())) {
            url += "&map_code=" + placementDetailSearchVO.getMap_code();
        }
        return url;
    }

    /**
     * 增加页面
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        SessionUtil.getUserSession(request).getCurrent_project_id();
        List<BaseHouse> baseHouses = basePlacementDetailService.houseList(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("baseHouses",baseHouses);
        mv.setViewName("base/placementDetailAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, BasePlacementDetail basePlacementDetail) {
        basePlacementDetail.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        int flag = basePlacementDetailService.add(basePlacementDetail);
        if (flag == 1) {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("新增成功");
        } else {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("新增失败");

        }
    }


    /**
     * 更新页面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,int id) {
        ModelAndView mv = new ModelAndView();
        List<BaseHouse> baseHouses = basePlacementDetailService.houseList(SessionUtil.getUserSession(request).getCurrent_project_id());
        BasePlacementDetail basePlacementDetail = basePlacementDetailService.get(id);
        mv.addObject("basePlacementDetail", basePlacementDetail);
        mv.addObject("baseHouses",baseHouses);
        mv.setViewName("base/placementDetailUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/update")
    public String update(BasePlacementDetail basePlacementDetail) {
        int flag = basePlacementDetailService.update(basePlacementDetail);
        if (flag == 1) {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("修改成功");
        } else {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("修改失败");

        }
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        int flag = basePlacementDetailService.delete(id);
        if (flag == 1) {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("删除成功");
        } else {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("删除失败");
        }
    }

    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, String build_code, String map_code) {
        BasePlacementDetail basePlacementDetail = basePlacementDetailService.getByBuildAndMap(build_code, map_code);
        WebUtil.outJson(response, basePlacementDetail == null);
    }

    @RequestMapping("/import")
    public void importData(@RequestParam(value = "excel_file", required = false) MultipartFile excel_file, HttpServletRequest request,
                           HttpServletResponse response) {

        String uploadPath = pubConfig.getImageUploadPath();
        String storePath = "/upload/" + DateUtil.getShortSystemDate() + "/";
        String fileName = excel_file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String createFilename = DateUtil.getShortSystemTime() + SerialNumUtil.createRandowmNum(6) + "." + suffix;
        File targetFile = new File(uploadPath + storePath, createFilename);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String json;

        if (excel_file.getSize() > 20 * 1024 * 1024) {
            json = "{success:" + false + ",msgText:'" + "文件超过20M" + "'}";
        } else {
            // 保存
            try {
                excel_file.transferTo(targetFile);
                String result = basePlacementDetailService.saveImport(uploadPath + storePath + File.separator + createFilename, SessionUtil.getUserSession(request).getCurrent_project_id());
                if (result.equals("")) {
                    json = "{success:" + true + ",msgText:'" + "导入成功" + "'}";
                } else {
                    json = "{success:" + false + ",msgText:'" + "导入失败" + "',errorInfo:'" + result + "'}";
                }
                FileUtil.delete(uploadPath + storePath + File.separator + createFilename);
            } catch (Exception e) {
                json = "{success:" + false + ",msgText:'" + "上传失败" + e.getMessage() + "'}";
                e.printStackTrace();
            }
        }
        WebUtil.out(response, json);
    }
}
