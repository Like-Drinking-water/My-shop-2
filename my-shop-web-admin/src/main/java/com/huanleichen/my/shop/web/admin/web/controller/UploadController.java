package com.huanleichen.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {
    private static final String UPLOAD_PATH = "/static/upload/";

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropFile, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        //确定存放上传文件的绝对路径
        String filePath = request.getSession().getServletContext().getRealPath("static/upload");
        //创建 File 对象
        File dir = new File(filePath);
        //如果文件夹不存在则创建文件夹
        if (!dir.exists()) {
            dir.mkdir();
        }
        //获取文件的名称并获取其后缀
        String fileName = dropFile.getOriginalFilename();
        String fileNameTail = fileName.substring(fileName.lastIndexOf("."));

        File file = new File(filePath, UUID.randomUUID() + fileNameTail);

        try {
            dropFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        data.put("fileName", file.getName());

        return data;
    }
}
