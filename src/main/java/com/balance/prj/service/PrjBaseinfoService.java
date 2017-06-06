package com.balance.prj.service;

import com.balance.prj.dao.PrjBaseinfoDao;
import com.balance.prj.model.PrjBaseinfo;
import com.balance.prj.vo.PrjBaseinfoSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/4.
 */
@Service
public class PrjBaseinfoService {
    @Autowired
    private PrjBaseinfoDao prjBaseinfoDao;

    public int add(PrjBaseinfo prjBaseinfo) {
        return prjBaseinfoDao.add(prjBaseinfo);
    }

    public int update(PrjBaseinfo prjBaseinfo) {
        return prjBaseinfoDao.update(prjBaseinfo);
    }

    public int delete(int id) {
        return prjBaseinfoDao.delete(id);
    }

    public PrjBaseinfo get(int id) {
        return prjBaseinfoDao.get(id);
    }

    public List<PrjBaseinfo> list(PrjBaseinfoSearchVO prjBaseinfoSearchVO) {
        return prjBaseinfoDao.list(prjBaseinfoSearchVO);
    }

    public int listCount(PrjBaseinfoSearchVO prjBaseinfoSearchVO) {
        return prjBaseinfoDao.listCount(prjBaseinfoSearchVO);
    }

    /**
     * 所有列表
     *
     * @return
     */
    public List<PrjBaseinfo> list() {
        return prjBaseinfoDao.list();
    }

    /**
     * 获取该用户的所有项目
     *
     * @param user_id
     * @return
     */
    public List<PrjBaseinfo> listSelectProject(int user_id) {
        return prjBaseinfoDao.listSelectProject(user_id);
    }

    /**
     * 修改简介、流程、框架字段
     *
     * @return
     */
    public int updateIntro(int id, int type, String content) {
        return prjBaseinfoDao.updateIntro(id, type, content);
    }
}
