package com.balance.prj.dao;

import com.balance.prj.model.PrjBaseinfo;
import com.balance.prj.vo.PrjBaseinfoSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
@Repository
public class PrjBaseinfoDao extends BaseDao<PrjBaseinfo, PrjBaseinfoSearchVO> {
    /**
     * 新增
     *
     * @param prjBaseinfo
     * @return
     */
    public int add(PrjBaseinfo prjBaseinfo) {
        String sql = "insert into t_prj_baseinfo(prj_name,introduction,created_at,created_by) values(:prj_name,:introduction,now(),:created_by)";
        return update(sql, prjBaseinfo);
    }

    /**
     * 修改
     *
     * @param prjBaseinfo
     * @return
     */
    public int update(PrjBaseinfo prjBaseinfo) {
        String sql = "update t_prj_baseinfo set  introduction=:introduction,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, prjBaseinfo);
    }
    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_prj_baseinfo where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public PrjBaseinfo get(int id) {
        String sql = "select * from t_prj_baseinfo t where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 分页列表
     *
     * @param prjBaseinfoSearchVO
     * @return
     */
    public List<PrjBaseinfo> list(PrjBaseinfoSearchVO prjBaseinfoSearchVO) {
        String sql = "select * from t_prj_baseinfo t ";
        sql = PageUtil.createMysqlPageSql(sql, prjBaseinfoSearchVO.getPageIndex(), prjBaseinfoSearchVO.getPageSize());
        return list(sql, prjBaseinfoSearchVO);
    }

    /**
     * 查询总数
     *
     * @param prjBaseinfoSearchVO
     * @return
     */
    public int listCount(PrjBaseinfoSearchVO prjBaseinfoSearchVO) {
        String sql = "select count(*) from t_prj_baseinfo t  ";
        return listCount(sql, prjBaseinfoSearchVO);
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<PrjBaseinfo> list() {
        String sql = "select id,prj_name from t_prj_baseinfo t order by id desc";
        return list(sql);
    }
}
