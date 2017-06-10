package com.balance.config.interceptor;

import com.balance.util.json.JsonResult;
import com.balance.util.json.JsonUtil;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import com.balance.util.web.WebUtil;
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

    /**
     * 操作前先判断是否登录，未登录跳转到登录界面
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtil.isNullOrEmpty(request.getHeader("Authorization")) || SessionUtil.getAppSession(request) == null) {
            JsonResult jsonResult = new JsonResult(false);
            jsonResult.setMessage("未授权");
            WebUtil.out(response, JsonUtil.toStr(jsonResult));
            return false;
        } else {
            return true;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
