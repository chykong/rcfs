package com.balance.base.dao;

import com.balance.base.model.BaseParticipant;
import com.balance.base.vo.BaseParticipantSearchVO;
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
public class BaseParticipantDao extends BaseDao<BaseParticipant, BaseParticipantSearchVO> {


    /**
     * 分页列表
     *
     * @param baseParticipantSearchVO
     * @return
     */
    public List<BaseParticipant> list(BaseParticipantSearchVO baseParticipantSearchVO) {
        String sql = "select * from t_base_participant t where 1=1";
        sql += createSearchSql(baseParticipantSearchVO);
        sql = PageUtil.createMysqlPageSql(sql, baseParticipantSearchVO.getPageIndex(), baseParticipantSearchVO.getPageSize());
        return list(sql, baseParticipantSearchVO);
    }

    /**
     * 查询总数
     *
     * @param baseParticipantSearchVO
     * @return
     */
    public int listCount(BaseParticipantSearchVO baseParticipantSearchVO) {
        String sql = "select count(*) from t_base_participant t where 1=1 ";
        sql += createSearchSql(baseParticipantSearchVO);
        return listCount(sql, baseParticipantSearchVO);
    }


    private String createSearchSql(BaseParticipantSearchVO baseParticipantSearchVO) {
        String sql = "";
        if (baseParticipantSearchVO.getPrj_base_info_id() != null) {
            sql += " and prj_base_info_id=:prj_base_info_id";//项目id
        }
        if (StringUtil.isNotNullOrEmpty(baseParticipantSearchVO.getName())) {
            sql += " and name like :name_str";//姓名
        }
        if (StringUtil.isNotNullOrEmpty(baseParticipantSearchVO.getMobile())) {
            sql += " and mobile=:mobile";//手机号
        }
        return sql;
    }


    /**
     * 新增
     *
     * @param baseParticipant
     * @return
     */
    public int add(BaseParticipant baseParticipant) {
        String sql = "insert into t_base_participant(prj_base_info_id,name,company,groups,mobile,duty,project_duty,created_at,created_by,release_date)" +
                " values(:prj_base_info_id,:name,:company,:groups,:mobile,:duty,:project_duty,now(),:created_by,:release_date)";
        return update(sql, baseParticipant);
    }

    /**
     * 修改
     *
     * @param baseParticipant
     * @return
     */
    public int update(BaseParticipant baseParticipant) {
        String sql = "update t_base_participant set name=:name,mobile=:mobile,company=:company,groups=:groups,duty=:duty,project_duty=:project_duty,created_by=:created_by,release_date:=release_date,last_modified_at=now(),release_date=:release_date,last_modified_by=:last_modified_by where id=:id";
        return update(sql, baseParticipant);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_base_participant where id=?";
        return delete(sql, new Object[]{id});
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public BaseParticipant get(int id) {
        String sql = "select * from t_base_participant t where id=?";
        return get(sql, new Object[]{id});
    }


    /**
     * 全部公司列表
     *
     * @return 列表
     */
    public List<BaseParticipant> listAll(int prj_base_info_id) {
        String sql = "select * from t_base_participant t where  ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BaseParticipant.class));
    }


}
