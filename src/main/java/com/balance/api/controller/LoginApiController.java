package com.balance.api.controller;

import com.balance.api.dto.LoginApiDTO;
import com.balance.api.dto.UserLoginDTO;
import com.balance.prj.model.PrjBaseinfo;
import com.balance.prj.service.PrjBaseinfoService;
import com.balance.sys.model.SysUser;
import com.balance.sys.service.SysUserService;
import com.balance.util.json.JsonResult;
import com.balance.util.redis.RedisKeyUtil;
import com.balance.util.redis.RedisUtil;
import com.balance.util.session.AppSession;
import com.balance.util.string.StringUtil;
import com.balance.util.web.WebTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Date  2017/6/10.
 * Author  孔垂云
 */
@RequestMapping("/api")
@RestController
public class LoginApiController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PrjBaseinfoService prjBaseinfoService;

    /**
     * 校验登录
     *
     * @param loginApiDTO
     * @return
     */
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public JsonResult checkLogin(HttpServletRequest request, @RequestBody LoginApiDTO loginApiDTO) {
        boolean flag = false;
        SysUser sysUser = sysUserService.getByUsername(loginApiDTO.getUsername());
        if (sysUser != null) {
            flag = sysUserService.checkPass(sysUser, loginApiDTO.getPassword());
        }
        JsonResult jsonResult = new JsonResult();
        if (flag == true) {
            jsonResult.setSuccess(true);
            jsonResult.setMessage("登录成功");
            //定义token，对应具体的用户id
            String uuid = UUID.randomUUID().toString();
            AppSession appSession = new AppSession();
            appSession.setUser_id(sysUser.getId());
            appSession.setUser_ip(StringUtil.getIp(request));//user_ip
            appSession.setUser_name(sysUser.getUsername());
            appSession.setRole_id(sysUser.getRole_id());
            appSession.setRealname(sysUser.getRealname());
            //  当前项目信息
            appSession.setCurrent_project_id(sysUser.getCurrent_project_id());
            PrjBaseinfo prjBaseinfo = prjBaseinfoService.get(sysUser.getCurrent_project_id());
            if (prjBaseinfo != null) appSession.setCurrent_project_name(prjBaseinfo.getPrj_name());//项目名称
            appSession.setCurrent_land_status(sysUser.getCurrent_land_status());
            appSession.setCurrent_land_name(WebTag.getCurrentLandName(sysUser.getCurrent_land_status()));
            appSession.setCurrent_building_type(sysUser.getCurrent_building_type());
            appSession.setCurrent_building_name(WebTag.getCurrentBuildingName(sysUser.getCurrent_building_type()));
            RedisUtil.set(RedisKeyUtil.APP_KEY + uuid, appSession);//

            UserLoginDTO userLoginDTO = new UserLoginDTO();
            userLoginDTO.setAccess_token(uuid);
            userLoginDTO.setRealname(sysUser.getRealname());
            jsonResult.setData(userLoginDTO);
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("账号或密码错误");
        }
        return jsonResult;
    }

}
