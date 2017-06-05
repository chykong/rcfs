package com.balance.sys.service;

import com.balance.sys.dao.SysUserprojectsDao;
import com.balance.sys.model.SysUserprojects;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
@Service
public class SysUserprojectsService {
    @Autowired
    private SysUserprojectsDao sysUserprojectsDao;

    /**
     * 新增
     *
     * @return
     */
    public void save(int user_id, String prj_base_info_ids) {
        sysUserprojectsDao.delete(user_id);
        String[] prj_base_info_idArr = prj_base_info_ids.split("\\,");
        for (int i = 0; i < prj_base_info_idArr.length; i++) {
            if (StringUtil.isNotNullOrEmpty(prj_base_info_idArr[i])) {
                SysUserprojects sysUserprojects = new SysUserprojects(user_id, Integer.parseInt(prj_base_info_idArr[i]));
                sysUserprojectsDao.add(sysUserprojects);
            }
        }
    }

    /**
     * 获取该用户的所有项目id
     *
     * @param user_id
     * @return
     */
    public List<SysUserprojects> getPrj_base_info_ids(int user_id) {
        return sysUserprojectsDao.list(user_id);
    }




}
