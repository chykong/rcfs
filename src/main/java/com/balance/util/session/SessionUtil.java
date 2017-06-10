package com.balance.util.session;

import com.balance.util.redis.RedisKeyUtil;
import com.balance.util.redis.RedisUtil;
import com.balance.util.string.StringUtil;
import com.balance.util.web.WebUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * session工具类，用于获取用户session
 *
 * @author 孔垂云
 */
public class SessionUtil {
    /**
     * 功能描述:获取用户session
     *
     * @param request
     * @return UserSession
     * @version 1.0.0
     * @author 孔垂云
     */
    public static UserSession getUserSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("userSession") != null)
            return (UserSession) request.getSession().getAttribute("userSession");
        else {
            //			UserSession userSession = new UserSession();
            //			userSession.setShop_id(1);
            //			userSession.setRole_id(2);
            //			userSession.setUser_id(1);
            //			userSession.setUser_name("admin");
            //			return userSession;
            return null;
        }
    }

    /**
     * 获取appSession
     *
     * @param request
     * @return
     */
    public static AppSession getAppSession(HttpServletRequest request) {
        String authorization = WebUtil.getSafeStr(request.getHeader("Authorization"));//从header获取Authorization
        if (StringUtil.isNotNullOrEmpty(authorization)) return
                RedisUtil.get(RedisKeyUtil.APP_KEY + authorization, AppSession.class);
        else return null;
    }
}
