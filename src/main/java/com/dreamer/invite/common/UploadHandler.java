package com.dreamer.invite.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dreamer.invite.qiniu.QiNiuImageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sima on 2017/1/11.
 */
@Controller
@RequestMapping("/upload")
public class UploadHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QiNiuImageHelper qiniuHelper;

    @RequestMapping("/toUpload")
    public ModelAndView toUpload(ModelAndView model){
        model.setViewName("upload");
        return model;
    }

    /**
     * 单文件上传，返回文件url
     * @param upload
     * @param request
     * @return
     */
    @RequestMapping("singleupload")
    @ResponseBody
    public String upload(MultipartFile upload, HttpServletRequest request){

            if(!upload.isEmpty()){
                try {
                    File tempFile = File.createTempFile(String.valueOf(new Date().getTime()),upload.getOriginalFilename());
                    upload.transferTo(tempFile);
                    JSONObject obj = JSONObject.parseObject(qiniuHelper.Upload(tempFile.getAbsolutePath(),upload.getOriginalFilename()));
                    return obj.getString("url");
                } catch (Exception e) {
                    logger.error("文件"+upload.getOriginalFilename()+"上传出错",e);
                }
            }
        logger.info("上传文件不成功,未获取到上传之后的url");
        return "";
    }

    /**
     * 多文件上传，返回 key：url的json格式
     * @param upload
     * @param request
     * @return
     */
    @RequestMapping("multiupload")
    @ResponseBody
    public String multiupload(@RequestParam("upload") MultipartFile upload[], HttpServletRequest request){

        Map<String,String> map = new HashMap<String,String>();
        for (int i=0;i<upload.length;i++){
            if(!upload[i].isEmpty()){
                long start =  System.currentTimeMillis();
                try {
                    File tempFile = File.createTempFile(String.valueOf(new Date().getTime()),upload[i].getOriginalFilename());
                    upload[i].transferTo(tempFile);
                    JSONObject obj = JSONObject.parseObject(qiniuHelper.Upload(tempFile.getAbsolutePath(),upload[i].getOriginalFilename()));
                    map.put(obj.getString("key"),obj.getString("url"));
                } catch (Exception e) {
                    logger.error("文件"+upload[i].getOriginalFilename()+"上传出错",e);
                }
            }
        }
        return JSON.toJSONString(map);
    }
}
