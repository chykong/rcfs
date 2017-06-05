package com.balance.prj.dao;

import com.balance.prj.model.PrjPresentation;
import com.balance.prj.vo.PrjPresentationSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;

import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 项目简报
 * @author 刘凯
 * @date 2017年6月5日
 */
@Repository
public class PrjPresentationDao extends BaseDao<PrjPresentation, PrjPresentationSearchVO> {
    /**
     * 查询总数
     *
     * @return
     */
    public int listCount(PrjPresentationSearchVO prjPresentationSearchVO) {
        String sql = "select count(*) from t_prj_presentation t where 1=1";
        sql+=createSearchSql(prjPresentationSearchVO);
        return listCount(sql, prjPresentationSearchVO);
    }

    public List<PrjPresentation> list(PrjPresentationSearchVO prjPresentationSearchVO) {
        String sql = "select * from t_prj_presentation t where 1=1";
        sql+=createSearchSql(prjPresentationSearchVO);
        sql = PageUtil.createMysqlPageSql(sql, prjPresentationSearchVO.getPageIndex(), prjPresentationSearchVO.getPageSize());
        return list(sql, prjPresentationSearchVO);
    }

    private String createSearchSql(PrjPresentationSearchVO prjPresentationSearchVO) {
        String sql = "";
        if (prjPresentationSearchVO.getProgress() != null) {//名称模糊查询
            sql += " and progress =:progress";
        }
        return sql;
    }

    /**
     * 新增简报内容
     *
     * @param prjPresentation
     * @return
     */
    public int add(PrjPresentation prjPresentation) {
        String sql = "insert into t_prj_presentation(prj_base_info_id,title,content,file_name,file_path,progress,created_by,created_at)values(:prj_base_info_id,:title,:content,:file_name,:file_path,:progress,:created_by,now())";
        return update(sql, prjPresentation);
    }
    /**
     * 根据id查询prjPresentation
     */
    public PrjPresentation findById(int id){
    	String sql="select * from t_prj_presentation where id=?";
    	return get(sql, new Object[]{id});
    }
    /**
     * 修改项目简报
     */
    public int update(PrjPresentation prjPresentation){
    	String sql="update t_prj_presentation set progress=:progress,title=:title,content=:content,file_name=:file_name,file_path=:file_path,last_modified_at=now(),last_modified_by=:last_modified_by where id=:id";
    	return update(sql,prjPresentation);
    }
    /**
     * 删除项目简报
     */ 
    public int delete(int id){
    	String sql="delete from t_prj_presentation where id=?";
    	return delete(sql,new Object[]{id});
    }
}
