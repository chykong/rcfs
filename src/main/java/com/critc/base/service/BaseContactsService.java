package com.critc.base.service;

import com.critc.base.dao.BaseContactsDao;
import com.critc.base.model.BaseContacts;
import com.critc.base.vo.BaseContactsSearchVO;
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
