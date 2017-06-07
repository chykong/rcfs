package com.balance.base.service;

import com.balance.base.dao.BaseContactsDao;
import com.balance.base.model.BaseContacts;
import com.balance.base.vo.BaseContactsSearchVO;
import com.balance.util.excel.Excel2007Util;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author 孔垂云 Date 2017/6/3.
 */
@Service
public class BaseContactsService {

    @Autowired
    private BaseContactsDao baseContactsDao;

    public List<BaseContacts> list(BaseContactsSearchVO baseContactsSearchVO) {
        return baseContactsDao.list(baseContactsSearchVO);
    }

    public int listCount(BaseContactsSearchVO baseContactsSearchVO) {
        return baseContactsDao.listCount(baseContactsSearchVO);
    }

    public int add(BaseContacts BaseContacts) {
        return baseContactsDao.add(BaseContacts);
    }

    public int update(BaseContacts BaseContacts) {
        return baseContactsDao.update(BaseContacts);
    }

    public int delete(int id) {
        return baseContactsDao.delete(id);
    }

    public BaseContacts get(int id) {
        return baseContactsDao.get(id);
    }

    /**
     * 批量导入学员信息
     * 如果校验错误，返回错误信息，如果正确，返回空值
     *
     * @param file_path excel地址
     * @return
     */
    public String saveImport(String file_path, String create_person, int prj_base_info_id) {
        // 1读取excel数据
        List<String[]> list = new Excel2007Util().readExcelContent(file_path, 2);
        // 2校验数据
        String checkResult = checkData(list);
        if (checkResult.length() != 0)
            return checkResult;
        // 3导入数据
        List<BaseContacts> listTrans = transData(list);
        for (BaseContacts baseContacts : listTrans) {
            baseContacts.setCreated_by(create_person);// 导入人
            baseContacts.setPrj_base_info_id(prj_base_info_id);//项目
            baseContactsDao.add(baseContacts);
        }
        return "";
    }

    /**
     * 校验数据
     *
     * @param list
     * @return
     */
    private String checkData(List<String[]> list) {
        StringBuffer sb = new StringBuffer();
        int i = 2;
        for (String[] str : list) {
            if (str.length != 5) {
                sb.append("第" + i + "行数据不全" + "\\\n");
            }
            if (StringUtil.isNullOrEmpty(str[1]) || str[1].length() > 10) {
                sb.append("第" + i + "行姓名错误-" + str[1] + "\\\n");
            }
            if (str[2].length() != 11) {
                sb.append("第" + i + "行手机号错误-" + str[2] + "\\\n");
            }
            if (str[3].length() > 100) {
                sb.append("第" + i + "行职务超长-" + str[3] + "\\\n");
            }
            if (str[4].length() > 100) {
                sb.append("第" + i + "行职责超长-" + str[4] + "\\\n");
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
    private List<BaseContacts> transData(List<String[]> list) {
        List<BaseContacts> listTrans = new ArrayList<>();
        for (String[] str : list) {
//			姓名	手机	职务	职责
            BaseContacts baseContacts = new BaseContacts();
            baseContacts.setName(str[1]);// 姓名
            baseContacts.setMobile(str[2]);// 手机
            baseContacts.setDuty(str[3]);// 职务
            baseContacts.setProject_duty(str[4]);// 职责
            listTrans.add(baseContacts);
        }
        return listTrans;
    }

}
