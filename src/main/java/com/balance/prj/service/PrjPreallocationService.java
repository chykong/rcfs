package com.balance.prj.service;

import com.balance.api.dto.GisDTO;
import com.balance.api.dto.HouseholdersDTO;
import com.balance.api.dto.HouseholdersDetailDTO;
import com.balance.base.dao.BasePlacementDetailDao;
import com.balance.base.model.BasePlacementDetail;
import com.balance.common.vo.ComboboxVO;
import com.balance.prj.dao.*;
import com.balance.prj.model.*;
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
    private PrjSelectDao selectDao;
    @Autowired
    private PrjPreallocationRelaDao relaDao;
    @Autowired
    private PrjPreallAttachDao prjPreallAttachDao;
    @Autowired
    private BasePlacementDetailDao placementDetailDao;

    public List<PrjPreallocation> findAll(PrjPreallocationSearchVO prjPreallocationSearchVO) {
        if(prjPreallocationSearchVO.getChooseType() != null){
            return prjPreallocationDao.findAllByChoose(prjPreallocationSearchVO);
        }else{
            return prjPreallocationDao.findAll(prjPreallocationSearchVO);
        }
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


        List<PreallocationRela> relas = getSaveRela(prjPreallocation.getRelas(), prjPreallocation);
        if (relas.size() > 0) {
            relaDao.batchAdd(relas);
        }


        return prjPreallocationDao.add(prjPreallocation);
    }
    private List<PreallocationRela> getSaveRela(List<PreallocationRela> relas, PrjPreallocation prjPreallocation) {
        List<PreallocationRela> saveRela = new ArrayList<>();
        if (relas != null && relas.size() > 0) {
            for (PreallocationRela rela : relas) {
                if (StringUtil.isNotNullOrEmpty(rela.getName())
                        && StringUtil.isNotNullOrEmpty(rela.getId_no())) {
                    rela.setMap_id(prjPreallocation.getMap_id());
                    rela.setPrj_base_info_id(prjPreallocation.getPrj_base_info_id());
                    rela.setCreated_by(prjPreallocation.getCreated_by());
                    saveRela.add(rela);
                }
            }
        }

        return saveRela;
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

        List<PreallocationRela> relas = relaDao.list(prjPreallocation.getMap_id(), prjPreallocation.getPrj_base_info_id());

        prjPreallocation.setRelas(relas);
        prjPreallocation.setRelas_num(relas.size());
        String rela_detail = "";
        for (PreallocationRela preallocationRela : relas) {
            rela_detail += preallocationRela.getName() + ",";
        }
        prjPreallocation.setRelas_detail(rela_detail);

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

    public String[][] export(PrjPreallocationSearchVO preallocationSearchVO) {
        List<PrjPreallocation> list = prjPreallocationDao.findAllForExport(preallocationSearchVO);
        String[][] data = new String[list.size()][81];
        int i = 0;
        for (PrjPreallocation preallocation : list) {
            data[i][0] = String.valueOf(i + 1);// 序号
            data[i][1] = preallocation.getMap_id();  //编号
            data[i][2] = preallocation.getHost_name(); //产权人
            data[i][3] = preallocation.getLocation();  //房屋坐落
            data[i][4] = preallocation.getId_card();  //身份证号
            data[i][5] = preallocation.getLessee_name();  //承租人
            data[i][6] = preallocation.getLegal_name(); //营业执照法人
            data[i][7] = preallocation.getTotal_land_area() == null ? "0" : preallocation.getTotal_land_area().toString();  //土地总面积
            data[i][8] = preallocation.getCard_land_area() == null ? "0" : preallocation.getCard_land_area().toString(); // 证载土地面积
            data[i][9] = preallocation.getCog_land_area() == null ? "0" : preallocation.getCog_land_area().toString();  //实际用地面积
            data[i][10] = preallocation.getLessee_land_area() == null ? "0" : preallocation.getLessee_land_area().toString();  //土地租赁面积
            data[i][11] = preallocation.getTotal_homestead_area() == null ? "0" : preallocation.getTotal_homestead_area().toString();   //总建筑面积
            data[i][12] = preallocation.getCard_homestead_area() == null ? "0" : preallocation.getCard_homestead_area().toString();  //证载房屋建筑面积
            data[i][13] = preallocation.getNo_card_homestead_area() == null ? "0" : preallocation.getNo_card_homestead_area().toString();  //无证房屋建筑面积
            data[i][14] = preallocation.getManagement_homestead_area() == null ? "0" : preallocation.getManagement_homestead_area().toString();  //经营面积
            data[i][15] = preallocation.getTown(); //镇
            data[i][16] = preallocation.getVillage();  //村
            data[i][17] = preallocation.getSection();  //标段
            data[i][18] = preallocation.getGroups();  //组别
            data[i][19] = preallocation.getBefore_area() == null ? "0" : preallocation.getBefore_area().toString();  //06年前面积
            data[i][20] = preallocation.getBetween_area() == null ? "0" : preallocation.getBetween_area().toString();  //06-09
            data[i][21] = preallocation.getAfter_area() == null ? "0" : preallocation.getAfter_area().toString();  //09后
            int j = 0;
            if (preallocationSearchVO.getBase_info_id() == 28) {
                data[i][22] = preallocation.getLast_area() == null ? "0" : preallocation.getLast_area().toString();  //09后
                j = 1;
            }

            data[i][22 + j] = preallocation.getManagement_house_area() == null ? "0" : preallocation.getManagement_house_area().toString();  //房屋营业面积
            data[i][23 + j] = preallocation.getField_house_area() == null ? "0" : preallocation.getField_house_area().toString();  //场地营业面积
            data[i][24 + j] = preallocation.getMoney_homestead() == null ? "0" : preallocation.getMoney_homestead().toString();  //房屋补偿款
            data[i][25 + j] = preallocation.getMoney_adjunct() == null ? "0" : preallocation.getMoney_adjunct().toString();  //附属物补偿款
            data[i][26 + j] = preallocation.getMoney_machine() == null ? "0" : preallocation.getMoney_machine().toString();  //机器设备补偿款
            data[i][27 + j] = preallocation.getAppraise_compensation() == null ? "0" : preallocation.getAppraise_compensation().toString();  //评估补偿款
            data[i][28 + j] = preallocation.getMoney_ssbcf() == null ? "0" : preallocation.getMoney_ssbcf().toString();  //停产停业补助费
            data[i][29 + j] = preallocation.getProject_cooperate_award() == null ? "0" : preallocation.getProject_cooperate_award().toString();  //工程配合奖
            data[i][30 + j] = preallocation.getMoney_relocate() == null ? "0" : preallocation.getMoney_relocate().toString();  //搬家补助费
            data[i][31 + j] = preallocation.getMoney_dhyjf() == null ? "0" : preallocation.getMoney_dhyjf().toString();  //电话
            data[i][32 + j] = preallocation.getMoney_ktyjf() == null ? "0" : preallocation.getMoney_ktyjf().toString();  //空调
            data[i][33 + j] = preallocation.getMoney_kd() == null ? "0" : preallocation.getMoney_kd().toString();  //宽带
            data[i][34 + j] = preallocation.getMoney_yxdsyjf() == null ? "0" : preallocation.getMoney_yxdsyjf().toString();  //有线电视
            data[i][35 + j] = preallocation.getMoney_rsqyjf() == null ? "0" : preallocation.getMoney_rsqyjf().toString();  //热水器
            data[i][36 + j] = preallocation.getTotal_yjf() == null ? "0" : preallocation.getTotal_yjf().toString();  //移机费
            data[i][37 + j] = preallocation.getMoney_qt() == null ? "0" : preallocation.getMoney_qt().toString();  //其他
            data[i][38 + j] = preallocation.getSubsidy_relocate() == null ? "0" : preallocation.getSubsidy_relocate().toString();  //补助奖励费
            data[i][39 + j] = preallocation.getTotal_compensation() == null ? "0" : preallocation.getTotal_compensation().toString();  //总补偿款
            data[i][40 + j] = preallocation.getAppraise_money() == null ? "0" : preallocation.getAppraise_money().toString();  //评估价格
            data[i][41 + j] = preallocation.getIn_host_date() == null ? "" : preallocation.getIn_host_date();  //入户日期
            data[i][42 + j] = preallocation.getSigned_date() == null ? "" : preallocation.getSigned_date();  //签约日期
            data[i][43 + j] = preallocation.getHandover_house_date() == null ? "" : preallocation.getHandover_house_date();  //交房日期
            data[i][44 + j] = preallocation.getDemolished_date() == null ? "" : preallocation.getDemolished_date();  //拆除日期
            data[i][45 + j] = preallocation.getAudit_date() == null ? "" : preallocation.getAudit_date();  //审核日期
            data[i][46 + j] = preallocation.getMoney_date() == null ? "" : preallocation.getMoney_date();  //放款日期
            data[i][47 + j] = preallocation.getStatusString();  //状态
            data[i][48 + j] = preallocation.getNo_sign_reason();  //滞留原因
            data[i][49 + j] = preallocation.getLeader();  //包村干部
            data[i][50 + j] = preallocation.getManagement_co();  //管理公司
            data[i][51 + j] = preallocation.getGeo_co();  //测绘公司
            data[i][52 + j] = preallocation.getAppraise_co();  //测绘公司
            data[i][53 + j] = preallocation.getDemolish_co();  //拆迁公司
            data[i][54 + j] = preallocation.getAudit_co();  //审计公司
            data[i][55 + j] = preallocation.getPulledown_co();  //拆除公司
            data[i][56 + j] = preallocation.getDemolition_year_code();     //拆迁许可证年号
            data[i][57 + j] = preallocation.getDemolition_card_code();  //拆迁许可证证号
            data[i][58 + j] = preallocation.getRelocate_date();  //搬迁日期
            data[i][59 + j] = preallocation.getFloat_people() == null ? "0" : preallocation.getFloat_people().toString();  //流动人口
            data[i][60 + j] = preallocation.getCar_num() == null ? "0" : preallocation.getCar_num().toString();  //车辆数量
            data[i][61 + j] = preallocation.getRmgl_num() == null ? "0" : preallocation.getRmgl_num().toString();  //燃煤锅炉
            data[i][62 + j] = preallocation.getRqgl_num() == null ? "0" : preallocation.getRqgl_num().toString();  //燃气锅炉
            data[i][63 + j] = preallocation.getZqgl_num() == null ? "0" : preallocation.getZqgl_num().toString();  //蒸汽锅炉
            data[i][64 + j] = preallocation.getScattered_coal() == null ? "0" : preallocation.getScattered_coal().toString();  //散煤
            data[i][65 + j] = preallocation.getJzzz_num() == null ? "0" : preallocation.getJzzz_num().toString();  //家住制造
            data[i][66 + j] = preallocation.getScqg_num() == null ? "0" : preallocation.getScqg_num().toString();  //石材切割
            data[i][67 + j] = preallocation.getQx_num() == null ? "0" : preallocation.getQx_num().toString();  //汽修
            data[i][68 + j] = preallocation.getWl_num() == null ? "0" : preallocation.getWl_num().toString();  //物流
            data[i][69 + j] = preallocation.getFz_num() == null ? "0" : preallocation.getFz_num().toString();  //服装
            data[i][70 + j] = preallocation.getQt_num() == null ? "0" : preallocation.getQt_num().toString();  //其他
            data[i][71 + j] = preallocation.getArchive_date();  //归档时间
            data[i][72 + j] = preallocation.getArchives_cabinet_number();  //档案柜号
            i++;
        }
        return data;
    }


    /**
     * 获取并更新最大选房顺序号
     *
     * @param mapId       编号
     * @param projectId   项目id
     * @param houseType   房屋类型
     * @param landType    土地类型
     * @param select_type 选择方式
     * @return 最大顺序号
     */
    public int updateMaxCode(String mapId, int projectId, String houseType, String landType, int select_type) {
        PrjSelected prjSelected = selectDao.getByMapId(mapId, projectId, houseType, landType);
        if (prjSelected != null) {
            prjSelected.setCompensation_type(select_type);
            selectDao.updateType(prjSelected);
            return prjSelected.getSelected_code();
        }
        int max = selectDao.getMaxCode(projectId, houseType, landType);

        prjSelected = new PrjSelected();
        prjSelected.setLand_type(landType);
        prjSelected.setHouse_type(houseType);
        prjSelected.setProject_id(projectId);
        prjSelected.setMap_id(mapId);
        prjSelected.setSelected_code(max + 1);
        prjSelected.setCompensation_type(select_type);
        selectDao.add(prjSelected);
        return max + 1;
    }

    public PrjSelected getSelect(PrjPreallocation preallocation) {

        String mapId = preallocation.getMap_id();
        int projectId = preallocation.getPrj_base_info_id() == null ? 0 : preallocation.getPrj_base_info_id();
        String houseType = preallocation.getHouse_property();
        String landType = preallocation.getLand_property();
        return selectDao.getByMapId(mapId, projectId, houseType, landType);
    }

    public PrjPreallocation getBySelect(String selectCode) {
        PrjPreallocation preallocation = prjPreallocationDao.getBySelect(selectCode);
        if (preallocation != null) {
            int realNum = relaDao.count(preallocation.getMap_id(), preallocation.getPrj_base_info_id());
            preallocation.setRelas_num(realNum);
        }
        return preallocation;
    }
    public int saveSelectHouse(String ids, String map_id, int projectId) {
        int flag = 0;

        for (String saveId : ids.split(",")) {
            int id = Integer.valueOf(saveId);
            BasePlacementDetail basePlacementDetail = placementDetailDao.get(id);
            if (basePlacementDetail != null) {
                flag = placementDetailDao.updateSelect(map_id, id);
                if (flag == 1) {
                    flag = prjPreallocationDao.updateSelect(id, map_id, projectId);
                }
            }
        }

        return flag;
    }

}
