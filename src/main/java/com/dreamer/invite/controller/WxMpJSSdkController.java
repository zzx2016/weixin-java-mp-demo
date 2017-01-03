package com.dreamer.invite.controller;

import com.dreamer.invite.service.WeixinService;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by zhangzixu on 2016/12/28.
 * 生成JS-SDK签名信息
 */
@RestController
public class WxMpJSSdkController {
    private final Logger mLogger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WeixinService mWxService;

    @ResponseBody
    @RequestMapping("/wechat/jssdk")
    public WxJsapiSignature getInvalidSignature(@RequestParam(name = "url") String url) {
        WxJsapiSignature wxJsapiSignature = new WxJsapiSignature();
        mLogger.info("转码前：" + url);
        try {
            url = URLDecoder.decode(url, "UTF-8");
            mLogger.info("转码后：" + url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            wxJsapiSignature = mWxService.createJsapiSignature(url);
            mLogger.info("jsapi_ticket：" + mWxService.getJsapiTicket());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return wxJsapiSignature;
    }
}
