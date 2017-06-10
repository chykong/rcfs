package com.balance.base.dao;

import com.balance.base.model.BasePolicy;
import com.balance.base.vo.BasePolicySearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import com.balance.util.string.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
@Repository
public class BasePolicyDao extends BaseDao<BasePolicy, BasePolicySearchVO> {
    /**
     * 新增
     *
     * @param basePolicy
     * @return
     */
    public int add(BasePolicy basePolicy) {
        String sql = "insert into t_base_policy(title,content,prj_base_info_id,created_at,created_by) values(:title,:content,:prj_base_info_id,now(),:created_by)";
        return update(sql, basePolicy);
    }

    /**
     * 修改
     *
     * @param basePolicy
     * @return
     */
    public int update(BasePolicy basePolicy) {
        String sql = "update t_base_policy set title=:title,content=:content,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, basePolicy);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_base_policy where id=?";
        return delete(sql, id);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public BasePolicy get(int id) {
        String sql = "select * from t_base_policy t where id=?";
        return get(sql, id);
    }

    /**
     * 分页列表
     *
     * @param basePolicySearchVO
     * @return
     */
    public List<BasePolicy> list(BasePolicySearchVO basePolicySearchVO) {
        String sql = "select id,title,prj_base_info_id,created_by,created_at,last_modified_by,last_modified_at from t_base_policy t where 1=1";
        sql += createSearchSql(basePolicySearchVO);
        sql += " order by id desc";
        sql = PageUtil.createMysqlPageSql(sql, basePolicySearchVO.getPageIndex(), basePolicySearchVO.getPageSize());
        return list(sql, basePolicySearchVO);
    }

    /**
     * 查询总数
     *
     * @param basePolicySearchVO
     * @return
     */
    public int listCount(BasePolicySearchVO basePolicySearchVO) {
        String sql = "select count(*) from t_base_policy t  where 1=1";
        sql += createSearchSql(basePolicySearchVO);
        return listCount(sql, basePolicySearchVO);
    }

    private String createSearchSql(BasePolicySearchVO basePolicySearchVO) {
        String sql = "";
        if (basePolicySearchVO.getPrj_base_info_id() != null) {//项目id
            sql += " and prj_base_info_id=:prj_base_info_id";
        }

        if (StringUtil.isNotNullOrEmpty(basePolicySearchVO.getTitle())) {//标题
            sql += " and title like :title_str";
        }
        return sql;
    }

    /**
     * 全部政策列表
     *
     * @return 列表
     */
    public List<BasePolicy> listAll(int prj_base_info_id) {
        String sql = "select id,title,prj_base_info_id,created_by,created_at,last_modified_by,last_modified_at from t_base_policy t where prj_base_info_id=?";
        return list(sql, prj_base_info_id);
    }
}
