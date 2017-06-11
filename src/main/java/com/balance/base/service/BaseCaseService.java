package com.balance.base.service;

import com.balance.base.dao.BaseCaseDao;
import com.balance.base.model.BaseCase;
import com.balance.base.vo.BaseCaseSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  刘凯
 * Date  2017/6/10.
 */
@Service
public class BaseCaseService {
    @Autowired
    private BaseCaseDao baseCaseDao;

    public int add(BaseCase baseCase) {
        return baseCaseDao.add(baseCase);
    }

    public int update(BaseCase baseCase) {
        return baseCaseDao.update(baseCase);
    }

    public int delete(int id) {
        return baseCaseDao.delete(id);
    }

    public BaseCase get(int id) {
        return baseCaseDao.get(id);
    }

    public List<BaseCase> list(BaseCaseSearchVO baseCaseSearchVO) {
        return baseCaseDao.list(baseCaseSearchVO);
    }

    public int listCount(BaseCaseSearchVO baseCaseSearchVO) {
        return baseCaseDao.listCount(baseCaseSearchVO);
    }
    public List<BaseCase> listAll() {
        return baseCaseDao.listAll();
    }
}
