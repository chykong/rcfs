package com.balance.base.controller;

import com.balance.base.model.BaseContacts;
import com.balance.base.service.BaseContactsService;
import com.balance.base.vo.BaseContactsSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.code.SerialNumUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.controller.BaseController;
import com.balance.util.date.DateUtil;
import com.balance.util.file.FileUtil;
import com.balance.util.page.PageNavigate;
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

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
@RequestMapping("/base/contacts")
@Controller
public class BaseContactsController  extends BaseController{
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
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, BaseContactsSearchVO baseContactsSearchVO) {
        ModelAndView mv = new ModelAndView();
        String url = createUrl(baseContactsSearchVO);
        baseContactsSearchVO.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());//项目id
        int recordCount = baseContactsService.listCount(baseContactsSearchVO);// 获取查询总数
        PageNavigate pageNavigate = new PageNavigate(url, baseContactsSearchVO.getPageIndex(), baseContactsSearchVO.getPageSize(), recordCount);//定义分页对象
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        List<BaseContacts> list = baseContactsService.list(baseContactsSearchVO);
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/base/contacts");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(BaseContactsSearchVO baseContactsSearchVO) {
        String url = pubConfig.getDynamicServer() + "/base/contacts/index.htm?";
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
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
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
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        BaseContacts baseContacts = baseContactsService.get(Integer.parseInt(request.getParameter("id")));
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
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("公司人员新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("公司人员新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, BaseContacts baseContacts) {
        baseContacts.setCreated_by(SessionUtil.getUserSession(request).getRealname());
        int flag = baseContactsService.update(baseContacts);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("公司人员信息修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("公司人员信息修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int flag = baseContactsService.delete(Integer.parseInt(request.getParameter("id")));
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("公司人员删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("公司人员删除成功");
    }

    /**
     * 导入数据
     *
     * @param excel_file
     * @param request
     * @param response
     */
    @RequestMapping("/importData")
    public void importData(@RequestParam(value = "excel_file", required = false) MultipartFile excel_file, HttpServletRequest request,
                           HttpServletResponse response) {
        String create_person = SessionUtil.getUserSession(request).getRealname();
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

        if (excel_file.getSize() > 10 * 1024 * 1024) {
            json = "{success:" + false + ",msgText:'" + "文件超过10M" + "'}";
        } else {
            // 保存
            try {
                excel_file.transferTo(targetFile);
                String result = baseContactsService.saveImport(uploadPath + storePath + File.separator + createFilename, create_person, SessionUtil.getUserSession(request).getCurrent_project_id());
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
