package com.balance.api.controller;

import com.balance.base.model.BasePolicy;
import com.balance.base.service.BasePolicyService;
import com.balance.util.page.ListDTO;
import com.balance.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 * 项目政策ApiController
 */
@RequestMapping("/api/policies")
@RestController
public class PoliciesApiController {
    @Autowired
    private BasePolicyService basePolicyService;

    /**
     * 获取项目政策列表
     *
     * @return 项目政策列表
     */
    @RequestMapping("get-policies")
    public ListDTO<BasePolicy> getPolicies(HttpServletRequest request, Integer projectId) {
        if (projectId == null) {
            projectId = SessionUtil.getAppSession(request).getCurrent_project_id();
        }
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "项目id是必须的");
        }
        List<BasePolicy> listPolicy = basePolicyService.listAll(projectId);
        ListDTO dto = new ListDTO();
        dto.setList(listPolicy);
        dto.setTotal(listPolicy.size());
        return dto;
    }

    /**
     * 获取项目政策明细
     *
     * @param id 项目政策id
     * @return 项目政策明细
     */
    @RequestMapping("{id}")
    public BasePolicy get(@PathVariable int id) {
        return basePolicyService.get(id);
    }
}
