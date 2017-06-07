package com.balance.base.dao;

import com.balance.base.model.BaseContacts;
import com.balance.base.vo.BaseContactsSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import com.balance.util.string.StringUtil;
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
        if (baseContactsSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id=:prj_base_info_id";//项目id
        }
        if (StringUtil.isNotNullOrEmpty(baseContactsSearchVO.getName())) {
            sql += " and name like:name_str";//姓名
        }
        if (StringUtil.isNotNullOrEmpty(baseContactsSearchVO.getMobile())) {
            sql += " and mobile=:mobile";//手机号
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
        String sql = "insert into t_base_contacts(prj_base_info_id,name,mobile,duty,project_duty,note,created_at,created_by)" +
                " values(:prj_base_info_id,:name,:mobile,:duty,:project_duty,:note,now(),:created_by)";
        return update(sql, baseContacts);
    }

    /**
     * 修改
     *
     * @param baseContacts
     * @return
     */
    public int update(BaseContacts baseContacts) {
        String sql = "update t_base_contacts set mobile=:mobile,duty=:duty,project_duty=:project_duty,note=:note,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
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
     *
     * @return 列表
     */
    public List<BaseContacts> listAll(int prj_base_info_id) {
        String sql = "select * from t_base_contacts t where  ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BaseContacts.class));
    }


}
