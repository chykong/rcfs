package com.balance.base.dao;

import com.balance.base.model.BaseContacts;
import com.balance.base.model.BaseContacts;
import com.balance.base.vo.BaseContactsSearchVO;
import com.balance.base.vo.BaseContactsSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
@Repository
public class BaseContactsDao extends BaseDao<BaseContacts, BaseContactsSearchVO> {


    /**
     * 分页列表
     *
     * @param baseContactsSearchVO
     * @return
     */
    public List<BaseContacts> list(BaseContactsSearchVO baseContactsSearchVO) {
        String sql = "select * from t_base_contacts t where 1=1";
        sql += createSearchSql(baseContactsSearchVO);
        sql = PageUtil.createMysqlPageSql(sql, baseContactsSearchVO.getPageIndex(), baseContactsSearchVO.getPageSize());
        return list(sql, baseContactsSearchVO);
    }

    /**
     * 查询总数
     *
     * @param baseContactsSearchVO
     * @return
     */
    public int listCount(BaseContactsSearchVO baseContactsSearchVO) {
        String sql = "select count(*) from t_base_contacts t where 1=1 ";
        sql += createSearchSql(baseContactsSearchVO);
        return listCount(sql, baseContactsSearchVO);
    }


    private String createSearchSql(BaseContactsSearchVO baseContactsSearchVO) {
        String sql = "";
        if (baseContactsSearchVO.getType() != null) {//类型
            sql += " and type=:type";
        }
        return sql;
    }
    
    
    /**
     * 新增
     *
     * @param baseContacts
     * @return
     */
    public int add(BaseContacts baseContacts) {
        String sql = "insert into t_base_contacts(name,type,mobile,created_at,created_by,prj_base_info_id) values(:name,:type,:mobile,now(),:created_by,:prj_base_info_id)";
        return update(sql, baseContacts);
    }

    /**
     * 修改
     *
     * @param baseContacts
     * @return
     */
    public int update(BaseContacts baseContacts) {
        String sql = "update t_base_contacts set mobile=:mobile,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, baseContacts);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_base_contacts where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public BaseContacts get(int id) {
        String sql = "select * from t_base_contacts t where id=?";
        return get(sql, new Object[]{id});
    }


    /**
     * 全部公司列表
     * @return 列表
     */
    public List<BaseContacts> listAll() {
        String sql = "select * from t_base_contacts t ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(BaseContacts.class));
    }
}
