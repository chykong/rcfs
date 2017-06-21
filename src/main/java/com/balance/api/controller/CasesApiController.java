package com.balance.api.controller;

import com.balance.base.model.BaseCase;
import com.balance.base.service.BaseCaseService;
import com.balance.util.page.ListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 * 经典案例ApiController
 */
@RequestMapping("/api/cases")
@RestController
public class CasesApiController {

    @Autowired
    private BaseCaseService baseCaseService;

    /**
     * 获取经典案例列表
     *
     * @return
     */
    @RequestMapping("get-classic-cases")
    public ListDTO<BaseCase> getCases(HttpServletRequest request) {
        List<BaseCase> listCase = baseCaseService.listAll();
        ListDTO dto = new ListDTO();
        dto.setList(listCase);
        dto.setTotal(listCase.size());
        return dto;
    }

    /**
     * 获取经典案例
     */
    @RequestMapping("get/{id}")
    public BaseCase get(@PathVariable int id) {
        return baseCaseService.get(id);
    }
}
