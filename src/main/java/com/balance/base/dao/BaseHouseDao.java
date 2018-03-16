package com.balance.base.dao;

import com.balance.base.model.BaseHouse;
import com.balance.base.vo.BaseHouseSearchVO;
import com.balance.util.dao.BaseDao;
import com.balance.util.page.PageUtil;
import com.balance.util.string.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨康康 on 2018/3/3.
 */
@Repository
public class BaseHouseDao extends BaseDao<BaseHouse, BaseHouseSearchVO> {
    //添加户型信息
    public int addHouse(BaseHouse baseHouse) {
        String sql = "INSERT  INTO t_base_house(name,house_image,house_describe,prj_base_info_id) VALUES(:name,:houseImage,:houseDescribe,:prjBaseInfoId)";
        return update(sql, baseHouse);
    }

    //修改戶型信息
    public int updateHouse(BaseHouse baseHouse) {
        String sql = "update t_base_house  SET  name=:name,house_image=:houseImage,house_describe=:houseDescribe  where id=:id";
        return update(sql, baseHouse);
    }

    //删除户型信息
    public int deleteHouse(String  id) {
        String sql = "delete from t_base_house where  id=?";
        return delete(sql, new Object[]{id});
    }

    //查询列表
    public List<BaseHouse> housesList(BaseHouseSearchVO baseHouseSearchVO) {
        String sql = "select * from  t_base_house where 1=1 ";
        sql += createSearchSql(baseHouseSearchVO);
        sql +="  and  prj_base_info_id=:prjBaseInfoId";
        sql += " order by id desc";
        sql = PageUtil.createMysqlPageSql(sql, baseHouseSearchVO.getPageIndex(), baseHouseSearchVO.getPageSize());
        return list(sql, baseHouseSearchVO);
    }

    //获取户型条数
    public int count(BaseHouseSearchVO baseHouseSearchVO) {
        String sql = "select count(*) from  t_base_house  where 1=1 ";
        sql += createSearchSql(baseHouseSearchVO);
        sql +="  and  prj_base_info_id=:prjBaseInfoId";
        return listCount(sql, baseHouseSearchVO);
    }

    //得到一条数据
    public BaseHouse get(String  id) {
        String sql = "select  *  from  t_base_house  where  id=?";
        return get(sql,new Object[]{id});
    }

    private String createSearchSql(BaseHouseSearchVO baseHouseSearchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(baseHouseSearchVO.getName())) {
            sql += " and name like :name_str";//姓名
        }
        return sql;
    }


}
