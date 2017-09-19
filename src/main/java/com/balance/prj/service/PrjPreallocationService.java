package com.balance.prj.service;

import com.balance.api.dto.GisDTO;
import com.balance.api.dto.HouseholdersDTO;
import com.balance.api.dto.HouseholdersDetailDTO;
import com.balance.common.vo.ComboboxVO;
import com.balance.prj.dao.PrjGroupDao;
import com.balance.prj.dao.PrjPreallAttachDao;
import com.balance.prj.dao.PrjPreallocationDao;
import com.balance.prj.dao.PrjSectionDao;
import com.balance.prj.model.PrjPreallAttach;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.model.PrjSection;
import com.balance.prj.vo.PreallocationImportVO;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户信息service
 */
@Service
public class PrjPreallocationService {
    @Autowired
    private PrjPreallocationDao prjPreallocationDao;
    @Autowired
    private PrjSectionDao prjSectionDao;
    @Autowired
    private PrjGroupDao prjGroupDao;
    @Autowired
    private PrjPreallAttachDao prjPreallAttachDao;

    public List<PrjPreallocation> findAll(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        return prjPreallocationDao.findAll(prjPreallocationSearchVO);
    }

    /**
     * api接口里面需要分页查询
     *
     * @param prjPreallocationSearchVO
     * @return
     */
    public List<HouseholdersDTO> list(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        return prjPreallocationDao.list(prjPreallocationSearchVO);
    }

    public List<GisDTO> listForGis(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        return prjPreallocationDao.listForGis(prjPreallocationSearchVO);
    }

    public int count(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        return prjPreallocationDao.count(prjPreallocationSearchVO);
    }

    private List<PrjPreallAttach> getAttachList(List<PrjPreallAttach> prjAttachments, String user_name, String map_id) {
        List<PrjPreallAttach> attachments = new ArrayList<>();
        for (PrjPreallAttach prjAttachment : prjAttachments) {
            if (StringUtil.isNotNullOrEmpty(prjAttachment.getFile_name()) && StringUtil.isNotNullOrEmpty(prjAttachment.getFile_path())) {
                prjAttachment.setMap_id(map_id);
                prjAttachment.setCreated_by(user_name);
                attachments.add(prjAttachment);
            }
        }
        return attachments;
    }

    public int add(PrjPreallocation prjPreallocation) {
        List<PrjPreallAttach> attachments;
        if (prjPreallocation != null && prjPreallocation.getPreallAttaches() != null && prjPreallocation.getPreallAttaches().size() > 0) {
            attachments = getAttachList(prjPreallocation.getPreallAttaches(), prjPreallocation.getCreated_by(), prjPreallocation.getMap_id());
            prjPreallAttachDao.batchAdd(attachments);
        }
        return prjPreallocationDao.add(prjPreallocation);
    }

    public int update(PrjPreallocation prjPreallocation) {
        List<PrjPreallAttach> attachments;
        prjPreallAttachDao.delete(prjPreallocation.getMap_id());

        if (prjPreallocation.getPreallAttaches() != null && prjPreallocation.getPreallAttaches().size() > 0) {
            attachments = getAttachList(prjPreallocation.getPreallAttaches(), prjPreallocation.getCreated_by(), prjPreallocation.getMap_id());
            prjPreallAttachDao.batchAdd(attachments);
        }
        return prjPreallocationDao.update(prjPreallocation);
    }

    public PrjPreallocation getById(int id) {
        PrjPreallocation prjPreallocation = prjPreallocationDao.getById(id);
        if (prjPreallocation != null) {
            List<PrjPreallAttach> prjAttachments = prjPreallAttachDao.list(prjPreallocation.getMap_id());
            prjPreallocation.setPreallAttaches(prjAttachments);
        }
        return prjPreallocation;
    }

    /**
     * 按照id删除
     *
     * @param id
     */
    public int delete(int id) {
        return prjPreallocationDao.delete(id);
    }

    public int deleteBatch(String map_idString, int prj_base_info_id) {
        List<String> map_ids = new ArrayList<>();
        Collections.addAll(map_ids, map_idString.split(","));
        return prjPreallocationDao.deleteBatch(map_ids, prj_base_info_id);
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

    public PreallocationImportVO saveForImport(PrjPreallocation preallocation, int type) {
        PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
        if (type != 1 && type != 2) {
            preallocationsImportDTO.setReason("传入类型错误");
            return preallocationsImportDTO;
        }
        int project_id = preallocation.getPrj_base_info_id();
        PrjSection prjSection = prjSectionDao.existByPrjIdAndName(project_id, preallocation.getSection());
        if (prjSection == null) {
            preallocationsImportDTO.setReason("该项目下不存在此标段");
            return preallocationsImportDTO;
        }
        if (!prjGroupDao.existByPrjIdAndName(project_id, prjSection.getId(), preallocation.getGroups())) {
            preallocationsImportDTO.setReason("该项目该标段下不存在此组别");
            return preallocationsImportDTO;
        }
        int flag;
        PrjPreallocation preallocation_exist = prjPreallocationDao.getByMapId(preallocation.getMap_id());
        if (preallocation_exist != null) {
            preallocation.setId(preallocation_exist.getId());
            if (type == 1) {
                flag = prjPreallocationDao.updateBasic(preallocation);
            } else {
                preallocation = checkPreallocation(preallocation);
                flag = prjPreallocationDao.updateCompensation(preallocation);
            }
        } else {
            if (type == 1) {
                flag = prjPreallocationDao.add(preallocation);
            } else {
                preallocationsImportDTO.setReason("编号不存在，请先完善基本情况");
                return preallocationsImportDTO;
            }
        }
        if (flag == 0) {
            preallocationsImportDTO.setReason("保存失败");
        }
        return preallocationsImportDTO;
    }

    private PrjPreallocation checkPreallocation(PrjPreallocation preallocation) {
        BigDecimal money_homestead = preallocation.getMoney_homestead();
        BigDecimal money_adjunct = preallocation.getMoney_adjunct();
        BigDecimal money_machine = preallocation.getMoney_machine();

        BigDecimal sum_appraise = money_homestead.add(money_adjunct).add(money_machine);
        if (preallocation.getAppraise_compensation().compareTo(sum_appraise) != 0) {
            preallocation.setAppraise_compensation(sum_appraise);
        }

        BigDecimal money_relocate = preallocation.getMoney_relocate();
        BigDecimal project_cooperate_award = preallocation.getProject_cooperate_award();
        BigDecimal money_ssbcf = preallocation.getMoney_ssbcf();

        BigDecimal money_dhyjf = preallocation.getMoney_dhyjf();
        BigDecimal money_yxdsyjf = preallocation.getMoney_yxdsyjf();
        BigDecimal money_ktyjf = preallocation.getMoney_ktyjf();
        BigDecimal money_rsqyjf = preallocation.getMoney_rsqyjf();
        BigDecimal money_kd = preallocation.getMoney_kd();
        BigDecimal money_qt = preallocation.getMoney_qt();


        BigDecimal sum_subsidy = money_relocate.add(project_cooperate_award).add(money_ssbcf).add(money_dhyjf)
                .add(money_yxdsyjf).add(money_ktyjf).add(money_rsqyjf).add(money_kd).add(money_qt);

        preallocation.setSubsidy_relocate(sum_subsidy);
        preallocation.setTotal_compensation(preallocation.getAppraise_compensation().add(preallocation.getSubsidy_relocate()));

        return preallocation;
    }

    public PrjPreallocation updateInHost(PrjPreallocation prjPreallocation) {
        return prjPreallocationDao.updateInHost(prjPreallocation);
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

    public int updateArchive(String map_id, String host_name, String archives_code, int project_id, int status, String file_path, String file_name) {
        return prjPreallocationDao.updateArchive(map_id, host_name, archives_code, project_id, status, file_path, file_name);
    }

    /**
     * 城镇列表
     *
     * @param prj_base_info_id
     * @return
     */
    public List<ComboboxVO> getTown(int prj_base_info_id) {
        return prjPreallocationDao.getTown(prj_base_info_id);
    }

    public List<ComboboxVO> getVillage(int prj_base_info_id) {
        return prjPreallocationDao.getVillage(prj_base_info_id);
    }

    public List<ComboboxVO> getVillageByTown(int prj_base_info_id, String town) {
        return prjPreallocationDao.getVillageByTown(prj_base_info_id, town);
    }

    /**
     * 接口里面调用，获取拆迁详细
     *
     * @param id
     * @return
     */
    public HouseholdersDetailDTO getByidInApi(int id) {
        return prjPreallocationDao.getByIdInApi(id);
    }

    public List<PrjPreallAttach> listAttach(String map_id) {
        return prjPreallAttachDao.list(map_id);
    }

    public List<PrjPreallAttach> listAttach(String map_id, int type) {
        List<PrjPreallAttach> allAttachList = new ArrayList<>();

        List<PrjPreallAttach> beforeAttachList = prjPreallAttachDao.listForType(map_id, 1);
        List<PrjPreallAttach> betweenAttachList = prjPreallAttachDao.listForType(map_id, 2);
        List<PrjPreallAttach> afterAttachList = prjPreallAttachDao.listForType(map_id, 3);

        allAttachList.addAll(beforeAttachList);
        allAttachList.addAll(betweenAttachList);
        allAttachList.addAll(afterAttachList);
        return allAttachList;
    }
}
