package com.balance.base.service;

import com.balance.base.dao.BasePlacementDetailDao;
import com.balance.base.model.BaseHouse;
import com.balance.base.model.BasePlacementDetail;
import com.balance.base.model.BasePlacementFloor;
import com.balance.base.model.BasePlacementSelect;
import com.balance.base.vo.BasePlacementDetailSearchVO;
import com.balance.util.excel.Excel2007Util;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Author  dsy
 * Date  2017/8/29.
 */
@Service
public class BasePlacementDetailService {
    @Autowired
    private BasePlacementDetailDao basePlacementDetailDao;

    public List<BasePlacementDetail> listDetail(BasePlacementDetailSearchVO placementDetailSearchVO) {
        return basePlacementDetailDao.listDetail(placementDetailSearchVO);
    }

    public int add(BasePlacementDetail basePlacementDetail) {
        return basePlacementDetailDao.add(basePlacementDetail);
    }

    public int update(BasePlacementDetail basePlacementDetail) {
        return basePlacementDetailDao.update(basePlacementDetail);
    }

    public BasePlacementDetail get(int id) {
        return basePlacementDetailDao.get(id);
    }

    public BasePlacementDetail getByBuildAndMap(String build_code, String map_code) {
        return basePlacementDetailDao.getByBuildAndMap(build_code, map_code);
    }

    public int delete(int id) {
        return basePlacementDetailDao.delete(id);
    }

    public List<BaseHouse> houseList(int prjBaseInfoId) {
        return basePlacementDetailDao.houseList(prjBaseInfoId);
    }

    //批量删除
    public int delete(List<Integer> ids) {
        return basePlacementDetailDao.delete(ids);
    }

    /**
     * 批量导入学员信息
     * 如果校验错误，返回错误信息，如果正确，返回空值
     *
     * @param file_path excel地址
     * @return
     */
    public String saveImport(String file_path, int prj_base_info_id) {
        // 1读取excel数据
        List<String[]> list = new Excel2007Util().readExcelContent(file_path, 2);
        // 2校验数据
        String checkResult = checkData(list,prj_base_info_id);
        if (checkResult.length() != 0)
            return checkResult;
        // 3导入数据
        List<BasePlacementDetail> listTrans = transData(list,prj_base_info_id);

        List<BasePlacementDetail> saveList = new ArrayList<>();
        for (BasePlacementDetail baseContacts : listTrans) {
            baseContacts.setPrj_base_info_id(prj_base_info_id);//项目
            saveList.add(baseContacts);
        }
        basePlacementDetailDao.batchAdd(saveList);
        return "";
    }

    /**
     * 校验数据
     *
     * @param list
     * @return
     */
    private String checkData(List<String[]> list, int prj_base_info_id) {
        StringBuffer sb = new StringBuffer();
        int i = 2;
        for (String[] str : list) {
            int j = 0;
            if (str.length != 11) {
                sb.append("第" + i + "行数据不全" + "\\\n");
                j++;
            }
            if (StringUtil.isNullOrEmpty(str[1])) {
                sb.append("第" + i + "行楼号为空-" + str[1] + "\\\n");
                j++;
            }
            if (str[1].length() > 50) {
                sb.append("第" + i + "行楼号超长-" + str[1] + "\\\n");
                j++;
            }
            if (StringUtil.isNullOrEmpty(str[2])) {
                sb.append("第" + i + "行楼层为空-" + str[2] + "\\\n");
                j++;
            }
            if (StringUtil.isNullOrEmpty(str[3])) {
                sb.append("第" + i + "行门牌号为空-" + str[3] + "\\\n");
                j++;
            }
            if (str[3].length() > 50) {
                sb.append("第" + i + "行门牌号超长-" + str[3] + "\\\n");
                j++;
            }
            if (StringUtil.isNullOrEmpty(str[4])) {
                sb.append("第" + i + "行户型为空-" + str[4] + "\\\n");
                j++;
            }
            if(!basePlacementDetailDao.existsHouse(str[4],prj_base_info_id)){
                sb.append("第" + i + "行户型不存在-" + str[4] + "\\\n");
                j++;
            }

            if (StringUtil.isNullOrEmpty(str[5])) {
                sb.append("第" + i + "行面积为空-" + str[5] + "\\\n");
                j++;
            }

            if (StringUtil.isNullOrEmpty(str[6])) {
                sb.append("第" + i + "行是否通透为空-" + str[6] + "\\\n");
                j++;
            }

            if (StringUtil.isNullOrEmpty(str[7])) {
                sb.append("第" + i + "行是否明厨为空-" + str[7] + "\\\n");
                j++;
            }

            if (StringUtil.isNullOrEmpty(str[8])) {
                sb.append("第" + i + "行是否明卫为空-" + str[8] + "\\\n");
                j++;
            }
            if (StringUtil.isNullOrEmpty(str[9])) {
                sb.append("第" + i + "行购房金额为空-" + str[9] + "\\\n");
                j++;
            }
            if (j > 0) {
                sb.append("<br/>");
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * 转换数据
     *
     * @param list
     * @return
     */
    private List<BasePlacementDetail> transData(List<String[]> list, int prj_base_info_id) {
        List<BasePlacementDetail> listTrans = new ArrayList<>();
        for (String[] str : list) {
//			姓名	手机	职务	职责
            BasePlacementDetail basePlacementDetail = new BasePlacementDetail();
            basePlacementDetail.setBuild_code(str[1]);// 楼号
            basePlacementDetail.setFloor(Integer.valueOf(str[2]));// 楼号
            basePlacementDetail.setMap_code(str[3]);// 门牌号
            basePlacementDetail.setHouse_type(basePlacementDetailDao.getHouseId(str[4],prj_base_info_id));// 户型
            basePlacementDetail.setArea(Float.valueOf(str[5]));// 面积
            basePlacementDetail.setBool_tt(getBoolToInt(str[6]));  //是否通透
            basePlacementDetail.setBool_mc(getBoolToInt(str[7]));  //是否明厨
            basePlacementDetail.setBool_mw(getBoolToInt(str[8]));  //是否明卫
            basePlacementDetail.setMoney(new BigDecimal(str[9]));  //购房金额
            basePlacementDetail.setNote(str[10]);  //备注说明

            listTrans.add(basePlacementDetail);
        }
        return listTrans;
    }

    private int getBoolToInt(String bool) {
        if (StringUtil.isNullOrEmpty(bool)) {
            return 0;
        }
        return bool.equals("是") ? 1 : 0;
    }


    public BasePlacementSelect getSelectList(int projectId){
        BasePlacementSelect placementSelect = new BasePlacementSelect();

        int total = basePlacementDetailDao.countAll(projectId);
        int selected = basePlacementDetailDao.countSelect(projectId);

        List<String> buildList= basePlacementDetailDao.getBuildCode(projectId);
        List<BasePlacementFloor> floorList= basePlacementDetailDao.getFloor(projectId);
        List<BasePlacementDetail> selectList = basePlacementDetailDao.getAll(projectId);

        placementSelect.setTotal(total);
        placementSelect.setNoSelect(selected);
        placementSelect.setBuildCodeList(buildList);
        placementSelect.setSelectList(selectList);
        placementSelect.setFloorList(floorList);
        return  placementSelect;
    }
}
