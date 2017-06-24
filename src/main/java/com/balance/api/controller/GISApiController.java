package com.balance.api.controller;

import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.page.ListDTO;
import com.balance.util.session.SessionUtil;
import com.balance.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 拆地图获取信息  ApiController
 */
@RequestMapping("/gis")
@RestController
public class GISApiController {
    @Autowired
    private PrjPreallocationService prjPreallocationService;

    /**
     * 获取拆迁户 基本情况列表
     *
     * @return 拆迁户 基本情况列表
     */
    @RequestMapping("get-householders")
    public ListDTO<PrjPreallocation> getHouseholders(HttpServletRequest request,Integer projectId ) {

        if (projectId == null) {
            projectId =  SessionUtil.getUserSession(request).getCurrent_project_id();
        }
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "项目id是必须的");
        }
        PrjPreallocationSearchVO prjPreallocationSearchVO = new PrjPreallocationSearchVO();
        prjPreallocationSearchVO.setBase_info_id(projectId);
        prjPreallocationSearchVO.setPageIndex(WebUtil.getSafeInt(request.getParameter("page")));
        prjPreallocationSearchVO.setPageSize(9999);
        List<PrjPreallocation> preallocationList = prjPreallocationService.findAll(prjPreallocationSearchVO);
        ListDTO dto = new ListDTO();
        dto.setList(preallocationList);
        dto.setTotal(prjPreallocationService.count(prjPreallocationSearchVO));
        return dto;
    }

}
