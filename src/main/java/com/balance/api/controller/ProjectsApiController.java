package com.balance.api.controller;

import com.balance.api.dto.SimpleProjectDTO;
import com.balance.prj.model.PrjBaseinfo;
import com.balance.prj.model.PrjSection;
import com.balance.prj.service.PrjBaseinfoService;
import com.balance.prj.service.PrjSectionService;
import com.balance.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
@RequestMapping("/api/projects")
@RestController
public class ProjectsApiController {
    @Autowired
    private PrjBaseinfoService prjBaseinfoService;
    @Autowired
    private PrjSectionService prjSectionService;

    /**
     * 获取当前用户所拥有的项目列表
     *
     * @return 项目列表
     */
    @RequestMapping("get-owned-projects")
    public List<SimpleProjectDTO> getOwnedProjects(HttpServletRequest request) {
        List<PrjBaseinfo> list = prjBaseinfoService.listSelectProject(SessionUtil.getAppSession(request).getUser_id());
        return list == null ? null : list.stream().map(p -> new SimpleProjectDTO(p.getId(), p.getPrj_name()))
                .collect(Collectors.toList());
    }

    /**
     * 根据项目id获取项目的标段
     *
     * @param projectId 项目id
     * @return 标段列表
     */
    @RequestMapping("get-sections")
    public List<String> getSections(HttpServletRequest request, Integer projectId) {
        if (projectId == null) {
            projectId = SessionUtil.getAppSession(request).getCurrent_project_id();
        }
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "项目id是必须的");
        }
        List<String> sections = new ArrayList<>();
        List<PrjSection> prjSectionList = prjSectionService.listByprj_base_info_id(projectId);
        for (PrjSection prjSection : prjSectionList) {
            sections.add(prjSection.getName());
        }
        return sections;
    }
}
