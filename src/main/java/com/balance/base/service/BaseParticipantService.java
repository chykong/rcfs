package com.balance.base.service;

import com.balance.api.dto.ContactsDTO;
import com.balance.api.dto.ContactsGroupsDTO;
import com.balance.base.dao.BaseParticipantDao;
import com.balance.base.model.BaseParticipant;
import com.balance.base.vo.BaseParticipantSearchVO;
import com.balance.util.date.DateUtil;
import com.balance.util.excel.Excel2007Util;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author 孔垂云 Date 2017/6/3.
 */
@Service
public class BaseParticipantService {

    @Autowired
    private BaseParticipantDao baseParticipantDao;

    public List<BaseParticipant> list(BaseParticipantSearchVO baseParticipantSearchVO) {
        return baseParticipantDao.list(baseParticipantSearchVO);
    }

    public int listCount(BaseParticipantSearchVO baseParticipantSearchVO) {
        return baseParticipantDao.listCount(baseParticipantSearchVO);
    }

    public int add(BaseParticipant BaseParticipant) {
        return baseParticipantDao.add(BaseParticipant);
    }

    public int update(BaseParticipant BaseParticipant) {
        return baseParticipantDao.update(BaseParticipant);
    }

    public int delete(int id) {
        return baseParticipantDao.delete(id);
    }

    public BaseParticipant get(int id) {
        return baseParticipantDao.get(id);
    }

    public String saveImport(String file_path, String create_person, int prj_base_info_id) {
        // 1读取excel数据
        List<String[]> list = new Excel2007Util().readExcelContent(file_path, 2);
        // 2校验数据
        String checkResult = checkData(list);
        if (checkResult.length() != 0)
            return checkResult;
        // 3导入数据
        List<BaseParticipant> listTrans = transData(list);
        Date date = new Date();
        for (BaseParticipant baseParticipant : listTrans) {
            baseParticipant.setCreated_by(create_person);// 导入人
            baseParticipant.setPrj_base_info_id(prj_base_info_id);// 项目
            baseParticipant.setRelease_date(DateUtil.getSystemDate());//发布时间
            baseParticipantDao.add(baseParticipant);
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
            if (str.length != 7) {
                sb.append("第" + i + "行数据不全" + "\\\n");
            }
            if (str[1].length() > 100) {
                sb.append("第" + i + "行工作职能错误-" + str[1] + "\\\n");
            }
            if (StringUtil.isNullOrEmpty(str[2]) || str[2].length() > 10) {
                sb.append("第" + i + "行姓名错误-" + str[2] + "\\\n");
            }
            if (str[3].length() > 11) {
                sb.append("第" + i + "行手机超长-" + str[3] + "\\\n");
            }
            if (str[4].length() > 100) {
                sb.append("第" + i + "行组别超长-" + str[4] + "\\\n");
            }
            if (str[5].length() > 100) {
                sb.append("第" + i + "行公司名称超长-" + str[5] + "\\\n");
            }
            if (str[6].length() > 100) {
                sb.append("第" + i + "行工作职责超长-" + str[6] + "\\\n");
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
    private List<BaseParticipant> transData(List<String[]> list) {
        List<BaseParticipant> listTrans = new ArrayList<>();
        for (String[] str : list) {
            BaseParticipant baseParticipant = new BaseParticipant();
            baseParticipant.setDuty(str[1]);// 工作职能
            baseParticipant.setName(str[2]);// 姓名
            baseParticipant.setMobile(str[3]);// 手机
            baseParticipant.setGroups(str[4]);// 组别
            baseParticipant.setCompany(str[5]);// 公司名称
            baseParticipant.setProject_duty(str[6]);// 工作职责
            listTrans.add(baseParticipant);
        }
        return listTrans;
    }

    /**
     * 全部公司列表
     *
     * @return 列表
     */
    public List<ContactsGroupsDTO> listByGroup(int prj_base_info_id) {
        return baseParticipantDao.listByGroup(prj_base_info_id);
    }

    /**
     * 根据组名和项目id获取联系人列表
     *
     * @return 列表
     */
    public List<ContactsDTO> listBySection(int prj_base_info_id, String section) {
        return baseParticipantDao.listBySection(prj_base_info_id, section);
    }
}
