package com.balance.prj.service;

import com.balance.prj.dao.PrjPreallocationDao;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户信息service
 */
@Service
public class PrjPreallocationService {
    @Autowired
    private PrjPreallocationDao prjPreallocationDao;

    public List<PrjPreallocation> findAll(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        return prjPreallocationDao.findAll(prjPreallocationSearchVO);
    }

    public int count(PrjPreallocationSearchVO prjPreallocationSearchVO){
        return prjPreallocationDao.count(prjPreallocationSearchVO);
    }

    public int add(PrjPreallocation prjPreallocation){
        return prjPreallocationDao.add(prjPreallocation);
    }

    public int update(PrjPreallocation prjPreallocation){
        return prjPreallocationDao.update(prjPreallocation);
    }

    public PrjPreallocation getById(int id){
        return prjPreallocationDao.getById(id);
    }
    public boolean existByMapId(String map_Id, int project_id) {
        return prjPreallocationDao.existByMapId(map_Id, project_id);
    }

    public boolean existByName(String name, int project_id) {
        return prjPreallocationDao.existByName(name, project_id);
    }

    public List<PrjPreallocation> getHostNameByMapId(String mapId) {
        return prjPreallocationDao.getHostNameByMapId(mapId);
    }

    public PrjPreallocation updateBasic(PrjPreallocation prjPreallocation) {
        return prjPreallocationDao.updateBasic(prjPreallocation);
    }

    public PrjPreallocation updateInHost(PrjPreallocation prjPreallocation) {
        return prjPreallocationDao.updateInHost(prjPreallocation);
    }

    public PrjPreallocation updateCompensation(PrjPreallocation prjPreallocation) {
        return prjPreallocationDao.updateCompensation(prjPreallocation);
    }

    public PrjPreallocation updateChooseRoom(PrjPreallocation prjPreallocation) {
        return prjPreallocationDao.updateChooseRoom(prjPreallocation);
    }

    public PrjPreallocation updateArchive(PrjPreallocation prjPreallocation) {
        return prjPreallocationDao.updateArchive(prjPreallocation);
    }

    public boolean updateToUnaudited(int id) {
        return prjPreallocationDao.updateToUnaudited(id);
    }
}
