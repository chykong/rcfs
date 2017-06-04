package com.balance.sys.dao;

import com.balance.sys.model.SysUserprojects;
import com.balance.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
@Repository
public class SysUserprojectsDao extends BaseDao<SysUserprojects, SysUserprojects> {
    /**
     * 新增
     *
     * @param sysUserprojects
     * @return
     */
    public int add(SysUserprojects sysUserprojects) {
        String sql = "insert into t_sys_userprojects(user_id,prj_base_info_id) values(:user_id,:prj_base_info_id)";
        return update(sql, sysUserprojects);
    }

    /**
     * 删除
     *
     * @param user_id
     * @return
     */
    public int delete(int user_id) {
        String sql = "delete from t_sys_userprojects where user_id=?";
        return delete(sql, new Object[]{user_id});
    }

    /**
     * 获取该用户id下的所有项目
     *
     * @param user_id
     * @return
     */
    public List<SysUserprojects> list(int user_id) {
        String sql = "select * from t_sys_userprojects where user_id=?";
        return list(sql, new Object[]{user_id});
    }
}
