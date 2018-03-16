package com.balance.base.service;

import com.balance.base.dao.BaseHouseDao;
import com.balance.base.model.BaseHouse;
import com.balance.base.vo.BaseHouseSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 杨康康 on 2018/3/3.
 */
@Service
public class BaseHouseService {
    @Autowired
    private BaseHouseDao baseHouseDao;

    //添加户型
    public int addHouse(BaseHouse baseHouse) {
        return baseHouseDao.addHouse(baseHouse);
    }

    //修改戶型信息
    public int updateHouse(BaseHouse baseHouse) {
        return baseHouseDao.updateHouse(baseHouse);
    }

    //删除户型信息
    public int deleteHouse(String  id) {
        return baseHouseDao.deleteHouse(id);
    }

    //查询列表
    public List<BaseHouse> housesList(BaseHouseSearchVO baseHouseSearchVO) {
        return baseHouseDao.housesList(baseHouseSearchVO);
    }
    //得到一条数据
    public BaseHouse get(String  id) {
        return baseHouseDao.get(id);
    }
    //获取户型条数
    public int count(BaseHouseSearchVO baseHouseSearchVO) {
        return baseHouseDao.count(baseHouseSearchVO);
    }
}
