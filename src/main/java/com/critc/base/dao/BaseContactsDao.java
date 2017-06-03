package com.critc.base.dao;

import com.critc.base.model.BaseContacts;
import com.critc.base.vo.BaseContactsSearchVO;
import com.critc.util.dao.BaseDao;
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
        //sql = PageUtil.createMysqlPageSql(sql, baseContactsSearchVO.getPageIndex(), baseContactsSearchVO.getPageSize());
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
}
