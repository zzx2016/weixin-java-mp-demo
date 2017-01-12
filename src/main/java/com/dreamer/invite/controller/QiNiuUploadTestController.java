package com.dreamer.invite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreamer.invite.qiniu.QiNiuImageHelper;

@RestController
@RequestMapping("/qiniu")
public class QiNiuUploadTestController {

    @Autowired
    private QiNiuImageHelper qiniuHelper;

    @RequestMapping("/upload")
    public String uploadImage() {
        return qiniuHelper.Upload("E:/c1000/c1000_bottom_qq.png", "qq.jpg");
    }

    @RequestMapping("/overlay")
    public String overlayUpload() {
        return qiniuHelper.OverlayUpload("E:/c1000/c1000_row1_laba.png", "qq.jpg");
    }

    @RequestMapping("/download")
    public String download() {
        return qiniuHelper.download("qq.jpg");
    }

    @RequestMapping("/delete")
    public void deleteImage() {
        qiniuHelper.delete("qq.jpg");
    }

}
