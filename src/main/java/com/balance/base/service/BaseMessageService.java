package com.balance.base.service;

import com.balance.base.dao.BaseMessageDao;
import com.balance.base.dao.BaseMessagereadDao;
import com.balance.base.model.BaseMessage;
import com.balance.base.model.BaseMessageread;
import com.balance.base.vo.BaseMessageSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author 李晓明 Date 2017/6/11.
 */
@Service
public class BaseMessageService {

    @Autowired
    private BaseMessageDao baseMessageDao;
    @Autowired
    private BaseMessagereadDao baseMessagereadDao;


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


    /**
     * 修改消息读取状态
     *
     * @param baseMessageread
     * @return
     */
    public int updateStatus(BaseMessageread baseMessageread) {
        return baseMessagereadDao.updateStatus(baseMessageread);
    }
}
