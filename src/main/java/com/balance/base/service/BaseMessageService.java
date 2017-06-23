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

    /**
     * 新增消息，同时新增消息接收人表
     * @param baseMessage
     * @return
     */
    public int add(BaseMessage baseMessage) {
        int message_id= baseMessageDao.add(baseMessage);
        BaseMessageread baseMessageread=new BaseMessageread();
        baseMessageread.setMessage_id(message_id);
        baseMessageread.setStatus(0);
        baseMessageread.setUser_id(baseMessage.getUser_id());
        baseMessagereadDao.add(baseMessageread);
        return 1;
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
