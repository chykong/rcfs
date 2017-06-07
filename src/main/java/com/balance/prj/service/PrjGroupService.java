package com.balance.prj.service;

import com.balance.prj.dao.PrjGroupDao;
import com.balance.prj.model.PrjGroup;
import com.balance.prj.vo.PrjGroupSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
@Service
public class PrjGroupService {

    @Autowired
    private PrjGroupDao prjGroupDao;

    public int add(PrjGroup prjGroup) {
        return prjGroupDao.add(prjGroup);
    }

    public int update(PrjGroup prjGroup) {
        return prjGroupDao.update(prjGroup);
    }

    public int delete(int id) {
        return prjGroupDao.delete(id);
    }

    public PrjGroup get(int id) {
        return prjGroupDao.get(id);
    }

    public List<PrjGroup> list(PrjGroupSearchVO prjGroupSearchVO) {
        return prjGroupDao.list(prjGroupSearchVO);
    }

    public int listCount(PrjGroupSearchVO prjGroupSearchVO) {
        return prjGroupDao.listCount(prjGroupSearchVO);
    }

    /**
     * 根据id查组别
     *
     * @param section_id
     * @return
     */
    public List<PrjGroup> listBySection_id(int section_id) {
        return prjGroupDao.listBySection_id(section_id);
    }
}
