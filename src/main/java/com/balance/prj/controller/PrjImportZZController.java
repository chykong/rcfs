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
            String fileName = excel_file.getOriginalFilename();//得到文件名称
            String fileExt = FilenameUtils.getExtension(fileName);//获取文件的扩展名
            List<PreallocationImportVO> noPassPreallocations = new ArrayList<>();
            List<PrjPreallocation> preallocations;
            switch (fileExt) {
                case "xls": {
                    HSSFWorkbook preallocationWb = new HSSFWorkbook(excel_file.getInputStream());//创建一个excel文件将   得到一个输入流  放进去
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
        HSSFSheet firstSheet = hssfWorkbook.getSheetAt(0);//得到第一行索引
        if (firstSheet == null || firstSheet.getLastRowNum() < 1) {//最后一行行标
            return null;
        }
        int lastRowNum = firstSheet.getLastRowNum();//获得最后一行行标
        for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
            HSSFRow hssfRow = firstSheet.getRow(rowIndex);
            if (hssfRow == null) continue;
            String map_id = Excel2007Util.getHssfCellStringValue(hssfRow, 0);//编号
            String id_card = Excel2007Util.getHssfCellStringValue(hssfRow, 1);//身份证号
            String host_name = Excel2007Util.getHssfCellStringValue(hssfRow, 2);//被拆除腾退人
            String location = Excel2007Util.getHssfCellStringValue(hssfRow, 3);//房屋坐落
            String card_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 4);//实测宅基地面积
            String cog_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 5);//确权宅基地面积
            String total_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 6);//总建筑面积
            Double card_homestead_area = (Integer.parseInt(total_homestead_area) * 0.75);//总面积的百分之75
            String town = Excel2007Util.getHssfCellStringValue(hssfRow, 7);//镇
            String village = Excel2007Util.getHssfCellStringValue(hssfRow, 8);//村
            String section = Excel2007Util.getHssfCellStringValue(hssfRow, 9);//标段
            String groups = Excel2007Util.getHssfCellStringValue(hssfRow, 10);//组
            String existFamily = Excel2007Util.getHssfCellStringValue(hssfRow, 11);//是否分户
            String yardOrFamily = Excel2007Util.getHssfCellStringValue(hssfRow, 12);//院还是户
            String yardCode = Excel2007Util.getHssfCellStringValue(hssfRow, 13);//院的编号
            String select_house_code = Excel2007Util.getHssfCellStringValue(hssfRow, 14);//选房顺序号
            int parent_type = 0;
            if (existFamily.equals("是")) {
                parent_type = yardOrFamily.equals("院") ? 1 : yardOrFamily.equals("户") ? 2 : 1;
            } else {
                parent_type = 0;
            }

            if (!checkImportParamsNull(map_id, host_name, section, groups)) {
                PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
                preallocationsImportDTO.setRowIndex(rowIndex);
                preallocationsImportDTO.setReason("导入失败，编号与姓名有空单元格");
                noPassPreallocations.add(preallocationsImportDTO);
            } else {

                PrjPreallocation preallocation = getImport(map_id, host_name, location, id_card, card_land_area, cog_land_area, total_homestead_area,
                        town, village, section, groups, getUserName(request), rowIndex, getProjectId(request), getLandStatus(request), getHouseProperty(request)
                        , card_homestead_area, parent_type, yardCode, select_house_code);
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
            String host_name = Excel2007Util.getXssfCellStringValue(xssfRow, 2);
            String location = Excel2007Util.getXssfCellStringValue(xssfRow, 3);
            String card_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 4);
            String cog_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 5);
            String total_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 6);
            String town = Excel2007Util.getXssfCellStringValue(xssfRow, 7);
            String village = Excel2007Util.getXssfCellStringValue(xssfRow, 8);
            String section = Excel2007Util.getXssfCellStringValue(xssfRow, 9);
            String groups = Excel2007Util.getXssfCellStringValue(xssfRow, 10);
            Double card_homestead_area = (Integer.parseInt(total_homestead_area) * 0.75);//总面积的百分之75
            String existFamily = Excel2007Util.getXssfCellStringValue(xssfRow, 11);//是否分户
            String yardOrFamily = Excel2007Util.getXssfCellStringValue(xssfRow, 12);//院还是户
            String yardCode = Excel2007Util.getXssfCellStringValue(xssfRow, 13);//院的编号
            String select_house_code = Excel2007Util.getXssfCellStringValue(xssfRow, 14);//选房顺序号
            int parent_type = 0;
            if (existFamily.equals("是")) {
                parent_type = yardOrFamily.equals("院") ? 1 : yardOrFamily.equals("户") ? 2 : 1;
            } else {
                parent_type = 0;
            }


            if (!checkImportParamsNull(map_id, host_name, section, groups)) {
                PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
                preallocationsImportDTO.setRowIndex(rowIndex);
                preallocationsImportDTO.setReason("导入失败，编号与姓名或者标段与组别有空单元格");
                noPassPreallocations.add(preallocationsImportDTO);
            } else {
                PrjPreallocation preallocation = getImport(map_id, host_name, location, id_card,
                        card_land_area, cog_land_area,
                        total_homestead_area, town, village, section, groups,
                        getUserName(request), rowIndex, getProjectId(request), getLandStatus(request), getHouseProperty(request),
                        card_homestead_area, parent_type, yardCode, select_house_code);
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
                                       int row_index, int projectId, String land_property, String house_property, Double card_homestead_area, int parent_type, String yardCode, String select_house_code) {

        PrjPreallocation preallocation = new PrjPreallocation();
        preallocation.setPrj_base_info_id(projectId);
        preallocation.setStatus(0);
        preallocation.setMap_id(map_id);
        preallocation.setHost_name(host_name);
        preallocation.setLocation(location);
        preallocation.setLand_property(land_property);
        preallocation.setHouse_property(house_property);
        preallocation.setId_card(id_card);
        preallocation.setCard_land_area(StringUtil.isNullOrEmpty(card_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(card_land_area)));
        preallocation.setCog_land_area(StringUtil.isNullOrEmpty(cog_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(cog_land_area)));
        preallocation.setTotal_homestead_area(StringUtil.isNullOrEmpty(total_homestead_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(total_homestead_area)));
        preallocation.setTown(town);
        preallocation.setVillage(village);
        preallocation.setSection(section);
        preallocation.setGroups(groups);

        preallocation.setCreated_by(create_person_name);
        preallocation.setRowIndex(row_index);
        preallocation.setCard_homestead_area(card_homestead_area);
        preallocation.setParent_type(parent_type);
        preallocation.setParent_id(yardCode);
        preallocation.setSigned_code(select_house_code);
        return preallocation;
    }


}
