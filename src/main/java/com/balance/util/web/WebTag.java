package com.balance.util.web;

import com.balance.common.vo.ComboboxVO;
import com.balance.sys.service.SysRoleService;
import com.balance.util.date.DateUtil;
import com.balance.util.dic.DicUtil;
import com.balance.util.session.SessionUtil;
import com.balance.util.session.UserSession;
import com.balance.util.spring.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 页面标签，用于直接处理页面数据，展示使用
 *
 * @author chykong
 */
public class WebTag {

    /**
     * 生成菜单
     *
     * @param request 请求
     * @return 菜单
     */
    public static String createMenu(HttpServletRequest request) {
        UserSession userSession = SessionUtil.getUserSession(request);
        SysRoleService sysRoleService = SpringContextHolder.getBean("sysRoleService");
        return sysRoleService.createMenuStr(userSession.getRole_id());
    }

    /**
     * 获取当前用户的模块（菜单）列表
     *
     * @param request 请求
     * @return 当前用户的模块（菜单）列表
     *//*
      public static List<SysModule> getCurrentModule(HttpServletRequest request) {
          UserSession userSession = SessionUtil.getUserSession(request);
	  SysRoleService sysRoleService =
	  SpringContextHolder.getBean("sysRoleService");

	  return sysRoleService.listModuleByRoleId(userSession == null ? 1 :
	  userSession.getRole_id());
      }*/

    /**
     * 判断按钮权限
     *
     * @param buttonCode
     * @return
     */
    public static boolean isPrivilege(String buttonCode) {
        SysRoleService sysRoleService = SpringContextHolder.getBean("sysRoleService");
        return sysRoleService.checkBtnPrivilege(buttonCode);
    }

    /**
     * 格式化日期,标签里面不允许同名
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return DateUtil.dateToString(date, "yyyy-MM-dd");
    }

    /**
     * 截取日期为（YYYY-MM-DD）
     *
     * @param date
     * @return
     */
    public static String subString(String date) {
        if (date.length() < 10) {
            return date;
        }
        return date.substring(0, 10);
    }

    /**
     * 截取日期为（YYYY-MM-DD HH:mm）
     *
     * @param date
     * @return
     */
    public static String subLongDate(String date) {
        if (date.length() < 16) {
            return date;
        }
        return date.substring(0, 16);
    }

    /*
     * 截取字符串
     */
    public static String stringSubset(String str, Integer i) {
        if (str.length() <= i) {
            return str;
        } else {
            return str.substring(0, i) + "..";
        }

    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String formatDate2(Date date, String formatStr) {
        return DateUtil.dateToString(date, formatStr);
    }

    /**
     * 获取用户状态，系统管理-用户管理用到
     *
     * @param user_status
     * @return
     */
    public static String getUserStatus(Integer user_status) {
        if (user_status == 1)
            return "<span class=\"label label-success arrowed\">正常</span>";
        else if (user_status == 2)
            return "<span class=\"label label-warning arrowed\">已锁定</span>";
        else
            return "";
    }

    /**
     * 获取项目进度
     *
     * @param progress 1前期准备阶段2入户调查阶段3预分方案制作阶段4动迁准备阶段5动迁阶段6收尾阶段
     * @return
     */
    public static String getProjectProgress(Integer progress) {
        if (progress == 1)
            return "<span class=\"label label-xlg label-success arrowed \">前期准备阶段</span>";
        else if (progress == 2)
            return "<span class=\"label label-xlg label-success arrowed\">入户调查阶段</span>";
        else if (progress == 3)
            return "<span class=\"label label-xlg label-success arrowed\">内业整理阶段</span>";
        else if (progress == 4)
            return "<span class=\"label label-xlg label-success arrowed\">动迁准备阶段</span>";
        else if (progress == 5)
            return "<span class=\"label label-xlg label-success arrowed\">动迁阶段</span>";
        else if (progress == 6)
            return "<span class=\"label label-xlg label-success arrowed\">收尾阶段</span>";
        else
            return "";
    }

    /**
     * 获取土地性质
     *
     * @param current_land_status 1国有2集体
     * @return
     */
    public static String getCurrentLandName(Integer current_land_status) {
        if (current_land_status == 1)
            return "国有";
        else if (current_land_status == 2)
            return "集体";
        else
            return "";
    }

    /**
     * 获取建筑类型
     *
     * @param current_building_type 1住宅，2非住宅
     * @return
     */
    public static String getCurrentBuildingName(Integer current_building_type) {
        if (current_building_type == 1)
            return "住宅";
        else if (current_building_type == 2)
            return "非住宅";
        else
            return "";
    }

    /**
     * 页面显示阶段
     *
     * @param progress
     * @return
     */
    public static String getProcessTab(String url, Integer progress) {
        StringBuffer sb = new StringBuffer("");
        List<ComboboxVO> list = DicUtil.listProjectProgress();
        for (ComboboxVO vo : list) {
            String btnClass = "";
            if (Integer.parseInt(vo.getValue()) == progress) btnClass = "btn-success";
            else btnClass = "btn-primary";
            sb.append("<button class=\"btn " + btnClass + " btn-sm \" onclick='location.href=\"" + url + "?progress=" + vo.getValue() + "\"'>" + vo.getContent() + "</button> ");
        }
        return sb.toString();
    }

    /**
     * 项目进度的下拉框
     *
     * @param progress 1前期准备阶段2入户调查阶段3预分方案制作阶段4动迁准备阶段5动迁阶段6收尾阶段
     * @return
     */
    public static String showProgressSelect(Integer progress) {
        StringBuilder sb = new StringBuilder("<select id=\"progress\" name=\"progress\" class=\"col-xs-10 col-sm-5\">");
        List<ComboboxVO> list = DicUtil.listProjectProgress();
        for (ComboboxVO vo : list) {
            String selected = "";
            if (progress == Integer.parseInt(vo.getValue())) selected = "selected";
            sb.append("<option value=" + vo.getValue() + " " + selected + ">" + vo.getContent() + "</option>");
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 根据类型查询图标标题
     *
     * @param type
     * @return
     */
    public static String getChartTitleByType(int type) {
        String content = "";
        if (type == 1) return "户数";
        else if (type == 2) return "占地";
        else if (type == 3) return "建面";
        else return "户数";
    }
}
