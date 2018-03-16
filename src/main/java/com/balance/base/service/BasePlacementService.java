package com.balance.base.service;

import com.balance.base.dao.BasePlacementDao;
import com.balance.base.model.BasePlacement;
import com.balance.util.config.PubConfig;
import com.balance.util.file.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by  杨康康 on 2017/8/29.
 * 安置房概况  service
 */
@Service
public class BasePlacementService {
    @Autowired
    private BasePlacementDao basePlacementDao;
    @Autowired
    private PubConfig pubConfig;

    public int update(BasePlacement basePlacement) {
        BasePlacement basePlacementDb = basePlacementDao.get(basePlacement.getPrj_base_info_id());
        if (basePlacementDb != null) {
            if (!basePlacement.getAttach_path().equals(basePlacementDb.getAttach_path())) {
                FileUtil.delete(pubConfig.getImageUploadPath() + basePlacementDb.getAttach_path());
            }
            return basePlacementDao.update(basePlacement);
        }
        return basePlacementDao.add(basePlacement);
    }

    public BasePlacement get(int id) {
        return basePlacementDao.get(id);
    }

}
