package com.balance.base.dao;

import com.balance.base.model.BaseCompany;
import com.balance.base.vo.BaseCompanySearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
@Repository
public class BaseCompanyDao extends BaseDao<BaseCompany, BaseCompanySearchVO> {
    /**
     * 新增
     *
     * @param baseCompany
     * @return
     */
    public int add(BaseCompany baseCompany) {
        String sql = "insert into t_base_company(name,type,created_at,created_by) values(:name,:type,now(),:created_by)";
        return update(sql, baseCompany);
    }

    /**
     * 修改
     *
     * @param baseCompany
     * @return
     */
    public int update(BaseCompany baseCompany) {
        String sql = "update t_base_company set name=:name,type=:type,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, baseCompany);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_base_company where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public BaseCompany get(int id) {
        String sql = "select * from t_base_company t where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 分页列表
     *
     * @param baseCompanySearchVO
     * @return
     */
    public List<BaseCompany> list(BaseCompanySearchVO baseCompanySearchVO) {
        String sql = "select * from t_base_company t ";
        sql = PageUtil.createMysqlPageSql(sql, baseCompanySearchVO.getPageIndex(), baseCompanySearchVO.getPageSize());
        return list(sql, baseCompanySearchVO);
    }

    /**
     * 查询总数
     *
     * @param baseCompanySearchVO
     * @return
     */
    public int listCount(BaseCompanySearchVO baseCompanySearchVO) {
        String sql = "select count(*) from t_base_company t  ";
        return listCount(sql, baseCompanySearchVO);
    }
}
