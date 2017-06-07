package com.balance.prj.controller;

import com.balance.prj.model.PrjReport;
import com.balance.prj.service.PrjReportService;
import com.balance.prj.vo.PrjReportSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.controller.BaseController;
import com.balance.util.file.DownloadUtil;
import com.balance.util.page.PageNavigate;
import com.balance.util.session.SessionUtil;
import com.balance.util.session.UserSession;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 汇报材料
 *
 * @author 刘凯
 * @date 2017年6月4日
 */
@RequestMapping("/prj/report")
@Controller
public class PrjReportController extends BaseController {
    @Autowired
    private PrjReportService prjReportService;
    @Autowired
    private PubConfig pubConfig;

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, PrjReportSearchVO prjReportSearchVO) {
        if (prjReportSearchVO.getProgress() == null) {
            prjReportSearchVO.setProgress(1);
        }
        prjReportSearchVO.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        ModelAndView mv = new ModelAndView();
        int recordCount = prjReportService.listCount(prjReportSearchVO);// 获取查询总数
        String url = createUrl(prjReportSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, prjReportSearchVO.getPageIndex(), prjReportSearchVO.getPageSize(), recordCount);//定义分页对象
        List<PrjReport> list = prjReportService.list(prjReportSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/prj/report");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    public String createUrl(PrjReportSearchVO prjReportSearchVO) {
        String url = pubConfig.getDynamicServer() + "/prj/report/index.htm?progress=" + prjReportSearchVO.getProgress();
        return url;
    }

    /**
     * 新增会议界面
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        int progress = Integer.parseInt(request.getParameter("progress"));
        ModelAndView mv = new ModelAndView();
        mv.addObject("progress", progress);
        mv.setViewName("/prj/reportAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 新增会议内容
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, PrjReport prjReport) {
        UserSession session = SessionUtil.getUserSession(request);
        prjReport.setCreated_by(session.getRealname());//创建人
        prjReport.setPrj_base_info_id(session.getCurrent_project_id());//项目id
        int flag = prjReportService.add(prjReport);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("新增汇报材料失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("新增汇报材料成功");
        }

    }

    /**
     * 修改汇报材料页面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        PrjReport prjReport = prjReportService.findById(id);
        mv.addObject("prjReport", prjReport);
        mv.setViewName("/prj/reportUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 修改汇报材料
     */
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, PrjReport prjReport) {
        prjReport.setLast_modified_by(SessionUtil.getUserSession(request).getRealname());
        int flag = prjReportService.update(prjReport);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("修改汇报材料失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("修改汇报材料成功");
        }

    }

    /**
     * 删除汇报材料
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = prjReportService.delete(id);
        if (flag == 0) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("删除汇报材料失败");
        } else {
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("删除汇报材料成功");
        }
    }

    /**
     * 显示文本内容
     */
    @RequestMapping("/toDetail")
    public ModelAndView toDetail(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        PrjReport prjReport = prjReportService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("prjReport", prjReport);
        mv.setViewName("/prj/reportDetail");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 导出
     */
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        PrjReport prjReport = prjReportService.findById(id);
        DownloadUtil.download(response, pubConfig.getImageUploadPath() + File.separator + prjReport.getFile_path() + File.separator + prjReport.getFile_name(), prjReport.getFile_name(), "UTF-8");
    }
}




