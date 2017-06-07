package com.balance.base.service;

import com.balance.base.dao.BaseParticipantDao;
import com.balance.base.model.BaseParticipant;
import com.balance.base.vo.BaseParticipantSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author 孔垂云 Date 2017/6/3.
 */
@Service
public class BaseParticipantService {

	@Autowired
	private BaseParticipantDao baseParticipantDao;

	public List<BaseParticipant> list(BaseParticipantSearchVO baseParticipantSearchVO) {
		return baseParticipantDao.list(baseParticipantSearchVO);
	}

	public int listCount(BaseParticipantSearchVO baseParticipantSearchVO) {
		return baseParticipantDao.listCount(baseParticipantSearchVO);
	}

	public int add(BaseParticipant BaseParticipant) {
		return baseParticipantDao.add(BaseParticipant);
	}

	public int update(BaseParticipant BaseParticipant) {
		return baseParticipantDao.update(BaseParticipant);
	}

	public int delete(int id) {
		return baseParticipantDao.delete(id);
	}

	public BaseParticipant get(int id) {
		return baseParticipantDao.get(id);
	}

}
