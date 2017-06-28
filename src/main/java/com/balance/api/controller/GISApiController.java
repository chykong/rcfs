package com.balance.api.controller;

import com.balance.api.dto.GisDTO;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.controller.BaseController;
import com.balance.util.page.ListDTO;
import com.balance.util.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 拆地图获取信息  ApiController
 */
@RequestMapping("/gis")
@RestController
public class GISApiController extends BaseController {
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
            projectId = SessionUtil.getAppSession(request).getCurrent_project_id();
        }
        if (projectId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "项目id是必须的");
        }
        PrjPreallocationSearchVO prjPreallocationSearchVO = new PrjPreallocationSearchVO();
        prjPreallocationSearchVO.setBase_info_id(projectId);
        prjPreallocationSearchVO.setPageIndex(1);
        prjPreallocationSearchVO.setPageSize(9999);
        List<GisDTO> preallocationList = prjPreallocationService.listForGis(prjPreallocationSearchVO);
        ListDTO dto = new ListDTO();
        dto.setList(preallocationList);
        dto.setTotal(prjPreallocationService.count(prjPreallocationSearchVO));
        return dto;
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/gis/index");// 跳转至指定页面
        return mv;
    }

}
