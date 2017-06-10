package com.balance.api.controller;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 * 用户ApiController
 */

import com.balance.api.dto.ChangePassDTO;
import com.balance.sys.service.SysUserService;
import com.balance.util.json.JsonResult;
import com.balance.util.session.AppSession;
import com.balance.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/api/users")
@RestController
public class UsersApiController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 修改用户密码
     *
     * @param changePassDTO 修改密码参数
     * @return 执行结果
     */
    @RequestMapping(value = "change-pass", method = RequestMethod.POST)
    public JsonResult changePass(HttpServletRequest request, @RequestBody @Valid ChangePassDTO changePassDTO) {
        AppSession appSession = SessionUtil.getAppSession(request);
        int flag = sysUserService.updatePass(appSession.getUser_id(), changePassDTO.getOld_pass(), changePassDTO.getNew_pass());
        JsonResult jsonResult = new JsonResult();
        if (flag == 1) {
            jsonResult.setSuccess(true);
            jsonResult.setMessage("修改成功");
        } else if (flag == 2) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("原密码输入错误");
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("修改失败");
        }
        return jsonResult;
    }

}
