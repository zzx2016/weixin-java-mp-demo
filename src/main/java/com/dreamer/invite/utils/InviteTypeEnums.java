package com.dreamer.invite.utils;

/**
 * Created by sima on 2017/1/12.
 */
public enum InviteTypeEnums {

    WEDDING(1,"婚礼"),
    CONFERENCE(2,"会议"),
    ACTIVITY(3,"活动");

    private Integer id;
    private String name;

    InviteTypeEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    InviteTypeEnums[] getAllEnums(){
        return InviteTypeEnums.values();
    }
}
