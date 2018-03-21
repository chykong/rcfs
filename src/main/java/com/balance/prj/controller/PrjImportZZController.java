package com.balance.prj.controller;

import com.balance.prj.model.PrjPreallocation;
import com.balance.prj.service.PrjPreallocationService;
import com.balance.prj.vo.PreallocationImportVO;
import com.balance.util.excel.Excel2007Util;
import com.balance.util.json.JsonUtil;
import com.balance.util.number.NumberUtil;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨康康 on 2018/3/21.
 */
@Controller
@RequestMapping("/prj/base")
public class PrjImportZZController {
    @Autowired
    private PrjPreallocationService preallocationService;

    @SuppressWarnings("Duplicates")
    @RequestMapping("/importZZ")
    public void importZZExcel(MultipartFile excel_file, HttpServletResponse response, HttpServletRequest request) {
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
//            if (preallocations != null && preallocations.size() > 0 && noPassPreallocations.size()) {
            noPassPreallocations.addAll(saveForImport(preallocations));
            WebUtil.out(response, JsonUtil.createJQueryStr(true, JsonUtil.toStr(noPassPreallocations)));
            return;
//            }
//            WebUtil.out(response, JsonUtil.createOperaStr(false, "文件上传失败"));
//            return;
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

    private String getLandStatus(HttpServletRequest request) {
        return SessionUtil.getUserSession(request).getCurrent_land_name();
    }


    private String getHouseProperty(HttpServletRequest request) {
        return SessionUtil.getUserSession(request).getCurrent_building_name();
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
                    PreallocationImportVO preallocationsImportDTO = preallocationService.saveForImport(preallocationList.get(i), 1);
                    if (StringUtil.isNotNullOrEmpty(preallocationsImportDTO.getReason())) {
                        preallocationsImportDTO.setRowIndex(preallocationList.get(i).getRowIndex());
                        errorVOList.add(preallocationsImportDTO);
                    }
                }
            }
        } else {
            for (PrjPreallocation preallocation : preallocationList) {
                PreallocationImportVO preallocationsImportDTO = preallocationService.saveForImport(preallocation, 1);
                if (StringUtil.isNotNullOrEmpty(preallocationsImportDTO.getReason())) {
                    preallocationsImportDTO.setRowIndex(preallocation.getRowIndex());
                    errorVOList.add(preallocationsImportDTO);
                }
            }
        }
        return errorVOList;
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
            String id_card = Excel2007Util.getHssfCellStringValue(hssfRow, 1);
//            String land_status = Excel2007Util.getHssfCellStringValue(hssfRow, 1);
            String host_name = Excel2007Util.getHssfCellStringValue(hssfRow, 2);
            String location = Excel2007Util.getHssfCellStringValue(hssfRow, 3);
            String card_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 4);
            String cog_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 5);
            String total_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 6);
            String town = Excel2007Util.getHssfCellStringValue(hssfRow, 7);
            String village = Excel2007Util.getHssfCellStringValue(hssfRow, 8);
            String section = Excel2007Util.getHssfCellStringValue(hssfRow, 9);
            String groups = Excel2007Util.getHssfCellStringValue(hssfRow, 10);


//            String lessee_name = Excel2007Util.getHssfCellStringValue(hssfRow, 3);
//            String id_card = Excel2007Util.getHssfCellStringValue(hssfRow, 4);
////            String location = Excel2007Util.getHssfCellStringValue(hssfRow, 5);
//            String legal_name = Excel2007Util.getHssfCellStringValue(hssfRow, 6);
//            String total_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 7);
////            String card_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 8);
////            String cog_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 9);
//            String lessee_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 10);
////            String total_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 11);
//            String card_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 12);
//            String no_card_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 13);
//            String management_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 14);
////            String town = Excel2007Util.getHssfCellStringValue(hssfRow, 15);
////            String village = Excel2007Util.getHssfCellStringValue(hssfRow, 16);
////            String section = Excel2007Util.getHssfCellStringValue(hssfRow, 17);
////            String groups = Excel2007Util.getHssfCellStringValue(hssfRow, 18);
//            String before_area = Excel2007Util.getHssfCellStringValue(hssfRow, 19);
//            String between_area = Excel2007Util.getHssfCellStringValue(hssfRow, 20);
//            String after_area = Excel2007Util.getHssfCellStringValue(hssfRow, 21);
//            int i = 0;
//            String last_area = "";
//            String management_house_area = Excel2007Util.getHssfCellStringValue(hssfRow, 22 + i);
//            String field_house_area = Excel2007Util.getHssfCellStringValue(hssfRow, 23 + i);
//            String leader = Excel2007Util.getHssfCellStringValue(hssfRow, 24 + i);
//            String management_co = Excel2007Util.getHssfCellStringValue(hssfRow, 25 + i);
//            String geo_co = Excel2007Util.getHssfCellStringValue(hssfRow, 26 + i);
//            String appraise_co = Excel2007Util.getHssfCellStringValue(hssfRow, 27 + i);
//            String demolish_co = Excel2007Util.getHssfCellStringValue(hssfRow, 28 + i);
//            String audit_co = Excel2007Util.getHssfCellStringValue(hssfRow, 29 + i);
//            String pulledown_co = Excel2007Util.getHssfCellStringValue(hssfRow, 30 + i);
//            String demolition_year_code = Excel2007Util.getHssfCellStringValue(hssfRow, 31 + i);
//            String demolition_card_code = Excel2007Util.getHssfCellStringValue(hssfRow, 32 + i);
//            String relocate_date = Excel2007Util.getHssfCellStringValue(hssfRow, 33 + i);
//            String remarks = Excel2007Util.getHssfCellStringValue(hssfRow, 34 + i);
//            String in_host_date = Excel2007Util.getHssfCellStringValue(hssfRow, 35 + i);
//            String ldrk_num = Excel2007Util.getHssfCellStringValue(hssfRow, 36 + i);
//            String car_num = Excel2007Util.getHssfCellStringValue(hssfRow, 37 + i);
//            String rmgl_num = Excel2007Util.getHssfCellStringValue(hssfRow, 38 + i);
//            String rqgl_num = Excel2007Util.getHssfCellStringValue(hssfRow, 39 + i);
//            String zqgl_num = Excel2007Util.getHssfCellStringValue(hssfRow, 40 + i);
//            String sm_num = Excel2007Util.getHssfCellStringValue(hssfRow, 41 + i);
//            String jjzz_num = Excel2007Util.getHssfCellStringValue(hssfRow, 42 + i);
//            String scqg_num = Excel2007Util.getHssfCellStringValue(hssfRow, 43 + i);
//            String qx_num = Excel2007Util.getHssfCellStringValue(hssfRow, 44 + i);
//            String wl_num = Excel2007Util.getHssfCellStringValue(hssfRow, 45 + i);
//            String fz_num = Excel2007Util.getHssfCellStringValue(hssfRow, 46 + i);
//            String qt_num = Excel2007Util.getHssfCellStringValue(hssfRow, 47 + i);
            //noinspection Duplicates
            if (!checkImportParamsNull(map_id, host_name, section, groups)) {
                PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
                preallocationsImportDTO.setRowIndex(rowIndex);
                preallocationsImportDTO.setReason("导入失败，编号与姓名有空单元格");
                noPassPreallocations.add(preallocationsImportDTO);
            } else {
//                PrjPreallocation preallocation = getImport(map_id, host_name, location, id_card, land_status,
//                        lessee_name, legal_name, total_land_area, card_land_area, cog_land_area,
//                        lessee_land_area, total_homestead_area, card_homestead_area, no_card_homestead_area,
//                        management_homestead_area, town, village, section, groups, before_area, between_area, after_area, last_area,
//                        management_house_area, field_house_area, leader, management_co, geo_co, appraise_co, demolish_co, audit_co,
//                        pulledown_co, demolition_year_code, demolition_card_code, relocate_date, remarks, in_host_date,
//                        ldrk_num, car_num, rmgl_num, rqgl_num, zqgl_num, sm_num, jjzz_num, scqg_num, qx_num,
//                        wl_num, fz_num, qt_num, getUserName(request), rowIndex, getProjectId(request));

                PrjPreallocation preallocation = getImport(map_id, host_name, location, id_card, card_land_area, cog_land_area, total_homestead_area,
                        town, village, section, groups, getUserName(request), rowIndex, getProjectId(request), getLandStatus(request), getHouseProperty(request));
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
            String id_card = Excel2007Util.getXssfCellStringValue(xssfRow, 1);
//            String land_status = Excel2007Util.getXssfCellStringValue(xssfRow, 1);
            String host_name = Excel2007Util.getXssfCellStringValue(xssfRow, 2);
            String location = Excel2007Util.getXssfCellStringValue(xssfRow, 3);
            String card_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 4);
            String cog_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 5);
            String total_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 6);
            String town = Excel2007Util.getXssfCellStringValue(xssfRow, 7);
            String village = Excel2007Util.getXssfCellStringValue(xssfRow, 8);
            String section = Excel2007Util.getXssfCellStringValue(xssfRow, 9);
            String groups = Excel2007Util.getXssfCellStringValue(xssfRow, 10);


//            String map_id = Excel2007Util.getXssfCellStringValue(xssfRow, 0);
//            String land_status = Excel2007Util.getXssfCellStringValue(xssfRow, 1);
//            String host_name = Excel2007Util.getXssfCellStringValue(xssfRow, 2);
//            String lessee_name = Excel2007Util.getXssfCellStringValue(xssfRow, 3);
//            String id_card = Excel2007Util.getXssfCellStringValue(xssfRow, 4);
//            String location = Excel2007Util.getXssfCellStringValue(xssfRow, 5);
//            String legal_name = Excel2007Util.getXssfCellStringValue(xssfRow, 6);
//            String total_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 7);
//            String card_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 8);
//            String cog_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 9);
//            String lessee_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 10);
//            String total_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 11);
//            String card_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 12);
//            String no_card_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 13);
//            String management_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 14);
//            String town = Excel2007Util.getXssfCellStringValue(xssfRow, 15);
//            String village = Excel2007Util.getXssfCellStringValue(xssfRow, 16);
//            String section = Excel2007Util.getXssfCellStringValue(xssfRow, 17);
//            String groups = Excel2007Util.getXssfCellStringValue(xssfRow, 18);
//            String before_area = Excel2007Util.getXssfCellStringValue(xssfRow, 19);
//            String between_area = Excel2007Util.getXssfCellStringValue(xssfRow, 20);
//            String after_area = Excel2007Util.getXssfCellStringValue(xssfRow, 21);
//            int i = 0;
//            String last_area = "";
//            String management_house_area = Excel2007Util.getXssfCellStringValue(xssfRow, 22 + i);
//            String field_house_area = Excel2007Util.getXssfCellStringValue(xssfRow, 23 + i);
//            String leader = Excel2007Util.getXssfCellStringValue(xssfRow, 24 + i);
//            String management_co = Excel2007Util.getXssfCellStringValue(xssfRow, 25 + i);
//            String geo_co = Excel2007Util.getXssfCellStringValue(xssfRow, 26 + i);
//            String appraise_co = Excel2007Util.getXssfCellStringValue(xssfRow, 27 + i);
//            String demolish_co = Excel2007Util.getXssfCellStringValue(xssfRow, 28 + i);
//            String audit_co = Excel2007Util.getXssfCellStringValue(xssfRow, 29 + i);
//            String pulledown_co = Excel2007Util.getXssfCellStringValue(xssfRow, 30 + i);
//            String demolition_year_code = Excel2007Util.getXssfCellStringValue(xssfRow, 31 + i);
//            String demolition_card_code = Excel2007Util.getXssfCellStringValue(xssfRow, 32 + i);
//            String relocate_date = Excel2007Util.getXssfCellStringValue(xssfRow, 33 + i);
//            String remarks = Excel2007Util.getXssfCellStringValue(xssfRow, 34 + i);
//            String in_host_date = Excel2007Util.getXssfCellStringValue(xssfRow, 35 + i);
//            String ldrk_num = Excel2007Util.getXssfCellStringValue(xssfRow, 36 + i);
//            String car_num = Excel2007Util.getXssfCellStringValue(xssfRow, 37 + i);
//            String rmgl_num = Excel2007Util.getXssfCellStringValue(xssfRow, 38 + i);
//            String rqgl_num = Excel2007Util.getXssfCellStringValue(xssfRow, 39 + i);
//            String zqgl_num = Excel2007Util.getXssfCellStringValue(xssfRow, 40 + i);
//            String sm_num = Excel2007Util.getXssfCellStringValue(xssfRow, 41 + i);
//            String jjzz_num = Excel2007Util.getXssfCellStringValue(xssfRow, 42 + i);
//            String scqg_num = Excel2007Util.getXssfCellStringValue(xssfRow, 43 + i);
//            String qx_num = Excel2007Util.getXssfCellStringValue(xssfRow, 44 + i);
//            String wl_num = Excel2007Util.getXssfCellStringValue(xssfRow, 45 + i);
//            String fz_num = Excel2007Util.getXssfCellStringValue(xssfRow, 46 + i);
//            String qt_num = Excel2007Util.getXssfCellStringValue(xssfRow, 47 + i);

            //noinspection Duplicates
            if (!checkImportParamsNull(map_id, host_name, section, groups)) {
                PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
                preallocationsImportDTO.setRowIndex(rowIndex);
                preallocationsImportDTO.setReason("导入失败，编号与姓名或者标段与组别有空单元格");
                noPassPreallocations.add(preallocationsImportDTO);
            } else {
                PrjPreallocation preallocation = getImport(map_id, host_name, location, id_card,
                        card_land_area, cog_land_area,
                        total_homestead_area, town, village, section, groups,
                        getUserName(request), rowIndex, getProjectId(request), getLandStatus(request), getHouseProperty(request));
                preallocations.add(preallocation);
            }
        }

        return preallocations;
    }

    private boolean checkImportParamsNull(String mapId, String hostName, String section, String groups) {
        return StringUtil.isNotNullOrEmpty(mapId) && StringUtil.isNotNullOrEmpty(hostName)
                && StringUtil.isNotNullOrEmpty(section) && StringUtil.isNotNullOrEmpty(groups);
    }


    private PrjPreallocation getImport(String map_id, String host_name, String location, String id_card,
                                       String card_land_area, String cog_land_area, String total_homestead_area,
                                       String town, String village, String section, String groups,
                                       String create_person_name,
                                       int row_index, int projectId, String land_property, String house_property) {

        PrjPreallocation preallocation = new PrjPreallocation();
        preallocation.setPrj_base_info_id(projectId);
//        if (StringUtil.isNotNullOrEmpty(in_host_date)) {
//            preallocation.setStatus(10);
//        } else {
        preallocation.setStatus(0);
//        }
        preallocation.setMap_id(map_id);
        preallocation.setHost_name(host_name);
        preallocation.setLocation(location);
        preallocation.setLand_property(land_property);
        preallocation.setHouse_property(house_property);
        preallocation.setId_card(id_card);
//        preallocation.setLand_property(land_status);
//        preallocation.setLessee_name(lessee_name);
//        preallocation.setLegal_name(legal_name);
//        preallocation.setTotal_land_area(StringUtil.isNullOrEmpty(total_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(total_land_area), 2));
        preallocation.setCard_land_area(StringUtil.isNullOrEmpty(card_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(card_land_area)));
        preallocation.setCog_land_area(StringUtil.isNullOrEmpty(cog_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(cog_land_area)));
//        preallocation.setLessee_land_area(StringUtil.isNullOrEmpty(lessee_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(lessee_land_area)));
        preallocation.setTotal_homestead_area(StringUtil.isNullOrEmpty(total_homestead_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(total_homestead_area)));
//        preallocation.setCard_homestead_area(StringUtil.isNullOrEmpty(card_homestead_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(card_homestead_area)));
//        preallocation.setNo_card_homestead_area(StringUtil.isNullOrEmpty(no_card_homestead_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(no_card_homestead_area)));
//        preallocation.setManagement_homestead_area(StringUtil.isNullOrEmpty(management_homestead_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(management_homestead_area)));
        preallocation.setTown(town);
        preallocation.setVillage(village);
        preallocation.setSection(section);
        preallocation.setGroups(groups);
//        preallocation.setBefore_area(StringUtil.isNullOrEmpty(before_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(before_area)));
//        preallocation.setBetween_area(StringUtil.isNullOrEmpty(between_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(between_area)));
//        preallocation.setAfter_area(StringUtil.isNullOrEmpty(after_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(after_area)));
//        preallocation.setLast_area(StringUtil.isNullOrEmpty(last_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(last_area)));
//        preallocation.setManagement_house_area(StringUtil.isNullOrEmpty(management_house_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(management_house_area)));
//        preallocation.setField_house_area(StringUtil.isNullOrEmpty(field_house_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(field_house_area)));
//        preallocation.setLeader(leader);
//        preallocation.setManagement_co(management_co);
//        preallocation.setGeo_co(geo_co);
//        preallocation.setAppraise_co(appraise_co);
//        preallocation.setDemolish_co(demolish_co);
//        preallocation.setAudit_co(audit_co);
//        preallocation.setPulledown_co(pulledown_co);
//        preallocation.setDemolition_card_code(demolition_card_code);
//        preallocation.setDemolition_year_code(demolition_year_code);
//        preallocation.setRelocate_date(relocate_date);
//
//        preallocation.setRemarks(remarks);
//        preallocation.setIn_host_date(in_host_date);
//
//        preallocation.setFloat_people(StringUtil.isNullOrEmpty(ldrk_num) ? 0 : Integer.valueOf(ldrk_num));
//        preallocation.setRmgl_num(StringUtil.isNullOrEmpty(rmgl_num) ? 0 : Integer.valueOf(rmgl_num));
//        preallocation.setRqgl_num(StringUtil.isNullOrEmpty(rqgl_num) ? 0 : Integer.valueOf(rqgl_num));
//        preallocation.setZqgl_num(StringUtil.isNullOrEmpty(zqgl_num) ? 0 : Integer.valueOf(zqgl_num));
//        preallocation.setCar_num(StringUtil.isNullOrEmpty(car_num) ? 0 : Integer.valueOf(car_num));
//        preallocation.setJzzz_num(StringUtil.isNullOrEmpty(jjzz_num) ? 0 : Integer.valueOf(jjzz_num));
//        preallocation.setScqg_num(StringUtil.isNullOrEmpty(scqg_num) ? 0 : Integer.valueOf(scqg_num));
//        preallocation.setQx_num(StringUtil.isNullOrEmpty(qx_num) ? 0 : Integer.valueOf(qx_num));
//        preallocation.setWl_num(StringUtil.isNullOrEmpty(wl_num) ? 0 : Integer.valueOf(wl_num));
//        preallocation.setQt_num(StringUtil.isNullOrEmpty(qt_num) ? 0 : Integer.valueOf(qt_num));
//        preallocation.setFz_num(StringUtil.isNullOrEmpty(fz_num) ? 0 : Integer.valueOf(fz_num));
//
//        preallocation.setScattered_coal(StringUtil.isNullOrEmpty(ldrk_num) ? 0 : Float.valueOf(sm_num));


        preallocation.setCreated_by(create_person_name);
        preallocation.setRowIndex(row_index);
        return preallocation;
    }


}
