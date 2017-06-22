package com.balance.base.dao;

import com.balance.base.model.BaseCase;
import com.balance.base.vo.BaseCaseSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  劉凱
 * Date  2017/6/10.
 */
@Repository
public class BaseCaseDao extends BaseDao<BaseCase, BaseCaseSearchVO> {
    /**
     * 新增
     *
     * @param baseCase
     * @return
     */
    public int add(BaseCase baseCase) {
        String sql = "insert into t_base_case(title,content,created_at,created_by) values(:title,:content,now(),:created_by)";
        return update(sql, baseCase);
    }

    /**
     * 修改
     *
     * @param baseCase
     * @return
     */
    public int update(BaseCase baseCase) {
        String sql = "update t_base_case set title=:title,content=:content,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, baseCase);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_base_case where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public BaseCase get(int id) {
        String sql = "select * from t_base_case t where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 分页列表
     *
     * @param baseCaseSearchVO
     * @return
     */
    public List<BaseCase> list(BaseCaseSearchVO baseCaseSearchVO) {
        String sql = "select id,title,created_at,created_by from t_base_case t order by id desc";
        sql = PageUtil.createMysqlPageSql(sql, baseCaseSearchVO.getPageIndex(), baseCaseSearchVO.getPageSize());
        return list(sql, baseCaseSearchVO);
    }

    /**
     * 查询总数
     *
     * @param baseCaseSearchVO
     * @return
     */
    public int listCount(BaseCaseSearchVO baseCaseSearchVO) {
        String sql = "select count(*) from t_base_case t  ";
        return listCount(sql, baseCaseSearchVO);
    }
    /**
     * 全部经典项目列表
     * @return 列表
     */
    public List<BaseCase> listAll() {
        String sql = "select id,title from t_base_case t ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(BaseCase.class));
    }
}
