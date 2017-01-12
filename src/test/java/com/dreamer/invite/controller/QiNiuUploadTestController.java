package com.dreamer.invite.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreamer.invite.qiniu.QiNiuImageHelper;

/**
 * Created by sima on 2017/1/12.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class QiNiuUploadTestController {

    @Autowired
    private QiNiuImageHelper qiniuHelper;

    @Test
    public void uploadImage() {

        System.out.println(qiniuHelper.upload("E:/c1000/c1000_bottom_qq.png", "qq.jpg"));
    }

    @Test
    public void overlayUpload() {

        System.out.println(qiniuHelper.overlayUpload("E:/c1000/c1000_top_logo.png", "qq.jpg"));
    }

    @Test
    public void download() {
        qiniuHelper.download("qq.jpg");
    }

    @Test
    public void deleteImage() {

        qiniuHelper.delete("qq.jpg");
    }

}
