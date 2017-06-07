package com.balance.prj.service;

import com.balance.prj.dao.PrjSectionDao;
import com.balance.prj.model.PrjSection;
import com.balance.prj.vo.PrjSectionSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
@Service
public class PrjSectionService {

    @Autowired
    private PrjSectionDao prjSectionDao;

    public int add(PrjSection prjSection) {
        return prjSectionDao.add(prjSection);
    }

    public int update(PrjSection prjSection) {
        return prjSectionDao.update(prjSection);
    }

    public int delete(int id) {
        return prjSectionDao.delete(id);
    }

    public PrjSection get(int id) {
        return prjSectionDao.get(id);
    }

    public List<PrjSection> list(PrjSectionSearchVO prjSectionSearchVO) {
        return prjSectionDao.list(prjSectionSearchVO);
    }

    public int listCount(PrjSectionSearchVO prjSectionSearchVO) {
        return prjSectionDao.listCount(prjSectionSearchVO);
    }

    /**
     * 根据id查区段
     *
     * @param prj_base_info_id
     * @return
     */
    public List<PrjSection> listByprj_base_info_id(int prj_base_info_id) {
        return prjSectionDao.listByprj_base_info_id(prj_base_info_id);
    }
}
