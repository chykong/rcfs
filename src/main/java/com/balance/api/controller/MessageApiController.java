package com.balance.api.controller;

import com.balance.api.dto.BaseMessageDTO;
import com.balance.base.service.BaseMessageService;
import com.balance.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 * 项目参与人员API
 */


@RequestMapping("/api/messages")
@RestController
public class MessageApiController {

    @Autowired
    private BaseMessageService baseMessageService;

    /**
     * 获取我的消息列表
     *
     * @return
     */
    @RequestMapping("get-messages")
    public List<BaseMessageDTO> getMessages(HttpServletRequest request) {
        int user_id = SessionUtil.getAppSession(request).getUser_id();
        List<BaseMessageDTO> list = baseMessageService.listByUser_id(user_id);
        return list;
    }

    /**
     * 5.4	获取我的未读消息数
     *
     * @return
     */
    @RequestMapping("unread-count")
    public int getunread_count(HttpServletRequest request) {
        int user_id = SessionUtil.getAppSession(request).getUser_id();
        return baseMessageService.listUnReadCountByUser_id(user_id);
    }

    /**
     * 获取消息
     */
    @RequestMapping("get/{id}")
    public BaseMessageDTO get(HttpServletRequest request, @PathVariable int id) {
        int user_id = SessionUtil.getAppSession(request).getUser_id();
        BaseMessageDTO baseMessageDTO=baseMessageService.getByMessageAndUser_id(id, user_id);
        baseMessageService.updateStatus(id,baseMessageDTO.getUser_id());

        return baseMessageDTO;
    }
}
