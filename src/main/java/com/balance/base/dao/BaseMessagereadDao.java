package com.balance.base.dao;

import com.balance.base.model.BaseMessageread;
import com.balance.base.vo.BaseMessagereadSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  刘凯
 * Date  2017/6/10.
 */
@Repository
public class BaseMessagereadDao extends BaseDao<BaseMessageread, BaseMessagereadSearchVO> {
    /**
     * 新增
     *
     * @param baseMessageread
     * @return
     */
    public int add(BaseMessageread baseMessageread) {
        String sql = "insert into t_base_messageread(message_id,user_id,status) values(:message_id,:user_id,:status)";
        return update(sql, baseMessageread);
    }

    /**
     * 修改
     *
     * @param baseMessageread
     * @return
     */
    public int update(BaseMessageread baseMessageread) {
        String sql = "update t_base_messageread set message_id=:message_id,user_id=:user_id,status=:status,read_at=:read_at where id=:id";
        return update(sql, baseMessageread);
    }

    /**
     * 修改消息读取状态
     *
     * @param baseMessageread
     * @return
     */
    public int updateStatus(BaseMessageread baseMessageread) {
        String sql = "update t_base_messageread set status=:status,read_at=:read_at where message_id=:message_id and user_id=:user_id";
        return update(sql, baseMessageread);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_base_messageread where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public BaseMessageread get(int id) {
        String sql = "select * from t_base_messageread t where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 分页列表
     *
     * @param baseMessagereadSearchVO
     * @return
     */
    public List<BaseMessageread> list(BaseMessagereadSearchVO baseMessagereadSearchVO) {
        String sql = "select * from t_base_messageread t ";
        sql = PageUtil.createMysqlPageSql(sql, baseMessagereadSearchVO.getPageIndex(), baseMessagereadSearchVO.getPageSize());
        return list(sql, baseMessagereadSearchVO);
    }

    /**
     * 查询总数
     *
     * @param baseMessagereadSearchVO
     * @return
     */
    public int listCount(BaseMessagereadSearchVO baseMessagereadSearchVO) {
        String sql = "select count(*) from t_base_messageread t  ";
        return listCount(sql, baseMessagereadSearchVO);
    }

    /**
     * 全部接收消息列表
     *
     * @return 列表
     */
    public List<BaseMessageread> listAll() {
        String sql = "select * from t_base_messageread t ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BaseMessageread.class));
    }
}
