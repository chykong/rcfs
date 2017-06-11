package com.balance.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balance.api.dto.ContactsGroupsDTO;
import com.balance.base.dao.BaseMessageDao;
import com.balance.base.model.BaseMessage;
import com.balance.base.model.BaseParticipant;
import com.balance.base.vo.BaseMessageSearchVO;

/**
 * Author 李晓明 Date 2017/6/11.
 */
@Service
public class BaseMessageService {

	@Autowired
	private BaseMessageDao baseMessageDao;

	public List<BaseMessage> list(BaseMessageSearchVO baseMessageSearchVO) {
		return baseMessageDao.list(baseMessageSearchVO);
	}

	public int listCount(BaseMessageSearchVO baseMessageSearchVO) {
		return baseMessageDao.listCount(baseMessageSearchVO);
	}

	public int add(BaseMessage baseMessage) {
		return baseMessageDao.add(baseMessage);
	}
	
	public int update(BaseMessage baseMessage) {
        return baseMessageDao.update(baseMessage);
    }

	public int delete(int id) {
		return baseMessageDao.delete(id);
	}

	public BaseMessage get(int id) {
		return baseMessageDao.get(id);
	}
}
