package com.balance.base.service;

import com.balance.base.dao.BaseCompanyDao;
import com.balance.base.model.BaseCompany;
import com.balance.base.vo.BaseCompanySearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
@Service
public class BaseCompanyService {
    @Autowired
    private BaseCompanyDao baseCompanyDao;

    public int add(BaseCompany baseCompany) {
        return baseCompanyDao.add(baseCompany);
    }

    public int update(BaseCompany baseCompany) {
        return baseCompanyDao.update(baseCompany);
    }

    public int delete(int id) {
        return baseCompanyDao.delete(id);
    }

    public BaseCompany get(int id) {
        return baseCompanyDao.get(id);
    }

    public List<BaseCompany> list(BaseCompanySearchVO baseCompanySearchVO) {
        return baseCompanyDao.list(baseCompanySearchVO);
    }

    public int listCount(BaseCompanySearchVO baseCompanySearchVO) {
        return baseCompanyDao.listCount(baseCompanySearchVO);
    }
}
