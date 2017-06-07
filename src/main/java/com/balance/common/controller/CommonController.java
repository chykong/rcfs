package com.balance.common.controller;

import com.balance.common.service.CommonService;
import com.balance.prj.service.PrjGroupService;
import com.balance.prj.service.PrjSectionService;
import com.balance.sys.service.SysModuleService;
import com.balance.sys.service.SysRoleService;
import com.balance.sys.service.SysUserService;
import com.balance.sys.vo.SysUserSearchVO;
import com.balance.util.global.GlobalConst;
import com.balance.util.json.JsonUtil;
import com.balance.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统公共数据源访问功能，各下拉列表的数据都从该地方获取
 *
 * @author chykong
 */
@RequestMapping("/common")
@Controller
public class CommonController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysModuleService sysModuleService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private PrjSectionService prjSectionService;
    @Autowired
    private PrjGroupService prjGroupService;

    /**
     * 下拉列表，公共方法
     *
     * @param request
     * @param response
     */
    @RequestMapping("/listCombobox")
    public void listCombobox(HttpServletRequest request, HttpServletResponse response) {
        String table_name = WebUtil.getSafeStr(request.getParameter("table_name"));
        String value = WebUtil.getSafeStr(request.getParameter("value"));
        String content = WebUtil.getSafeStr(request.getParameter("content"));
        String condition = WebUtil.getSafeStr(request.getParameter("condition"));
        String order = WebUtil.getSafeStr(request.getParameter("order"));
        String sort = WebUtil.getSafeStr(request.getParameter("sort"));
        WebUtil.out(response, JsonUtil.toStr(commonService.listCombobox(table_name, value, content, order, sort, condition)));
    }

    /**
     * 角色列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/listRoleCombobox")
    public void listRoleCombobox(HttpServletRequest request, HttpServletResponse response) {
        WebUtil.out(response, JsonUtil.toStr(sysRoleService.listCombo()));
    }

    /**
     * 所有人员列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/listAllSysUser")
    public void listAllSysUser(HttpServletRequest request, HttpServletResponse response) {
        WebUtil.out(response, JsonUtil.toStr(sysUserService.listAllUser()));
    }

    /**
     * 所有人员列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/listAllSysUserBobox")
    public void listAllSysUserBobox(HttpServletRequest request, HttpServletResponse response) {
        WebUtil.out(response, JsonUtil.toStr(commonService.listCombobox("t_sys_user", "realname", "realname", "username", "", "")));
    }

    /**
     * 系统功能模块combobox tree
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getSysModuleComboboxTree")
    public void getSysModuleComboboxTree(HttpServletRequest request, HttpServletResponse response) {
        String json = sysModuleService.getComboboxTreeJson();
        WebUtil.out(response, json);
    }

    /**
     * 弹出用户选择窗口
     *
     * @param request
     * @param response
     * @param sysUserSearchVO
     */
    @RequestMapping("/listUser")
    public void listUser(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) {
        int count = sysUserService.listCount(sysUserSearchVO);
        int pageIndex = WebUtil.getSafeInt(request.getParameter("page"), 1);
        int pageSize = WebUtil.getSafeInt(request.getParameter("limit"), GlobalConst.pageSize);
        String json = JsonUtil.createExtjsPageJson(count, sysUserService.list(sysUserSearchVO));
        WebUtil.out(response, json);
    }

    /**
     * 根据项目id选区段
     *
     * @param request
     * @param response
     */
    @RequestMapping("/listSecionByPrj")
    public void listSecionByPrj(HttpServletRequest request, HttpServletResponse response, int prj_base_info_id) {
        String json = JsonUtil.toStr(prjSectionService.listByprj_base_info_id(prj_base_info_id));
        WebUtil.out(response, json);
    }

    /**
     * 根据区段选组别
     *
     * @param request
     * @param response
     */
    @RequestMapping("/listGroupBySec")
    public void listGroupBySec(HttpServletRequest request, HttpServletResponse response, int section_id) {
        String json = JsonUtil.toStr(prjGroupService.listBySection_id(section_id));
        WebUtil.out(response, json);
    }
}
