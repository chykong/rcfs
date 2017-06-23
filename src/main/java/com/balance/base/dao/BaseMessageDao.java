package com.balance.base.dao;

import com.balance.api.dto.BaseMessageDTO;
import com.balance.base.model.BaseMessage;
import com.balance.base.vo.BaseMessageSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author 李晓明 Date 2017/6/11.
 */
@Repository
public class BaseMessageDao extends BaseDao<BaseMessage, BaseMessageSearchVO> {

    /**
     * 分页列表
     *
     * @param baseMessageSearchVO
     * @return
     */
    public List<BaseMessage> list(BaseMessageSearchVO baseMessageSearchVO) {
        String sql = "select t.id,t.title,t.send_at,t.send_by,r.user_id,r.status,u.realname user_realname from t_base_message t ,t_base_messageread r,t_sys_user u where t.id=r.message_id and r.user_id=u.id ";
        sql += createSearchSql(baseMessageSearchVO);
        sql = PageUtil.createMysqlPageSql(sql, baseMessageSearchVO.getPageIndex(), baseMessageSearchVO.getPageSize());
        return list(sql, baseMessageSearchVO);
    }

    /**
     * 查询总数
     *
     * @param baseMessageSearchVO
     * @return
     */
    public int listCount(BaseMessageSearchVO baseMessageSearchVO) {
        String sql = "select count(*) from t_base_message t where 1=1 ";
        sql += createSearchSql(baseMessageSearchVO);
        return listCount(sql, baseMessageSearchVO);
    }

    private String createSearchSql(BaseMessageSearchVO baseMessageSearchVO) {
        String sql = "";
        if (baseMessageSearchVO.getTitle() != null) {
            sql += " and title like :title";
        }
        return sql;
    }

    /**
     * 新增
     *
     * @param baseMessage
     * @return
     */
    public int add(BaseMessage baseMessage) {
        String sql = "insert into t_base_message(title,content,send_at,send_by)" + " values(:title,:content,now(),:send_by)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rc = getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(baseMessage), keyHolder);
        if (rc > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return 0;
        }
    }

    /**
     * 修改
     *
     * @return
     */
    public int update(BaseMessage baseMessage) {
        String sql = "update t_base_message set title=:title,content=:content where id=:id";
        return update(sql, baseMessage);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_base_message where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public BaseMessage get(int id) {
        String sql = "select * from t_base_message t where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 查询用户下的所有消息
     *
     * @param user_id
     * @return
     */
    public List<BaseMessageDTO> listByUser_id(int user_id) {
        String sql = "select t.id,t.title,r.status messageStatus  from t_base_message t ,t_base_messageread r,t_sys_user u where t.id=r.message_id and r.user_id=u.id ";
        sql += " and u.id=?";
        return jdbcTemplate.query(sql, new Object[]{user_id}, BeanPropertyRowMapper.newInstance(BaseMessageDTO.class));
    }

    /**
     * 查询未读消息数
     *
     * @param user_id
     * @return
     */
    public int listUnReadCountByUser_id(int user_id) {
        String sql = "select count(*) user_realname from t_base_message t ,t_base_messageread r,t_sys_user u where t.id=r.message_id and r.user_id=u.id ";
        sql += " and u.id=? and r.status=0";
        return jdbcTemplate.queryForObject(sql, new Object[]{user_id}, Integer.class);
    }


    /**
     * 根据消息id和用户id获取消息内容
     *
     * @param message_id
     * @return
     */
    public BaseMessageDTO getByMessageAndUser_id(int message_id, int user_id) {
        String sql = "select t.id,t.title,r.status messageStatus,t.content  from t_base_message t ,t_base_messageread r,t_sys_user u where t.id=r.message_id and r.user_id=u.id ";
        sql += " and t.id=? and u.id=? ";
        List<BaseMessageDTO> list = jdbcTemplate.query(sql, new Object[]{message_id, user_id}, BeanPropertyRowMapper.newInstance(BaseMessageDTO.class));
        return list.size() > 0 ? list.get(0) : null;
    }
}
