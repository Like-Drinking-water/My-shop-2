package com.huanleichen.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UploadController {
    private static final String UPLOAD_PATH = "/static/upload/";

    /**
     * 上传文件到服务器
     * @param dropFile Dropzone 的文件
     * @param editorFile wangEditor 的文件
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropFile, MultipartFile editorFile, HttpServletRequest request) {

        MultipartFile myFile = (dropFile == null) ? editorFile : dropFile;

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
        String fileName = myFile.getOriginalFilename();
        String fileNameTail = fileName.substring(fileName.lastIndexOf("."));

        File file = new File(filePath, UUID.randomUUID() + fileNameTail);

        try {
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //处理 Dropzone 上传的文件
        if (dropFile != null) {
            data.put("fileName", file.getName());
        }
        //处理 wangEditor 上传的文件
        else {
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            List<String> picture = new ArrayList<>();

            picture.add(serverPath + "/myshop/static/upload/" +  file.getName());
            data.put("errno", 0);
            data.put("data", picture);
        }
        return data;
    }
}
