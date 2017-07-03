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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dsy on 2017/6/4.
 * 拆迁户信息Controller
 */
@Controller
@RequestMapping("/prj/preallocation/basic")
public class PrjPreallocationBasicController extends BaseController {
    @Autowired
    private PrjPreallocationService preallocationService;
    @Autowired
    private PrjSectionService prjSectionService;
    @Autowired
    private BaseCompanyService baseCompanyService;

    @RequestMapping(value = {"", "index"})
    public ModelAndView index(HttpServletRequest request, PrjPreallocationSearchVO preallocationSearchVO) {
        setBtnAutho(request, "PrjPreallocationBase");
        ModelAndView mv = new ModelAndView("prj/preallocationBasicIndex");

        int land_status = SessionUtil.getUserSession(request).getCurrent_land_status();
        mv.addObject("land_status", land_status);

        List<PrjSection> sectionList = prjSectionService.listByprj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("sectionList", sectionList);

        List<ComboboxVO> townList = preallocationService.getTown(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("townList", townList);

        mv.addObject("preallocationSearchVO", preallocationSearchVO);

        BackUrlUtil.createBackUrl(mv, request, "/prj/preallocation/basic/index.htm");
        return mv;
    }

    @RequestMapping(value = "getPreallocation", method = RequestMethod.GET)
    @ResponseBody
    public Map getBasic(PrjPreallocationSearchVO preallocationSearchVO, HttpServletRequest request) {
        Map<Object, Object> map = new HashMap<>();
        preallocationSearchVO.setBase_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        preallocationSearchVO.setLand_status(SessionUtil.getUserSession(request).getCurrent_land_status());

        int count = preallocationService.count(preallocationSearchVO);

        List<PrjPreallocation> preallocations = preallocationService.findAll(preallocationSearchVO);

        map.put("data", preallocations);
        map.put("recordsTotal", count);   //必须有  否则报错
        map.put("recordsFiltered", count);  //必须有 否则报错
        map.put("searchDTO", preallocationSearchVO);
        return map;
    }


    @RequestMapping(value = "toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/prj/preallocationAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        PrjPreallocation preallocation = new PrjPreallocation();

        String land_status = SessionUtil.getUserSession(request).getCurrent_land_status() == 1 ? "国有" : "集体";
        preallocation.setLand_property(land_status);
        mv.addObject("preallocation", preallocation);

        List<PrjSection> sectionList = prjSectionService.listByprj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("sectionList", sectionList);
//
        List<BaseCompany> companyList = baseCompanyService.listAll();
        mv.addObject("companyList", companyList);

        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(PrjPreallocation preallocation, HttpServletRequest request) {
        preallocation.setCreated_by(SessionUtil.getUserSession(request).getUser_name());
        preallocation.setPrj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        int flag = preallocationService.add(preallocation);

        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("拆除腾退人新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("拆除腾退人新增成功");
    }

    @RequestMapping(value = "toUpdate", method = RequestMethod.GET)
    public ModelAndView toUpdate(int id, int type, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/prj/preallocationUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url

        PrjPreallocation preallocation = preallocationService.getById(id);
        if (preallocation == null) {
            mv.setViewName("/error.htm");
            mv.addObject("msg", "拆除腾退人不存在");
            return mv;
        }
        List<PrjSection> sectionList = prjSectionService.listByprj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("sectionList", sectionList);
//
        List<BaseCompany> companyList = baseCompanyService.listAll();
        mv.addObject("companyList", companyList);

        mv.addObject("type", type);

        mv.addObject("preallocation", preallocation);
        return mv;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(PrjPreallocation preallocation) {
        PrjPreallocation preallocation_DB = preallocationService.getById(preallocation.getId());
        preallocation.setPrj_base_info_id(preallocation_DB.getPrj_base_info_id());
        preallocation.setCreated_by(preallocation_DB.getCreated_by());

        int flag = preallocationService.update(preallocation);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("拆除腾退人修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("拆除腾退人修改成功");
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(int id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/prj/preallocationView");

        PrjPreallocation preallocation = preallocationService.getById(id);
        if (preallocation == null) {
            mv.setViewName("/error.htm");
            mv.addObject("msg", "拆除腾退人不存在");
            return mv;
        }
        List<PrjSection> sectionList = prjSectionService.listByprj_base_info_id(SessionUtil.getUserSession(request).getCurrent_project_id());
        mv.addObject("sectionList", sectionList);

        List<BaseCompany> companyList = baseCompanyService.listAll();
        mv.addObject("companyList", companyList);

        mv.addObject("preallocation", preallocation);
        mv.addObject("view_type", 1);
        return mv;
    }

    @RequestMapping("/checkMapId")
    @ResponseBody
    public boolean checkMapId(String mapId, HttpServletRequest request) {
        int base_info_id = SessionUtil.getUserSession(request).getCurrent_project_id();
        return !preallocationService.existByMapId(mapId, base_info_id);
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
            String land_status = Excel2007Util.getHssfCellStringValue(hssfRow, 1);
            String host_name = Excel2007Util.getHssfCellStringValue(hssfRow, 2);
            String lessee_name = Excel2007Util.getHssfCellStringValue(hssfRow, 3);
            String id_card = Excel2007Util.getHssfCellStringValue(hssfRow, 4);
            String location = Excel2007Util.getHssfCellStringValue(hssfRow, 5);
            String legal_name = Excel2007Util.getHssfCellStringValue(hssfRow, 6);
            String total_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 7);
            String card_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 8);
            String cog_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 9);
            String lessee_land_area = Excel2007Util.getHssfCellStringValue(hssfRow, 10);
            String total_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 11);
            String card_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 12);
            String no_card_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 13);
            String management_homestead_area = Excel2007Util.getHssfCellStringValue(hssfRow, 14);
            String town = Excel2007Util.getHssfCellStringValue(hssfRow, 15);
            String village = Excel2007Util.getHssfCellStringValue(hssfRow, 16);
            String section = Excel2007Util.getHssfCellStringValue(hssfRow, 17);
            String groups = Excel2007Util.getHssfCellStringValue(hssfRow, 18);
            String before_area = Excel2007Util.getHssfCellStringValue(hssfRow, 19);
            String between_area = Excel2007Util.getHssfCellStringValue(hssfRow, 20);
            String after_area = Excel2007Util.getHssfCellStringValue(hssfRow, 21);
            String management_house_area = Excel2007Util.getHssfCellStringValue(hssfRow, 22);
            String field_house_area = Excel2007Util.getHssfCellStringValue(hssfRow, 23);
            String leader = Excel2007Util.getHssfCellStringValue(hssfRow, 24);
            String management_co = Excel2007Util.getHssfCellStringValue(hssfRow, 25);
            String geo_co = Excel2007Util.getHssfCellStringValue(hssfRow, 26);
            String appraise_co = Excel2007Util.getHssfCellStringValue(hssfRow, 27);
            String demolish_co = Excel2007Util.getHssfCellStringValue(hssfRow, 28);
            String audit_co = Excel2007Util.getHssfCellStringValue(hssfRow, 29);
            String pulledown_co = Excel2007Util.getHssfCellStringValue(hssfRow, 30);
            String demolition_year_code = Excel2007Util.getHssfCellStringValue(hssfRow, 31);
            String demolition_card_code = Excel2007Util.getHssfCellStringValue(hssfRow, 32);
            String relocate_date = Excel2007Util.getHssfCellStringValue(hssfRow, 33);
            String remarks = Excel2007Util.getHssfCellStringValue(hssfRow, 34);
            String in_host_date = Excel2007Util.getHssfCellStringValue(hssfRow, 35);
            //noinspection Duplicates
            if (!checkImportParamsNull(map_id, host_name, section, groups)) {
                PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
                preallocationsImportDTO.setRowIndex(rowIndex);
                preallocationsImportDTO.setReason("导入失败，编号与姓名有空单元格");
                noPassPreallocations.add(preallocationsImportDTO);
            } else {
                PrjPreallocation preallocation = getImport(map_id, host_name, location, id_card, land_status,
                        lessee_name, legal_name, total_land_area, card_land_area, cog_land_area,
                        lessee_land_area, total_homestead_area, card_homestead_area, no_card_homestead_area,
                        management_homestead_area, town, village, section, groups, before_area, between_area, after_area,
                        management_house_area, field_house_area, leader, management_co, geo_co, appraise_co, demolish_co, audit_co,
                        pulledown_co, demolition_year_code, demolition_card_code, relocate_date, remarks, in_host_date,
                        getUserName(request), rowIndex, getProjectId(request));

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
            String land_status = Excel2007Util.getXssfCellStringValue(xssfRow, 1);
            String host_name = Excel2007Util.getXssfCellStringValue(xssfRow, 2);
            String lessee_name = Excel2007Util.getXssfCellStringValue(xssfRow, 3);
            String id_card = Excel2007Util.getXssfCellStringValue(xssfRow, 4);
            String location = Excel2007Util.getXssfCellStringValue(xssfRow, 5);
            String legal_name = Excel2007Util.getXssfCellStringValue(xssfRow, 6);
            String total_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 7);
            String card_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 8);
            String cog_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 9);
            String lessee_land_area = Excel2007Util.getXssfCellStringValue(xssfRow, 10);
            String total_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 11);
            String card_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 12);
            String no_card_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 13);
            String management_homestead_area = Excel2007Util.getXssfCellStringValue(xssfRow, 14);
            String town = Excel2007Util.getXssfCellStringValue(xssfRow, 15);
            String village = Excel2007Util.getXssfCellStringValue(xssfRow, 16);
            String section = Excel2007Util.getXssfCellStringValue(xssfRow, 17);
            String groups = Excel2007Util.getXssfCellStringValue(xssfRow, 18);
            String before_area = Excel2007Util.getXssfCellStringValue(xssfRow, 19);
            String between_area = Excel2007Util.getXssfCellStringValue(xssfRow, 20);
            String after_area = Excel2007Util.getXssfCellStringValue(xssfRow, 21);
            String management_house_area = Excel2007Util.getXssfCellStringValue(xssfRow, 22);
            String field_house_area = Excel2007Util.getXssfCellStringValue(xssfRow, 23);
            String leader = Excel2007Util.getXssfCellStringValue(xssfRow, 24);
            String management_co = Excel2007Util.getXssfCellStringValue(xssfRow, 25);
            String geo_co = Excel2007Util.getXssfCellStringValue(xssfRow, 26);
            String appraise_co = Excel2007Util.getXssfCellStringValue(xssfRow, 27);
            String demolish_co = Excel2007Util.getXssfCellStringValue(xssfRow, 28);
            String audit_co = Excel2007Util.getXssfCellStringValue(xssfRow, 29);
            String pulledown_co = Excel2007Util.getXssfCellStringValue(xssfRow, 30);
            String demolition_year_code = Excel2007Util.getXssfCellStringValue(xssfRow, 31);
            String demolition_card_code = Excel2007Util.getXssfCellStringValue(xssfRow, 32);
            String relocate_date = Excel2007Util.getXssfCellStringValue(xssfRow, 33);
            String remarks = Excel2007Util.getXssfCellStringValue(xssfRow, 34);
            String in_host_date = Excel2007Util.getXssfCellStringValue(xssfRow, 35);

            //noinspection Duplicates
            if (!checkImportParamsNull(map_id, host_name, section, groups)) {
                PreallocationImportVO preallocationsImportDTO = new PreallocationImportVO();
                preallocationsImportDTO.setRowIndex(rowIndex);
                preallocationsImportDTO.setReason("导入失败，编号与姓名或者标段与组别有空单元格");
                noPassPreallocations.add(preallocationsImportDTO);
            } else {
                PrjPreallocation preallocation = getImport(map_id, host_name, location, id_card, land_status,
                        lessee_name, legal_name, total_land_area, card_land_area, cog_land_area,
                        lessee_land_area, total_homestead_area, card_homestead_area, no_card_homestead_area,
                        management_homestead_area, town, village, section, groups, before_area, between_area, after_area,
                        management_house_area, field_house_area, leader, management_co, geo_co, appraise_co, demolish_co, audit_co,
                        pulledown_co, demolition_year_code, demolition_card_code, relocate_date, remarks,  in_host_date,
                        getUserName(request), rowIndex, getProjectId(request));
                preallocations.add(preallocation);
            }
        }

        return preallocations;
    }

    private boolean checkImportParamsNull(String mapId, String hostName, String section, String groups) {
        return StringUtil.isNotNullOrEmpty(mapId) && StringUtil.isNotNullOrEmpty(hostName)
                && StringUtil.isNotNullOrEmpty(section) && StringUtil.isNotNullOrEmpty(groups);
    }

    private PrjPreallocation getImport(String map_id, String host_name, String location, String id_card, String land_status,
                                       String lessee_name, String legal_name, String total_land_area, String card_land_area,
                                       String cog_land_area, String lessee_land_area, String total_homestead_area,
                                       String card_homestead_area, String no_card_homestead_area, String management_homestead_area,
                                       String town, String village, String section, String groups, String before_area, String between_area,
                                       String after_area, String management_house_area, String field_house_area, String leader,
                                       String management_co, String geo_co, String appraise_co, String demolish_co, String audit_co,
                                       String pulledown_co, String demolition_year_code, String demolition_card_code,
                                       String relocate_date, String remarks, String in_host_date, String create_person_name,
                                       int row_index, int projectId) {

        PrjPreallocation preallocation = new PrjPreallocation();
        preallocation.setPrj_base_info_id(projectId);
        preallocation.setStatus(10);
        preallocation.setMap_id(map_id);
        preallocation.setHost_name(host_name);
        preallocation.setLocation(location);
        preallocation.setId_card(id_card);
        preallocation.setLand_property(land_status);
        preallocation.setLessee_name(lessee_name);
        preallocation.setLegal_name(legal_name);
        preallocation.setTotal_land_area(StringUtil.isNullOrEmpty(total_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(total_land_area), 2));
        preallocation.setCard_land_area(StringUtil.isNullOrEmpty(card_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(card_land_area)));
        preallocation.setCog_land_area(StringUtil.isNullOrEmpty(cog_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(cog_land_area)));
        preallocation.setLessee_land_area(StringUtil.isNullOrEmpty(lessee_land_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(lessee_land_area)));
        preallocation.setTotal_homestead_area(StringUtil.isNullOrEmpty(total_homestead_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(total_homestead_area)));
        preallocation.setCard_homestead_area(StringUtil.isNullOrEmpty(card_homestead_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(card_homestead_area)));
        preallocation.setNo_card_homestead_area(StringUtil.isNullOrEmpty(no_card_homestead_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(no_card_homestead_area)));
        preallocation.setManagement_homestead_area(StringUtil.isNullOrEmpty(management_homestead_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(management_homestead_area)));
        preallocation.setTown(town);
        preallocation.setVillage(village);
        preallocation.setSection(section);
        preallocation.setGroups(groups);
        preallocation.setBefore_area(StringUtil.isNullOrEmpty(before_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(before_area)));
        preallocation.setBetween_area(StringUtil.isNullOrEmpty(between_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(between_area)));
        preallocation.setAfter_area(StringUtil.isNullOrEmpty(after_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(after_area)));
        preallocation.setManagement_house_area(StringUtil.isNullOrEmpty(management_house_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(management_house_area)));
        preallocation.setField_house_area(StringUtil.isNullOrEmpty(field_house_area) ? 0 : NumberUtil.formatDouble(Double.valueOf(field_house_area)));
        preallocation.setLeader(leader);
        preallocation.setManagement_co(management_co);
        preallocation.setGeo_co(geo_co);
        preallocation.setAppraise_co(appraise_co);
        preallocation.setDemolish_co(demolish_co);
        preallocation.setAudit_co(audit_co);
        preallocation.setPulledown_co(pulledown_co);
        preallocation.setDemolition_card_code(demolition_card_code);
        preallocation.setDemolition_year_code(demolition_year_code);
        preallocation.setRelocate_date(relocate_date);

        preallocation.setRemarks(remarks);
        preallocation.setIn_host_date(in_host_date);
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
                preallocationsImportDTO.setReason("编号重复!");
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

    @RequestMapping("/getVillageByTown")
    public void getVillageByTown(HttpServletRequest request, HttpServletResponse response, String town) {
        String json = JsonUtil.toStr(preallocationService.getVillageByTown(SessionUtil.getUserSession(request).getCurrent_project_id(), town));
        WebUtil.out(response, json);
    }

}
