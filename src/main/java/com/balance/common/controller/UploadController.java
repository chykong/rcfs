package com.balance.common.controller;

import com.balance.util.code.SerialNumUtil;
import com.balance.util.config.PubConfig;
import com.balance.util.date.DateUtil;
import com.balance.util.string.StringUtil;
import com.balance.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Paths;

/**
 * 文件上传controller
 *
 * @author chykong
 */
@Controller
@RequestMapping("/common")
public class UploadController {
    @Autowired
    private PubConfig pubConfig;

    @RequestMapping("/upload")
    public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String uploadPath = pubConfig.getImageUploadPath();
        String storePath = "/uploadfile/" + DateUtil.getShortSystemDate() + "/";
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        //        String fileName = new Date().getTime()+".jpg";
        String createFilename = DateUtil.getShortSystemTime() + SerialNumUtil.createRandowmNum(6) + "." + suffix;
        File targetFile = new File(uploadPath + storePath, createFilename);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String json = getjson(file, createFilename, storePath, targetFile, fileName);
        WebUtil.out(response, json);
    }


    public String getjson(MultipartFile file, String createFilename, String storePath, File targetFile, String fileName) {
        String json = "";
        if (file.getSize() > 10 * 1024 * 1024) {
            json = "{success:" + false + ",msgText:'" + "文件超过10M" + "'}";
        } else {
            //保存
            try {
                file.transferTo(targetFile);
                json = "{success:" + true + ",msgText:'" + "成功" + "',createFilename:'" + createFilename + "',createFilepath:'" + storePath
                        + "',original_name:'" + fileName + "'}";
            } catch (Exception e) {
                json = "{success:" + false + ",msgText:'" + "上传失败" + e.getMessage() + "'}";
                e.printStackTrace();
            }
        }
        return json;
    }

    @RequestMapping("/uploadError")
    public void uploadError(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("文件超出大小");
        String json = "{success:" + false + ",msgText:'" + "大小查出10M" + "'}";
        WebUtil.out(response, json);
    }

    @RequestMapping("delFile")
    public void delFile(String path, HttpServletResponse response) {
        String json = "{success:" + true + ",msgText:'" + "删除成功" + "'}";

        if (StringUtil.isNullOrEmpty(path)) {
            json = "{success:" + false + ",msgText:'" + "找不到文件" + "'}";
            WebUtil.out(response, json);
            return;
        }
        String base_path = pubConfig.getImageUploadPath();
        File file = Paths.get(base_path, path).toFile();
        if (file.isFile()) {
            file.delete();
        }
        WebUtil.out(response, json);

    }
}
