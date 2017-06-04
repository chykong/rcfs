package com.balance.base.service;

import com.balance.base.dao.BaseContactsDao;
import com.balance.base.model.BaseContacts;
import com.balance.base.vo.BaseContactsSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
@Service
public class BaseContactsService {

    @Autowired
    private BaseContactsDao baseContactsDao;

    public List<BaseContacts> list(BaseContactsSearchVO baseContactsSearchVO) {
        return baseContactsDao.list(baseContactsSearchVO);
    }

    public int listCount(BaseContactsSearchVO baseContactsSearchVO) {
        return baseContactsDao.listCount(baseContactsSearchVO);
    }
}
