package com.dreamer.invite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sima on 2017/1/12.
 */
@Controller
@RequestMapping("/invite")
public class WxMpInvitationController {


    /**
     * 保存请帖基本信息
     * @param inviteType  请帖类型
     * @param men 男方名字
     * @param female    女方名字
     * @param address   典礼地址
     * @param telephone 联系电话
     * @param weddingTime   典礼时间
     * @param request
     * @return  填写之后跳转页
     */
    @RequestMapping("/info")
    public String getInvitationInfo(@RequestParam("inviteType") String inviteType,
                                    @RequestParam("men") String men,
                                    @RequestParam("female") String female,
                                    @RequestParam("address") String address,
                                    @RequestParam("telephone") String telephone,
                                    @RequestParam("weddingTime") String weddingTime,
                                    HttpServletRequest request){

        return "";

    }
}
