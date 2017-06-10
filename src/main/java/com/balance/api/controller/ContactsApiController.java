package com.balance.api.controller;

import com.balance.api.dto.ContactsGroupsDTO;
import com.balance.base.model.BasePolicy;
import com.balance.base.service.BaseParticipantService;
import com.balance.util.page.ListDTO;
import com.balance.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 * 项目参与人员API
 */


@RequestMapping("/api/contacts")
@RestController
public class ContactsApiController {

    @Autowired
    private BaseParticipantService baseParticipantService;

    /**
     * 获取项目参与人员列表
     *
     * @return
     */
    @RequestMapping("get-groups")
    public ListDTO<BasePolicy> getPolicies(HttpServletRequest request, Integer projectId) {
        if (projectId == null) {
            projectId = SessionUtil.getAppSession(request).getCurrent_project_id();
        }
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "项目id是必须的");
        }
        List<ContactsGroupsDTO> listGroup = baseParticipantService.listByGroup(projectId);
        ListDTO dto = new ListDTO();
        dto.setList(listGroup);
        dto.setTotal(listGroup.size());
        return dto;
    }

}
