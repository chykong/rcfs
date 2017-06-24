package com.balance.config.interceptor;

import com.balance.util.date.DateUtil;
import com.balance.util.http.RequestUtil;
import com.balance.util.json.JsonResult;
import com.balance.util.json.JsonUtil;
import com.balance.util.session.AppSession;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import com.balance.util.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述:校验app请求是否授权拦截器
 * 未授权提示token错误
 * * @version 1.0.0
 *
 * @author 孔垂云
 * @2015年2月22日
 */
public class CheckAppAuthorizationInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger("operationLog");

    /**
     * 操作前先判断是否登录，未登录跳转到登录界面
     * header或request里面只要包含Authorization即可
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ((StringUtil.isNullOrEmpty(request.getHeader("Authorization")) && StringUtil.isNullOrEmpty(request.getParameter("Authorization"))) || SessionUtil.getAppSession(request) == null) {
            JsonResult jsonResult = new JsonResult(false);
            jsonResult.setMessage("未授权");
            WebUtil.out(response, JsonUtil.toStr(jsonResult));
            return false;
        } else {
            //记录日志
            //校验权限
            String path = request.getServletPath();
            String parameters = RequestUtil.getOperaParams(request);
            AppSession appSession=SessionUtil.getAppSession(request);
            logOperation(path, parameters, appSession);
            return true;
        }
    }

    /**
     * 记录文本日志
     *
     * @param path
     * @param parameters
     * @param appSession
     */
    public void logOperation(String path, String parameters, AppSession appSession) {
        String log = "";
        log = "[OPERALOG-登录日志]" + "-[" + appSession.getUser_ip() + "]" + "-[" + DateUtil.getSystemTime() + "]-" + "[" + appSession.getUser_name() + "]-" + "[INFO]-" + path + "-" + parameters;
        logger.info(log);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
