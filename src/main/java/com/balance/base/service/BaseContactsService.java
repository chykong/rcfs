package com.balance.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balance.base.dao.BaseContactsDao;
import com.balance.base.model.BaseContacts;
import com.balance.base.vo.BaseContactsSearchVO;

/**
 * Author 孔垂云 Date 2017/6/3.
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

	public int add(BaseContacts BaseContacts) {
		return baseContactsDao.add(BaseContacts);
	}

	public int update(BaseContacts BaseContacts) {
		return baseContactsDao.update(BaseContacts);
	}

	public int delete(int id) {
		return baseContactsDao.delete(id);
	}

	public BaseContacts get(int id) {
		return baseContactsDao.get(id);
	}

	public List<BaseContacts> listAll() {
		return baseContactsDao.listAll();
	}
}
