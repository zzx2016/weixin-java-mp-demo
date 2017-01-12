package com.dreamer.invite.utils;

/**
 * Created by sima on 2017/1/12.
 */
public enum InviteTypeEnums {

    WEDDING(1,"婚礼"),
    CONFERENCE(2,"会议"),
    ACTIVITY(3,"活动");

    private int id;
    private String name;

    InviteTypeEnums(String name, int id) {
        this.name = name;
        this.id = id;
    }

    InviteTypeEnums[] getAllEnums(){
        return InviteTypeEnums.values();
    }
}
