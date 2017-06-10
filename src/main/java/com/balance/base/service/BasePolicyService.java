package com.balance.base.service;

import com.balance.base.dao.BasePolicyDao;
import com.balance.base.model.BasePolicy;
import com.balance.base.vo.BasePolicySearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/3.
 */
@Service
public class BasePolicyService {
    @Autowired
    private BasePolicyDao basePolicyDao;

    public int add(BasePolicy basePolicy) {
        return basePolicyDao.add(basePolicy);
    }

    public int update(BasePolicy basePolicy) {
        return basePolicyDao.update(basePolicy);
    }

    public int delete(int id) {
        return basePolicyDao.delete(id);
    }

    public BasePolicy get(int id) {
        return basePolicyDao.get(id);
    }

    public List<BasePolicy> list(BasePolicySearchVO basePolicySearchVO) {
        return basePolicyDao.list(basePolicySearchVO);
    }

    public int listCount(BasePolicySearchVO basePolicySearchVO) {
        return basePolicyDao.listCount(basePolicySearchVO);
    }
    public List<BasePolicy> listAll(int prj_base_info_id) {
        return basePolicyDao.listAll(prj_base_info_id);
    }
}
