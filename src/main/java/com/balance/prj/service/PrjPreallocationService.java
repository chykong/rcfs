package com.balance.prj.service;

import com.balance.api.dto.GisDTO;
import com.balance.api.dto.HouseholdersDTO;
import com.balance.api.dto.HouseholdersDetailDTO;
import com.balance.common.vo.ComboboxVO;
import com.balance.prj.dao.PrjGroupDao;
import com.balance.prj.dao.PrjPreallocationDao;
import com.balance.prj.dao.PrjSectionDao;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.model.PrjSection;
import com.balance.prj.vo.PreallocationImportVO;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public int add(PrjPreallocation prjPreallocation) {
        return prjPreallocationDao.add(prjPreallocation);
    }

    public int update(PrjPreallocation prjPreallocation) {
        return prjPreallocationDao.update(prjPreallocation);
    }

    public PrjPreallocation getById(int id) {
        return prjPreallocationDao.getById(id);
    }

    /**
     * 按照id删除
     *
     * @param id
     */
    public int delete(int id) {
        return prjPreallocationDao.delete(id);
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
}
