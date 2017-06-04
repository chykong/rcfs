package com.balance.prj.dao;

import com.balance.prj.model.PrjSection;
import com.balance.prj.vo.PrjSectionSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
@Service
public class PrjSectionDao extends BaseDao<PrjSection, PrjSectionSearchVO> {

    /**
     * 新增
     *
     * @param prjSection
     * @return
     */
    public int add(PrjSection prjSection) {
        String sql = "insert into t_prj_section(name,prj_base_info_id,total_homes,created_at,created_by) values(:name,:prj_base_info_id,:total_homes,now(),:created_by)";
        return update(sql, prjSection);
    }

    /**
     * 修改
     *
     * @param prjSection
     * @return
     */
    public int update(PrjSection prjSection) {
        String sql = "update t_prj_section set name=:name,prj_base_info_id=:prj_base_info_id,total_homes=:total_homes,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
        return update(sql, prjSection);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_prj_section where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public PrjSection get(int id) {
        String sql = "select * from t_prj_section t where id=?";
        return get(sql, new Object[]{id});
    }

    /**
     * 分页列表
     *
     * @param prjSectionSearchVO
     * @return
     */
    public List<PrjSection> list(PrjSectionSearchVO prjSectionSearchVO) {
        String sql = "select * from t_prj_section t ";
        sql = PageUtil.createMysqlPageSql(sql, prjSectionSearchVO.getPageIndex(), prjSectionSearchVO.getPageSize());
        return list(sql, prjSectionSearchVO);
    }

    /**
     * 查询总数
     *
     * @param prjSectionSearchVO
     * @return
     */
    public int listCount(PrjSectionSearchVO prjSectionSearchVO) {
        String sql = "select count(*) from t_prj_section t  ";
        return listCount(sql, prjSectionSearchVO);
    }


    /**
     * 根据id查区段
     *
     * @param prj_base_info_id
     * @return
     */
    public List<PrjSection> list(int prj_base_info_id) {
        String sql = "select * from t_prj_section t where prj_base_info_id=? ";
        return list(sql, new Object[]{prj_base_info_id});
    }

}
