package com.balance.prj.controller;

import com.balance.base.model.BaseCompany;
import com.balance.base.service.BaseCompanyService;
import com.balance.common.vo.ComboboxVO;
import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.model.PrjSection;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.service.PrjSectionService;
import com.balance.prj.vo.PreallocationImportVO;
import com.balance.prj.vo.PrjPreallocationSearchVO;
import com.balance.util.backurl.BackUrlUtil;
import com.balance.util.controller.BaseController;
import com.balance.util.date.DateUtil;
import com.balance.util.excel.Excel2007Util;
import com.balance.util.json.JsonUtil;
import com.balance.util.session.SessionUtil;
import com.balance.util.string.StringUtil;
import com.balance.util.web.WebUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户补偿信息 Controller
 */
@Controller
@RequestMapping("/prj/preallocation/compensation")
public class PrjPreallocationCompenController extends BaseController {
    @Autowired
    private PrjPreallocationService preallocationService;
    @Autowired
    private PrjSectionService prjSectionService;

    @RequestMapping(value = {"", "index"})
    public ModelAndView index(HttpServletRequest request, PrjPreallocationSearchVO preallocationSearchVO) {
        setBtnAutho(request, "PrjPreallocationComp");

        ModelAndView mv = new ModelAndView("prj/preallocationCompenIndex");
        List<PrjSection> sectionList = prjSectionService.listByprj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("sectionList", sectionList);

        List<ComboboxVO> townList = preallocationService.getTown(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("townList",townList);

        mv.addObject("preallocationSearchVO", preallocationSearchVO);

        BackUrlUtil.createBackUrl(mv, request, "/prj/preallocation/compensation/index.htm");
        return mv;
    }

    @RequestMapping(value = "getPreallocation", method = RequestMethod.GET)
    @ResponseBody
    public Map getBasic(PrjPreallocationSearchVO PrjpreallocationSearchVO, HttpServletRequest request) {
        Map<Object, Object> map = new HashMap<>();

        int count = preallocationService.count(PrjpreallocationSearchVO);

        PrjpreallocationSearchVO.setBase_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        PrjpreallocationSearchVO.setLand_status(SessionUtil.getUserSession(request).getCurrent_land_status());
        List<PrjPreallocation> preallocations = preallocationService.findAll(PrjpreallocationSearchVO);

        map.put("data", preallocations);
        map.put("recordsTotal", count);   //必须有  否则报错
        map.put("recordsFiltered", count);  //必须有 否则报错
        map.put("searchDTO", PrjpreallocationSearchVO);
        return map;
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping("/import")
    public void importExcel(MultipartFile excel_file, HttpServletResponse response, HttpServletRequest request) {
        if (excel_file == null || excel_file.isEmpty()) {
            WebUtil.out(response, JsonUtil.createOperaStr(false, "文件上传失败"));
        }
        try {
            String fileName = excel_file.getOriginalFilename();
            String fileExt = FilenameUtils.getExtension(fileName);
            List<PreallocationImportVO> noPassPreallocations = new ArrayList<>();
            List<PrjPreallocation> preallocations;
            switch (fileExt) {
                case "xls": {
                    HSSFWorkbook preallocationWb = new HSSFWorkbook(excel_file.getInputStream());
                    preallocations = convertToPreallocations(preallocationWb, noPassPreallocations, request);
                    break;
                }
                case "xlsx": {
                    XSSFWorkbook subjectWb = new XSSFWorkbook(excel_file.getInputStream());
                    preallocations = convertToPreallocations(subjectWb, noPassPreallocations, request);
                    break;
                }
                default: {
                    WebUtil.out(response, JsonUtil.createOperaStr(false, "文件类型有误"));
                    return;
                }
            }
            if (preallocations != null && preallocations.size() > 0) {
                noPassPreallocations.addAll(saveForImport(preallocations));
                WebUtil.out(response, JsonUtil.createOperaStr(true, "导入成功"));
                return;
            }
            WebUtil.out(response, JsonUtil.createOperaStr(false, "文件上传失败"));
            return;
        } catch (NullPointerException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        WebUtil.out(response, JsonUtil.createOperaStr(false, "数据处理失败"));
    }

    private String getUserName(HttpServletRequest request) {
        return SessionUtil.getUserSession(request).getUser_name();
    }

    private int getProjectId(HttpServletRequest request) {
        return SessionUtil.getUserSession(request).getCurrent_project_id();
    }

    private List<PrjPreallocation> convertToPreallocations(HSSFWorkbook hssfWorkbook,
                                                           List<PreallocationImportVO> noPassPreallocations, HttpServletRequest request) {
        if (hssfWorkbook == null || hssfWorkbook.getNumberOfSheets() == 0) return null;
        List<PrjPreallocation> preallocations = new ArrayList<>();
        HSSFSheet firstSheet = hssfWorkbook.getSheetAt(0);
        if (firstSheet == null || firstSheet.getLastRowNum() < 1) {
            return null;
        }
        int lastRowNum = firstSheet.getLastRowNum();
        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
            HSSFRow hssfRow = firstSheet.getRow(rowIndex);
            if (hssfRow == null) continue;

            String map_id = Excel2007Util.getHssfCellStringValue(hssfRow, 0);
            String host_name = Excel2007Util.getHssfCellStringValue(hssfRow, 1);
            String location = Excel2007Util.getHssfCellStringValue(hssfRow, 2);
            String money_homestead = Excel2007Util.getHssfCellStringValue(hssfRow, 3);
            String money_adjunct = Excel2007Util.getHssfCellStringValue(hssfRow, 4);
            String money_machine = Excel2007Util.getHssfCellStringValue(hssfRow, 5);
            String appraise_compensation = Excel2007Util.getHssfCellStringValue(hssfRow, 6);
            String money_ssbcf = Excel2007Util.getHssfCellStringValue(hssfRow, 7);
            String project_cooperate_award = Excel2007Util.getHssfCellStringValue(hssfRow, 8);
            String money_relocate = Excel2007Util.getHssfCellStringValue(hssfRow, 9);
            String money_dhyjf = Excel2007Util.getHssfCellStringValue(hssfRow, 10);
            String money_ktyjf = Excel2007Util.getHssfCellStringValue(hssfRow, 11);
            String money_kd = Excel2007Util.getHssfCellStringValue(hssfRow, 12);
            String money_yxdsyjf = Excel2007Util.getHssfCellStringValue(hssfRow, 13);
            String money_rsqyjf = Excel2007Util.getHssfCellStringValue(hssfRow, 14);
            String total_yjf = Excel2007Util.getHssfCellStringValue(hssfRow, 15);
            String money_qt = Excel2007Util.getHssfCellStringValue(hssfRow, 16);
            String subsidy_relocate = Excel2007Util.getHssfCellStringValue(hssfRow, 17);
            String total_compensation = Excel2007Util.getHssfCellStringValue(hssfRow, 18);
            String status = Excel2007Util.getHssfCellStringValue(hssfRow, 19);
            String signed_date = Excel2007Util.getHssfCellStringValue(hssfRow, 20);
            String handover_house_date = Excel2007Util.getHssfCellStringValue(hssfRow, 21);
            String demolished_date = Excel2007Util.getHssfCellStringValue(hssfRow, 22);
            String audit_date = Excel2007Util.getHssfCellStringValue(hssfRow, 23);
            String money_date = Excel2007Util.getHssfCellStringValue(hssfRow, 24);
            String no_sign_reason = Excel2007Util.getHssfCellStringValue(hssfRow, 25);

            //noinspection Duplicates
            if (!checkImportParamsNull(map_id, host_name)) {
                PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
                preallocationsImportDTO.setRowIndex(rowIndex);
                preallocationsImportDTO.setReason("导入失败，编号与姓名有空单元格");
                noPassPreallocations.add(preallocationsImportDTO);
            } else {
                PrjPreallocation preallocation = getImport(map_id, host_name, location, money_homestead, money_adjunct,
                        money_machine, appraise_compensation, money_ssbcf, project_cooperate_award, money_relocate,
                        money_dhyjf, money_ktyjf, money_kd, money_yxdsyjf,money_rsqyjf, total_yjf, money_qt,
                        subsidy_relocate, total_compensation, signed_date, handover_house_date, demolished_date,
                        audit_date, money_date, no_sign_reason,status,getUserName(request), rowIndex, getProjectId(request));

                preallocations.add(preallocation);
            }
        }
        return preallocations;
    }

    private List<PrjPreallocation> convertToPreallocations(XSSFWorkbook xssfWorkbook,
                                                           List<PreallocationImportVO> noPassPreallocations, HttpServletRequest request) {
        if (xssfWorkbook == null || xssfWorkbook.getNumberOfSheets() == 0) return null;
        XSSFSheet firstSheet = xssfWorkbook.getSheetAt(0);
        if (firstSheet == null || firstSheet.getLastRowNum() < 1) return null;

        List<PrjPreallocation> preallocations = new ArrayList<>();
        int lastRowNum = firstSheet.getLastRowNum();
        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
            XSSFRow xssfRow = firstSheet.getRow(rowIndex);
            if (xssfRow == null) continue;

            String map_id = Excel2007Util.getXssfCellStringValue(xssfRow, 0);
            String host_name = Excel2007Util.getXssfCellStringValue(xssfRow, 1);
            String location = Excel2007Util.getXssfCellStringValue(xssfRow, 2);
            String money_homestead = Excel2007Util.getXssfCellStringValue(xssfRow, 3);
            String money_adjunct = Excel2007Util.getXssfCellStringValue(xssfRow, 4);
            String money_machine = Excel2007Util.getXssfCellStringValue(xssfRow, 5);
            String appraise_compensation = Excel2007Util.getXssfCellStringValue(xssfRow, 6);
            String money_ssbcf = Excel2007Util.getXssfCellStringValue(xssfRow, 7);
            String project_cooperate_award = Excel2007Util.getXssfCellStringValue(xssfRow, 8);
            String money_relocate = Excel2007Util.getXssfCellStringValue(xssfRow, 9);
            String money_dhyjf = Excel2007Util.getXssfCellStringValue(xssfRow, 10);
            String money_ktyjf = Excel2007Util.getXssfCellStringValue(xssfRow, 11);
            String money_kd = Excel2007Util.getXssfCellStringValue(xssfRow, 12);
            String money_yxdsyjf = Excel2007Util.getXssfCellStringValue(xssfRow, 13);
            String money_rsqyjf = Excel2007Util.getXssfCellStringValue(xssfRow, 14);
            String total_yjf = Excel2007Util.getXssfCellStringValue(xssfRow, 15);
            String money_qt = Excel2007Util.getXssfCellStringValue(xssfRow, 16);
            String subsidy_relocate = Excel2007Util.getXssfCellStringValue(xssfRow, 17);
            String total_compensation = Excel2007Util.getXssfCellStringValue(xssfRow, 18);
            String status = Excel2007Util.getXssfCellStringValue(xssfRow, 19);
            String signed_date = Excel2007Util.getXssfCellStringValue(xssfRow, 20);
            String handover_house_date = Excel2007Util.getXssfCellStringValue(xssfRow, 21);
            String demolished_date = Excel2007Util.getXssfCellStringValue(xssfRow, 22);
            String audit_date = Excel2007Util.getXssfCellStringValue(xssfRow, 23);
            String money_date = Excel2007Util.getXssfCellStringValue(xssfRow, 24);
            String no_sign_reason = Excel2007Util.getXssfCellStringValue(xssfRow, 25);

            //noinspection Duplicates
            if (!checkImportParamsNull(map_id, host_name)) {
                PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
                preallocationsImportDTO.setRowIndex(rowIndex);
                preallocationsImportDTO.setReason("导入失败，编号与姓名有空单元格");
                noPassPreallocations.add(preallocationsImportDTO);
            } else {
                PrjPreallocation preallocation = getImport(map_id, host_name, location, money_homestead, money_adjunct,
                        money_machine, appraise_compensation, money_ssbcf, project_cooperate_award, money_relocate,
                        money_dhyjf, money_ktyjf, money_kd, money_yxdsyjf,money_rsqyjf, total_yjf, money_qt,
                        subsidy_relocate, total_compensation, signed_date, handover_house_date, demolished_date,
                        audit_date, money_date, no_sign_reason,status,getUserName(request), rowIndex, getProjectId(request));
                preallocations.add(preallocation);
            }
        }

        return preallocations;
    }

    private boolean checkImportParamsNull(String mapId, String hostName) {
        return StringUtil.isNotNullOrEmpty(mapId) && StringUtil.isNotNullOrEmpty(hostName);
    }

    private PrjPreallocation getImport(String map_id, String host_name, String location, String money_homestead, String money_adjunct,
                                       String money_machine, String appraise_compensation, String money_ssbcf, String project_cooperate_award, String money_relocate,
                                       String money_dhyjf, String money_ktyjf, String money_kd, String money_yxdsyjf,String money_rsqyjf, String total_yjf, String money_qt,
                                       String subsidy_relocate, String total_compensation, String signed_date, String handover_house_date, String demolished_date,
                                       String audit_date, String money_date, String no_sign_reason,String status, String create_person_name,
                                       int row_index, int projectId) {

        PrjPreallocation preallocation = new PrjPreallocation();
        preallocation.setPrj_base_info_id(projectId);

        int save_status = 0;
        switch (status){
            case "未签约":
                save_status = 10;
                break;
            case "已签约":
                save_status = 20;
                break;
            case "已审核":
                save_status = 30;
                break;
            case "已交房":
                save_status = 40;
                break;
            case "已拆除":
                save_status = 50;
                break;
            case "已放款":
                save_status = 60;
                break;
            case "已归档":
                save_status = 70;
                break;
            default:
                break;
        }
        preallocation.setStatus(save_status);

        preallocation.setMap_id(map_id);
        preallocation.setHost_name(host_name);
        preallocation.setLocation(location);
        preallocation.setMoney_homestead(StringUtil.isNullOrEmpty(money_homestead) ? new BigDecimal(0) : new BigDecimal(money_homestead));
        preallocation.setMoney_adjunct(StringUtil.isNullOrEmpty(money_adjunct) ? new BigDecimal(0) : new BigDecimal(money_adjunct));
        preallocation.setMoney_machine(StringUtil.isNullOrEmpty(money_machine) ? new BigDecimal(0) : new BigDecimal(money_machine));
        preallocation.setAppraise_compensation(StringUtil.isNullOrEmpty(appraise_compensation) ? new BigDecimal(0) : new BigDecimal(appraise_compensation));
        preallocation.setMoney_ssbcf(StringUtil.isNullOrEmpty(money_ssbcf) ? new BigDecimal(0) : new BigDecimal(money_ssbcf));
        preallocation.setProject_cooperate_award(StringUtil.isNullOrEmpty(project_cooperate_award) ? new BigDecimal(0) : new BigDecimal(project_cooperate_award));
        preallocation.setMoney_relocate(StringUtil.isNullOrEmpty(money_relocate) ? new BigDecimal(0) : new BigDecimal(money_relocate));
        preallocation.setMoney_dhyjf(StringUtil.isNullOrEmpty(money_dhyjf) ? new BigDecimal(0) : new BigDecimal(money_dhyjf));
        preallocation.setMoney_ktyjf(StringUtil.isNullOrEmpty(money_ktyjf) ? new BigDecimal(0) : new BigDecimal(money_ktyjf));
        preallocation.setMoney_kd(StringUtil.isNullOrEmpty(money_kd) ? new BigDecimal(0) : new BigDecimal(money_kd));
        preallocation.setMoney_yxdsyjf(StringUtil.isNullOrEmpty(money_yxdsyjf) ? new BigDecimal(0) : new BigDecimal(money_yxdsyjf));
        preallocation.setMoney_rsqyjf(StringUtil.isNullOrEmpty(money_rsqyjf) ? new BigDecimal(0) : new BigDecimal(money_rsqyjf));

        preallocation.setMoney_qt(StringUtil.isNullOrEmpty(money_qt) ? new BigDecimal(0) : new BigDecimal(money_qt));
        preallocation.setSubsidy_relocate(StringUtil.isNullOrEmpty(subsidy_relocate) ? new BigDecimal(0) : new BigDecimal(subsidy_relocate));
        preallocation.setTotal_compensation(StringUtil.isNullOrEmpty(total_compensation) ? new BigDecimal(0) : new BigDecimal(total_compensation));

        preallocation.setRelocate_date(DateUtil.stringToDate(signed_date, "yyyy-MM-dd"));
        preallocation.setHandover_house_date(DateUtil.stringToDate(handover_house_date, "yyyy-MM-dd"));
        preallocation.setDemolished_date(DateUtil.stringToDate(demolished_date, "yyyy-MM-dd"));
        preallocation.setRelocate_date(DateUtil.stringToDate(audit_date, "yyyy-MM-dd"));
        preallocation.setRelocate_date(DateUtil.stringToDate(money_date, "yyyy-MM-dd"));


        preallocation.setRemarks(no_sign_reason);
        preallocation.setCreated_by(create_person_name);
        preallocation.setRowIndex(row_index);
        return preallocation;
    }

    /**
     * 校验数据
     *
     * @param preallocationList 导入数据List
     * @return 带有错误原因DTO的list
     */
    @SuppressWarnings("Duplicates")
    private List<PreallocationImportVO> checkForImport(List<PrjPreallocation> preallocationList, HttpServletRequest request) {
        List<PreallocationImportVO> errorVOList = new ArrayList<>();
        for (PrjPreallocation preallocation : preallocationList) {
            PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
            int project_id = getProjectId(request);
            if (preallocationService.existByMapId(preallocation.getMap_id(), project_id)) {
                preallocationsImportDTO.setReason("ID号重复!");
            }
            if (StringUtil.isNotNullOrEmpty(preallocationsImportDTO.getReason())) {
                preallocationsImportDTO.setRowIndex(preallocation.getRowIndex());
                errorVOList.add(preallocationsImportDTO);
            }
        }
        return errorVOList;
    }

    /**
     * 导入数据
     *
     * @param preallocationList 导入数据List
     * @return 带有错误原因DTO的list
     */
    @SuppressWarnings("Duplicates")
    private List<PreallocationImportVO> saveForImport(List<PrjPreallocation> preallocationList) {
        List<PreallocationImportVO> errorVOList = new ArrayList<>();
        if (preallocationList.size() > 100) {
            int max_times = preallocationList.size() / 100 + 1;
            for (int t = 0; t < max_times; t++) {
                int max_number = (t + 1) * 100 > preallocationList.size() ? preallocationList.size() : (t + 1) * 100;
                for (int i = t * 100; i < max_number; i++) {
                    PreallocationImportVO preallocationsImportDTO = preallocationService.saveForImport(preallocationList.get(i), 2);
                    if (StringUtil.isNotNullOrEmpty(preallocationsImportDTO.getReason())) {
                        preallocationsImportDTO.setRowIndex(preallocationList.get(i).getRowIndex());
                        errorVOList.add(preallocationsImportDTO);
                    }
                }
            }
        } else {
            for (PrjPreallocation preallocation : preallocationList) {
                PreallocationImportVO preallocationsImportDTO = preallocationService.saveForImport(preallocation, 2);
                if (StringUtil.isNotNullOrEmpty(preallocationsImportDTO.getReason())) {
                    preallocationsImportDTO.setRowIndex(preallocation.getRowIndex());
                    errorVOList.add(preallocationsImportDTO);
                }
            }
        }
        return errorVOList;
    }


}
